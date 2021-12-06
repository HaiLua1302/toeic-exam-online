package com.example.user.ui.user.exam6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.R;
import com.example.user.ui.home.UserHome;

public class ResultP6Activity extends AppCompatActivity {
    int TotalQuestion = 0;
    int CorrectQuestion = 0;
    TextView txtResultExam,txtPercent;
    ProgressBar progressBarPercent;
    Button continueExam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_p6);
        getSupportActionBar().setTitle("Kết Quả Kiểm Tra");
        txtResultExam = findViewById(R.id.txtResultExamP6);
        txtPercent = findViewById(R.id.txtPercentP6);
        progressBarPercent = findViewById(R.id.progressBarrPercentResultP6);

        continueExam = findViewById(R.id.btnContinueP6);
        continueExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ResultP6Activity.this, UserHome.class);
                startActivity(intent1);
            }
        });

        progressBarPercent.setMax(100);

    }

}