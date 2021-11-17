package com.example.user.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_pass_user extends AppCompatActivity {
    public EditText user_Mail;
    public ProgressBar process_loading;
    private Button btn_sendRequest;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_pass_again);
        getSupportActionBar().setTitle("Đặt lại mật khẩu");


        user_Mail = findViewById(R.id.edt_email_otp);
        btn_sendRequest = findViewById(R.id.btnGui);
        process_loading = findViewById(R.id.progressBar_loading_repair_pass);
        process_loading.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        btn_sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process_loading.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(user_Mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            process_loading.setVisibility(View.INVISIBLE);
                            Toast.makeText(Forgot_pass_user.this,"Truy cập Email của bạn để đổi mật khẩu !",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Forgot_pass_user.this, Login_user.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Forgot_pass_user.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //check send request mail
    public void send_request(){
        String email = user_Mail.getText().toString().trim();
        if (email.isEmpty()) {
            user_Mail.setError("Email không được để trống!");
            user_Mail.requestFocus();
            return;
        }

        else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            user_Mail.setError("Email không hợp lệ!");
            user_Mail.requestFocus();
            return;
        }
    }
}