package com.example.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.user.ui.admin.home.Main;
import com.example.user.ui.admin.login.LoginAdmin;
import com.example.user.ui.login.Begin_user;
import com.example.user.ui.login.Login_user;

import android.widget.Toast;

import com.example.user.ui.login.Begin_user;
import com.example.user.ui.login.Login_user;
import com.example.user.ui.setting.achievement_user;
import com.example.user.ui.setting.changelanguage_user;
import com.example.user.ui.setting.changelayout_user;
import com.example.user.ui.setting.feedback_user;
import com.example.user.ui.setting.information_user;
import com.example.user.ui.setting.rule_user;
import com.example.user.ui.setting.setting_user;
import com.example.user.ui.setting.tutorial_user;
import com.example.user.ui.setting.update_information_user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
                isLogged();
            }
        });
    }
    private void isLogged(){
        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null){
//            startActivity(new Intent(MainActivity.this, Login_user.class));
//        }
//        else{
//            startActivity(new Intent(MainActivity.this, setting_user.class));
//        }
        if(user == null){
            startActivity(new Intent(MainActivity.this, Login_user.class));
        }
        else{
            startActivity(new Intent(MainActivity.this, setting_user.class));
        }
    }
}