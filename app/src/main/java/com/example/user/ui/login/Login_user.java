package com.example.user.ui.login;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.user.MainActivity;
import com.example.user.R;
import com.example.user.ui.home.Main_home;

import com.example.user.ui.setting.setting_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_user extends AppCompatActivity {
    private Button editLogin;
    private EditText editEmail, editPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
        getSupportActionBar().setTitle("Login"); //Đăng nhập


        editLogin = findViewById(R.id.btn_login);
        editEmail = findViewById(R.id.edt_email);
        editPass = findViewById(R.id.edt_pass_login);

        mAuth = FirebaseAuth.getInstance();

        editLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


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

    private void login() {
        String email, pass;
        email = editEmail.getText().toString();
        pass = editPass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui long nhập email !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Vui lòng nhập password !", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login_user.this, setting_user.class); //change here
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại ! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
