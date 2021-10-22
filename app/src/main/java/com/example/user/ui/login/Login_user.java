package com.example.user.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.MainActivity;
import com.example.user.R;
import com.example.user.ui.home.Main_home;
import com.google.firebase.auth.FirebaseAuth;

public class Login_user extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
        getSupportActionBar().setTitle("Login"); //Đăng nhập

        TextView txt_register = findViewById(R.id.txt_register);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_user.this, Register_user.class);
                startActivity(intent);

            }
        });

        TextView txt_forgot_pass = findViewById(R.id.txt_quenmatkhau);
        txt_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_user.this, Forgot_pass_user.class);
                startActivity(intent);

            }
        });

        TextView txt_skip = findViewById(R.id.txt_skip);
        txt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_user.this, Main_home.class);
                startActivity(intent);

            }
        });


    }

}
