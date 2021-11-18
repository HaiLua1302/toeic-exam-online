package com.example.user.ui.admin.manager_exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.R;
import com.example.user.adapter.AdapterThemDeThi;
import com.example.user.adapter.ClickAddQuenstion;
import com.example.user.models.CauHoiModel;
import com.example.user.ui.admin.ChonCauHoi;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExamDetail extends AppCompatActivity {
    ArrayList<CauHoiModel> list;
    AdapterThemDeThi adapterThemDeThi;
    ListView lvCacCauHoi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_de_thi);
        Intent intent = getIntent();

        String id = intent.getStringExtra("id_dethi");
        EditText txttendethi = findViewById(R.id.txttendethi);
        txttendethi.setText( intent.getStringExtra("name_dethi"));

        list = new ArrayList<>();
        lvCacCauHoi = findViewById(R.id.lvCacCauHoi);
        adapterThemDeThi = new AdapterThemDeThi(getApplicationContext(), R.layout.item_them_de_thi, list, new ClickAddQuenstion() {
            @Override
            public void clickAddQuenstion(CauHoiModel cauHoi) {
                startActivity(new Intent(getApplicationContext(), ChonCauHoi.class));
            }
        });
        list.add(new CauHoiModel("afasf","sdf","ád"));
        list.add(new CauHoiModel("afasf","sdf","ád"));
        list.add(new CauHoiModel("afasf","sdf","ád"));
        list.add(new CauHoiModel("afasf","sdf","ád"));
        list.add(new CauHoiModel("afasf","sdf","ád"));
        list.add(new CauHoiModel("afasf","sdf","ád"));
        list.add(new CauHoiModel("afasf","sdf","ád"));
        list.add(new CauHoiModel("afasf","sdf","ád"));

        lvCacCauHoi.setAdapter(adapterThemDeThi);
    }
}
