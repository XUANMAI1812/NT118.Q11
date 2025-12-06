package com.example.bai3;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    private ReentrantLock reentrantLock;
    private Switch swAutoResponse;
    private LinearLayout llButtons;
    private Button btnSafe, btnMayday;
    private ArrayList<String> requesters;
    private ArrayAdapter<String> adapter;
    private ListView lvMessages;
    private BroadcastReceiver broadcastReceiver;
    public static boolean isRunning;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private final String AUTO_RESPONSE = "auto_response";

    private void findViewsByIds() {
        swAutoResponse = findViewById(R.id.sw_auto_response);
        llButtons = findViewById(R.id.ll_buttons);
        lvMessages = findViewById(R.id.lv_messages);
        btnSafe = findViewById(R.id.btn_safe);
        btnMayday = findViewById(R.id.btn_mayday);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{
                            android.Manifest.permission.RECEIVE_SMS,
                            android.Manifest.permission.SEND_SMS,
                            android.Manifest.permission.READ_SMS
                    },
                    1000
            );
        }

        findViewsByIds();
        initVariables();
        handleOnClickListenner();

        Intent intent = getIntent();
        if (intent.getExtras() != null &&
                intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY) != null) {

            processReceiveAddresses(intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY));
        }
    }

    private void respond(String to, String response) {
        reentrantLock.lock();
        requesters.remove(to);
        adapter.notifyDataSetChanged();
        reentrantLock.unlock();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(to, null, response, null, null);
    }

    private void respond(boolean ok) {
        String okString = getString(R.string.i_am_safe_and_well_worry_not);
        String notOkString = getString(R.string.tell_my_mother_i_love_her);

        String out = ok ? okString : notOkString;

        ArrayList<String> copy = new ArrayList<>(requesters);
        for (String to : copy) respond(to, out);
    }

    public void processReceiveAddresses(ArrayList<String> addresses) {
        for (String addr : addresses) {
            if (!requesters.contains(addr)) {
                reentrantLock.lock();
                requesters.add(addr);
                adapter.notifyDataSetChanged();
                reentrantLock.unlock();
            }

            if (swAutoResponse.isChecked()) respond(true);
        }
    }

    private void handleOnClickListenner() {
        btnSafe.setOnClickListener(v -> respond(true));
        btnMayday.setOnClickListener(v -> respond(false));

        swAutoResponse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            llButtons.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            editor.putBoolean(AUTO_RESPONSE, isChecked);
            editor.commit();
        });
    }

    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ArrayList<String> addresses =
                        intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY);
                processReceiveAddresses(addresses);
            }
        };
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;

        if (broadcastReceiver == null) initBroadcastReceiver();

        IntentFilter filter = new IntentFilter(SmsReceiver.SMS_FORWARD_BROADCAST_RECEIVER);
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;

        unregisterReceiver(broadcastReceiver);
    }

    private void initVariables() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();

        reentrantLock = new ReentrantLock();
        requesters = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, requesters);
        lvMessages.setAdapter(adapter);

        boolean auto = sharedPreferences.getBoolean(AUTO_RESPONSE, false);
        swAutoResponse.setChecked(auto);
        if (auto) llButtons.setVisibility(View.GONE);

        initBroadcastReceiver();
    }
}