package com.example.bai1;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.anim_in_from_left, R.anim.anim_out_to_right);
        });
    }
}