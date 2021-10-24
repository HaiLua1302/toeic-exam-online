package com.example.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.ui.login.Begin_user;
import com.example.user.ui.login.Login_user;
import com.example.user.ui.setting.achievement_user;
import com.example.user.ui.setting.changelanguage_user;
import com.example.user.ui.setting.changelayout_user;
import com.example.user.ui.setting.feedback_user;
import com.example.user.ui.setting.setting_user;
import com.example.user.ui.setting.tutorial_user;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.setting_user);

//        Button btn_login = findViewById(R.id.btn_batdau);
//
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Login_user.class);
//                startActivity(intent);
//
//            }
//        });
        Button achie = findViewById(R.id.btn_achievement);
        achie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, achievement_user.class);
                startActivity(intent);
            }
        });
        Button feedback = findViewById(R.id.btn_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, feedback_user.class);
                startActivity(intent);
            }
        });
        Button tutorial = findViewById(R.id.btn_tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tutorial_user.class);
                startActivity(intent);
            }
        });
        Button changelayout = findViewById(R.id.btn_theme);
        changelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, changelayout_user.class);
                startActivity(intent);
            }
        });
        Button changelanguage = findViewById(R.id.btn_language);
        changelanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, changelanguage_user.class);
                startActivity(intent);
            }
        });
    }
}