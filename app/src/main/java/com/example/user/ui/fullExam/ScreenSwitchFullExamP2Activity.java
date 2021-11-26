package com.example.user.ui.fullExam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.user.R;

public class ScreenSwitchFullExamP2Activity extends AppCompatActivity {
    String getKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_switch_full_exam_p2);

       /* Intent intent = getIntent();
        Bundle bundle2 = intent.getExtras();
        getKey = bundle2.getString("keyExam");*/

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.wraperfullp2,new DescFullP2Fragment()).commit();

    }
}