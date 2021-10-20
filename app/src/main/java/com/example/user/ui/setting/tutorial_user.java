package com.example.user.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class tutorial_user extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_user);

        Button step1 = findViewById(R.id.btn_step_1);
        Button step2 = findViewById(R.id.btn_step_2);
        Button step3 = findViewById(R.id.btn_step_3);
        step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        step3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
