package com.example.lab5bai1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 100);
        }

        showSmsIfAvailable();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        showSmsIfAvailable();
    }

    private void showSmsIfAvailable() {
        String sms = getIntent().getStringExtra("sms");
        if (sms != null) {
            TextView tv = findViewById(R.id.tv_content);
            tv.setText(sms);

            Toast.makeText(this, R.string.you_have_a_new_message, Toast.LENGTH_SHORT).show();
        }
    }
}