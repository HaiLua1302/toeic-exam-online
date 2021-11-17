package com.example.user.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Rule_user extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules_user);
        Button rule = findViewById(R.id.btn_rule);

        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Rule_user.this, "step 1", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
