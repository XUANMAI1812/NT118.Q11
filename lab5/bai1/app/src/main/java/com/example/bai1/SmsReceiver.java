package com.example.bai1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus == null) return;

        String format = bundle.getString("format");
        SmsMessage sms;

        StringBuilder sb = new StringBuilder();

        for (Object pdu : pdus) {

            if (android.os.Build.VERSION.SDK_INT >= 23) {
                sms = SmsMessage.createFromPdu((byte[]) pdu, format);
            } else {
                sms = SmsMessage.createFromPdu((byte[]) pdu);
            }

            sb.append(sms.getDisplayOriginatingAddress())
                    .append(":\n")
                    .append(sms.getMessageBody())
                    .append("\n\n");
        }

        Intent i = new Intent(context, MainActivity.class);
        i.putExtra("sms", sb.toString());
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(i);
    }
}
