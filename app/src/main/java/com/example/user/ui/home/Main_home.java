package com.example.user.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.MainActivity;
import com.example.user.R;
import com.example.user.ui.setting.Achievement_user;
import com.example.user.ui.setting.Changelanguage_user;
import com.example.user.ui.setting.Changelayout_user;
import com.example.user.ui.setting.Feedback_user;
import com.example.user.ui.setting.Rule_user;
import com.example.user.ui.setting.Tutorial_user;

public class Main_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user);
    }
}