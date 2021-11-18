package com.example.user.ui.admin.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.admin.login.LoginAdmin;
import com.example.user.ui.admin.manager_exam.Manager_Exam;
import com.example.user.ui.admin.manager_ques.Manager_Question;
import com.example.user.ui.login.Login_user;
import com.example.user.ui.setting.information_user;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {
    public Button btnLogoutAdmin, btnManagerQues, btnManagerExam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly);

        btnLogoutAdmin = findViewById(R.id.btn_logoutAdmin);
        btnLogoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signoutAdmin();
            }
        });

        btnManagerQues = findViewById(R.id.btn_ManagerQues);
        btnManagerQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this, Manager_Question.class));
            }
        });

        btnManagerExam = findViewById(R.id.btn_ManagerExam);
        btnManagerExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this, Manager_Exam.class));
            }
        });
    }

    public void signoutAdmin() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Main.this, "success", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Main.this, LoginAdmin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
