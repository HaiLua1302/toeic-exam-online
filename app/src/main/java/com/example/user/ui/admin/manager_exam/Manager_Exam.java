package com.example.user.ui.admin.manager_exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.R;
import com.example.user.adapter.AdapterQuanLyDeThi;
import com.example.user.adapter.ClickItemBoDe;
import com.example.user.models.DeThi;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Manager_Exam extends AppCompatActivity {
    public Button btnAddExam;
    ListView lvBoDe;
    ArrayList<DeThi> list;
    AdapterQuanLyDeThi adapterQuanLyDeThi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_de_thi_layout);
        setControl();
        setAction();
    }
    private void setAction() {
        list = new ArrayList<>();
        adapterQuanLyDeThi = new AdapterQuanLyDeThi(getApplicationContext(), R.layout.item_quan_ly_de_thi, list, new ClickItemBoDe() {
            @Override
            public void clickItemBoDe(DeThi deThi) {
                Intent intent = new Intent(getApplicationContext(), ExamDetail.class);

                intent.putExtra("id_dethi", deThi.getId());
                intent.putExtra("name_dethi", deThi.getBoDe());

                startActivity(intent);
            }
        });
        list.add(new DeThi("sdf", "a", "de 1 "));
        list.add(new DeThi("sdf", "a", "de 1 "));
        list.add(new DeThi("sdf", "a", "de 1 "));
        list.add(new DeThi("sdf", "a", "de 1 "));
        lvBoDe.setAdapter(adapterQuanLyDeThi);


        // bấm thêm đề thi:
        findViewById(R.id.btn_AddExam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddExam.class));
            }
        });


    }

    private void setControl() {
        lvBoDe = findViewById(R.id.lvBoDe);
    }
}
