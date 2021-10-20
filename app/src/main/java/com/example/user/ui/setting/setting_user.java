package com.example.user.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class setting_user extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Rule
        Button rule = findViewById(R.id.btn_rule);
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setting_user.this, rule_user.class);
                startActivity(intent);
            }
        });
        //Tutorial
        Button tutorial = findViewById(R.id.btn_tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setting_user.this, tutorial_user.class);
                startActivity(intent);
            }
        });

        //Feedback
//        Button feedback = findViewById(R.id.btn_feedback);
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(setting_user.this, tutorial_user.class);
//                startActivity(intent);
//            }
//        });
    }
}
