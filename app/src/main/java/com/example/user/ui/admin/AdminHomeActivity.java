package com.example.user.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.admin.fullExam.AddNewExamActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

@SuppressWarnings("ALL")
public class AdminHomeActivity extends AppCompatActivity{

    private Button btnAddQuestion,btnAddExam;
    private ActionBar actionBar;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        getSupportActionBar().setTitle("Manager");
        // calling the action bar
        actionBar = getSupportActionBar();
        // showing the back button in action bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bottom_admin);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnAddQuestion = findViewById(R.id.btnAddQuestion);
        btnAddExam = findViewById(R.id.btnAddExam);

        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, ManagerExamActivity.class);
                startActivity(intent);
            }
        });

        btnAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AddNewExamActivity.class);
                startActivity(intent);
            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_bottom_home:
                  /*  intent = new Intent(AdminHomeActivity.this, AdminHomeActivity.class);
                    startActivity(intent);*/
                    return true;
                case R.id.nav_bottom_logout:
                    try {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(AdminHomeActivity.this, "Đăng xuất thành công ",Toast.LENGTH_SHORT).show();
                        intent = new Intent(AdminHomeActivity.this, LoginAdminActivity.class);
                        startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(AdminHomeActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    return true;
            }
            return false;
        }
    };
}