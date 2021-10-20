package com.example.user.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class rule_user extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button rule = findViewById(R.id.btn_rule);

        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
