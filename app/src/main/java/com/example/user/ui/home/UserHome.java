package com.example.user.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.user.R;
import com.example.user.ui.setting.UserSetingActivity;
import com.example.user.ui.user.exam1.TutorialP1Activity;
import com.example.user.ui.user.exam2.TutorialP2Activity;
import com.example.user.ui.user.exam3.TutorialP3Activity;
import com.example.user.ui.user.exam4.TutorialP4Activity;
import com.example.user.ui.user.exam5.TutorialP5Activity;
import com.example.user.ui.user.exam6.TutorialP6Activity;
import com.example.user.ui.user.exam7.TutorialP7Activity;
import com.example.user.ui.user.fullExam.RecListExamFull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("ALL")
public class UserHome extends AppCompatActivity {

    public Button exam_1,exam_2,exam_3,exam_4,exam_5,exam_6,exam_7,full_exam;
    private ActionBar actionBar;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        getSupportActionBar().setTitle("TOEIC EXAM");
        // showing the back button in action bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bottom_user);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        exam_1 = findViewById(R.id.btn_part_1);
        exam_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, TutorialP1Activity.class);
                startActivity(intent);
            }
        });
        exam_2 = findViewById(R.id.btn_part_2);
        exam_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, TutorialP2Activity.class);
                startActivity(intent);
            }
        });
        exam_3 = findViewById(R.id.btn_part_3);
        exam_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, TutorialP3Activity.class);
                startActivity(intent);
            }
        });
        exam_4 = findViewById(R.id.btn_part_4);
        exam_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, TutorialP4Activity.class);
                startActivity(intent);
            }
        });
        exam_5 = findViewById(R.id.btn_part_5);
        exam_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, TutorialP5Activity.class);
                startActivity(intent);
            }
        });
        exam_6 = findViewById(R.id.btn_part_6);
        exam_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, TutorialP6Activity.class);
                startActivity(intent);
            }
        });
        exam_7 = findViewById(R.id.btn_part_7);
        exam_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, TutorialP7Activity.class);
                startActivity(intent);
            }
        });
        full_exam = findViewById(R.id.btn_full_exam);
        full_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this, RecListExamFull.class);
                startActivity(intent);
            }
        });


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_bar_user:
                   /* intent = new Intent(UserHome.this, AdminHomeActivity.class);
                    startActivity(intent);*/
                    return true;
                case R.id.setting_bar_user:
                    intent = new Intent(UserHome.this, UserSetingActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.exam_bar_user:

            case R.id.home:
                Intent intent = new Intent(UserHome.this, UserHome.class);
                startActivity(intent);
                return true;

            case R.id.setting_bar_user:
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}