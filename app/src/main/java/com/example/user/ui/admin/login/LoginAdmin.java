package com.example.user.ui.admin.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.admin.home.Main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginAdmin extends AppCompatActivity {
    public Button btnLoginAdmin;
    public EditText edtEmailAdmin, edtPassAdmin;
    public ProgressBar process_loading;
    private FirebaseAuth mAuth;
    private String TAG = "Đăng nhập Admin";
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        getSupportActionBar().setTitle("Đăng Nhập Admin"); //Đăng nhập


        btnLoginAdmin = findViewById(R.id.btn_loginAdmin);
        edtEmailAdmin = findViewById(R.id.edt_emailAdmin);
        edtPassAdmin = findViewById(R.id.edt_pass_loginAdmin);
//        process_loading = findViewById(R.id.progressBar_loading_login);
//        process_loading.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        //event click textview register
        TextView txtRegisterAdmin = findViewById(R.id.txt_registerAdmin);
        txtRegisterAdmin.setPaintFlags(txtRegisterAdmin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAdmin.this, RegisterAdmin.class);
                startActivity(intent);

            }
        });

        //event click textview forgotpass
//        TextView txt_forgot_pass = findViewById(R.id.txt_quenmatkhau);
//        txt_forgot_pass.setPaintFlags(txt_forgot_pass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        txt_forgot_pass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginAdmin.this, Forgot_pass_admin.class);
//                startActivity(intent);
//            }
//        });

        //event click textview skip
        TextView txtSkipAdmin = findViewById(R.id.txt_skipAdmin);
        txtSkipAdmin.setPaintFlags(txtSkipAdmin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtSkipAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAdmin.this, Main.class);
                startActivity(intent);

            }
        });
    }

    public void login() {
        String email = edtEmailAdmin.getText().toString().trim();
        String pass = edtPassAdmin.getText().toString().trim();

        if (email.isEmpty()) {
            edtEmailAdmin.setError("Email không được để trống!");
            edtEmailAdmin.requestFocus();
            return;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            edtEmailAdmin.setError("Email không hợp lệ!");
            edtEmailAdmin.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            edtPassAdmin.setError("Mật khẩu không được để trống!");
            edtPassAdmin.requestFocus();
            return;
        }
//        process_loading.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    process_loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAdmin.this, Main.class);
                    startActivity(intent);
                } else {
                    //process_loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại ! Vui lòng kiểm tra lại Email & Mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
