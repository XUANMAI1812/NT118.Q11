package com.example.bai3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!"android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) return;

        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus == null) return;

        SmsMessage[] msgs = new SmsMessage[pdus.length];
        String fullBody = "";
        String sender = "";

        for (int i = 0; i < pdus.length; i++) {
            if (Build.VERSION.SDK_INT >= 23) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], bundle.getString("format"));
            } else {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }

            sender = msgs[i].getOriginatingAddress();
            fullBody += msgs[i].getMessageBody();
        }

        String query = context.getString(R.string.are_you_ok).toLowerCase();

        if (!fullBody.toLowerCase().contains(query)) return;

        ArrayList<String> senders = new ArrayList<>();
        senders.add(sender);

        if (!MainActivity.isRunning) {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, senders);
            context.startActivity(i);
        } else {
            Intent i = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
            i.setPackage(context.getPackageName());
            i.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, senders);
            context.sendBroadcast(i);
        }
    }
}