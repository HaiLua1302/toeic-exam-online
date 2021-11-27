package com.example.user.ui.admin.part1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.user.R;
import com.example.user.ui.admin.part1.DescQuesAdminFragment;

public class ScreenSwitchQuesAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_switch_ques_admin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.wraperQuesAdmin,new DescQuesAdminFragment()).commit();
    }
}