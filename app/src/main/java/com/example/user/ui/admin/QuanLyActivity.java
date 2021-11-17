package com.example.user.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.user.R;
import com.example.user.TestTableActivity;

public class QuanLyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly);

        // quản lý câu hỏi
        findViewById(R.id.btn_part_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuestionManagementActivity.class));
            }
        });

        // quản lý đề thi:
        findViewById(R.id.btn_part_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TestTableActivity.class));
            }
        });

    }
}