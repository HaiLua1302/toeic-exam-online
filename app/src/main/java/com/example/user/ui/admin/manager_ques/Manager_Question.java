package com.example.user.ui.admin.manager_ques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.R;
import com.example.user.adapter.AdapterQuanLyCauHoi;
import com.example.user.adapter.ClickCauHoi;
import com.example.user.models.CauHoi;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Manager_Question extends AppCompatActivity {
    public Button btnAddQues;
    ListView QuestionManagement;

    AdapterQuanLyCauHoi adapterQuanLyCauHoi;
    ArrayList<CauHoi> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_cau_hoi);
        QuestionManagement = findViewById(R.id.listViewDeThi);
        list = new ArrayList<>();

        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));
        list.add(new CauHoi("ád", "ádasda", "A"));

        adapterQuanLyCauHoi = new AdapterQuanLyCauHoi(getApplicationContext(), R.layout.item_quan_ly_cau_hoi, list, new ClickCauHoi() {
            @Override
            public void clickItemCauHoi(CauHoi cauHoi) {
                startActivity(new Intent(getApplicationContext(), QuesDetail.class));
            }
        });
        QuestionManagement.setAdapter(adapterQuanLyCauHoi);
        findViewById(R.id.btn_AddQues).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuesDetail.class));
            }
        });
    }
}
