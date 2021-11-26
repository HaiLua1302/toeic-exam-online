package com.example.user.ui.fullExam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.user.R;

public class TutorialFullExamP5Activity extends AppCompatActivity {
    Button getDataP5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_full_exam_p5);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        getDataP5 = findViewById(R.id.btn_okie_tutorial_full5);
        getDataP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialFullExamP5Activity.this, ScreenSwitchFullExamP5Activity.class);
                startActivity(intent);
            }
        });
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
}