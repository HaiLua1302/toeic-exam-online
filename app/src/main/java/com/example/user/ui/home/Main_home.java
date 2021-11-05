package com.example.user.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.user.R;
import com.example.user.ui.exam.Exam_P1_Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("ALL")
public class Main_home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener{

    public Button exam_1;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user);
        getSupportActionBar().setTitle("TOEIC EXAM");

        exam_1 = findViewById(R.id.btn_part_1);
        exam_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_home.this, Exam_P1_Activity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.navigation_bottom);
        /*bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) Main_home.this);
        bottomNavigationView.setSelectedItemId(R.id.home_bar);*/

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.exam_bar:

            case R.id.home:
                Intent intent = new Intent(Main_home.this, Main_home.class);
                startActivity(intent);
                return true;

            case R.id.setting_bar:
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