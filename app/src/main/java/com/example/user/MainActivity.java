package com.example.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.user.ui.login.Login_user;

import com.example.user.ui.setting.Achievement_user;
import com.example.user.ui.setting.Changelanguage_user;
import com.example.user.ui.setting.Changelayout_user;
import com.example.user.ui.setting.Feedback_user;
import com.example.user.ui.setting.Rule_user;
import com.example.user.ui.setting.Tutorial_user;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.begin_user);

        Button btn_login = findViewById(R.id.btn_batdau);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login_user.class);
                startActivity(intent);
            }
        });

    }
}