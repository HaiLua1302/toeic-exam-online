package com.example.user.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Tutorial_user extends AppCompatActivity {
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
                Toast.makeText(Tutorial_user.this, "step 1", Toast.LENGTH_SHORT).show();
            }
        });
        step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Tutorial_user.this, "step 2", Toast.LENGTH_SHORT).show();
            }
        });
        step3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Tutorial_user.this, "step 3", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
