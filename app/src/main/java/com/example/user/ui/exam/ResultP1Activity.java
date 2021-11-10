package com.example.user.ui.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;

public class ResultP1Activity extends AppCompatActivity {
    int TotalQuestion = 0;
    int CorrectQuestion = 0;
    TextView txtResultExam,txtPercent;
    ProgressBar progressBarPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_result);
        txtResultExam = findViewById(R.id.txtResultExam);
        txtPercent = findViewById(R.id.txtPercent);
        progressBarPercent = findViewById(R.id.progressBarrPercentResult);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
             TotalQuestion = bundle.getInt("TotalQuestion");
            CorrectQuestion = bundle.getInt("CorrectQuestion");

        }
        float percentResult = ((CorrectQuestion / TotalQuestion)*100);

        txtResultExam.setText("Kết quả : "+ String.valueOf(CorrectQuestion)+ " / "+String.valueOf(TotalQuestion));
        txtPercent.setText(String.valueOf(percentResult)+" % ");

        progressBarPercent.setMax(100);
        progressBarPercent.setProgress((int) percentResult);
    }
}