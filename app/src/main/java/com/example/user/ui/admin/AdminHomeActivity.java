package com.example.user.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.user.R;
import com.example.user.ui.home.Main_home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("ALL")
public class AdminHomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener{
    private BottomNavigationView bottomNavigationView;
    private Button btnAddQuestion,btnAddExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        getSupportActionBar().setTitle("Manager");

        bottomNavigationView = findViewById(R.id.navigation_bottom_admin);
        btnAddQuestion = findViewById(R.id.btnAddQuestion);
        btnAddExam = findViewById(R.id.btnAddExam);

        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this,ManagerQuestionActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home_bar_admin:
                Intent intent = new Intent(AdminHomeActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.exam_logout:
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}