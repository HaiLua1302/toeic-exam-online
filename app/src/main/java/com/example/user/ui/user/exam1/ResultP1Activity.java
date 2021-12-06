package com.example.user.ui.user.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.R;
import com.example.user.ui.home.UserHome;

public class ResultP1Activity extends AppCompatActivity {
    int TotalQuestion = 0;
    int CorrectQuestion = 0;
    TextView txtResultExam,txtPercent;
    ProgressBar progressBarPercent;
    Button continueExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_p1);
        txtResultExam = findViewById(R.id.txtResultExam);
        txtPercent = findViewById(R.id.txtPercent);
        progressBarPercent = findViewById(R.id.progressBarrPercentResult);
        getSupportActionBar().setTitle("Kết quả");
        // calling the action bar

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
             TotalQuestion = bundle.getInt("TotalQuestion");
            CorrectQuestion = bundle.getInt("CorrectQuestion");

        }
        continueExam = findViewById(R.id.btnContinueP1);
        continueExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ResultP1Activity.this, UserHome.class);
                startActivity(intent1);
            }
        });
        float percentResult = ((CorrectQuestion / TotalQuestion)*100);

        txtResultExam.setText("Kết quả : "+ String.valueOf(CorrectQuestion)+ " / "+String.valueOf(TotalQuestion));
        txtPercent.setText(String.valueOf(percentResult)+" % ");

        progressBarPercent.setMax(100);
        progressBarPercent.setProgress((int) percentResult);
    }
}