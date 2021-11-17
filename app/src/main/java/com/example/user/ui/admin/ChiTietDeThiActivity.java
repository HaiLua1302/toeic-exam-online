package com.example.user.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.R;
import com.example.user.adapter.AdapterThemDeThi;
import com.example.user.adapter.ClickAddQuenstion;
import com.example.user.models.CauHoiModel;

import java.util.ArrayList;

public class ChiTietDeThiActivity extends AppCompatActivity {



    ArrayList<CauHoiModel> list;
    AdapterThemDeThi adapterThemDeThi;
    ListView lvCacCauHoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                startActivity(new Intent(getApplicationContext(), ChonCauHoiActivity.class));
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