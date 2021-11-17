package com.example.user.ui.admin;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.user.R;
import com.example.user.ui.home.Main_home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_admin extends AppCompatActivity {
    public Button btn_Login;
    public EditText edit_Email, edit_Pass;
    public ProgressBar process_loading;
    private FirebaseAuth mAuth;
    private String TAG = "Đăng nhập Admin";
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        getSupportActionBar().setTitle("Đăng Nhập"); //Đăng nhập


        btn_Login = findViewById(R.id.btn_login);
        edit_Email = findViewById(R.id.edt_email);
        edit_Pass = findViewById(R.id.edt_pass_login);
        process_loading = findViewById(R.id.progressBar_loading_login);
        process_loading.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        //event click textview register
        TextView txt_register = findViewById(R.id.txt_register);
        txt_register.setPaintFlags(txt_register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_admin.this, Register_admin.class);
                startActivity(intent);

            }
        });

        //event click textview forgotpass
        TextView txt_forgot_pass = findViewById(R.id.txt_quenmatkhau);
        txt_forgot_pass.setPaintFlags(txt_forgot_pass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_admin.this, Forgot_pass_admin.class);
                startActivity(intent);
            }
        });

        //event click textview skip
        TextView txt_skip = findViewById(R.id.txt_skip);
        txt_skip.setPaintFlags(txt_skip.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_admin.this, Main_home.class);
                startActivity(intent);

            }
        });
    }

    public void login() {
        String email = edit_Email.getText().toString().trim();
        String pass = edit_Pass.getText().toString().trim();

        if (email.isEmpty()) {
            edit_Email.setError("Email không được để trống!");
            edit_Email.requestFocus();
            return;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            edit_Email.setError("Email không hợp lệ!");
            edit_Email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            edit_Pass.setError("Mật khẩu không được để trống!");
            edit_Pass.requestFocus();
            return;
        }
        process_loading.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    process_loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login_admin.this, Main_home.class);
                    startActivity(intent);
                } else {
                    process_loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại ! Vui lòng kiểm tra lại Email & Mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}