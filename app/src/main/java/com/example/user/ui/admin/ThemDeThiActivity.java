package com.example.user.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.user.R;
import com.example.user.adapter.AdapterThemDeThi;
import com.example.user.adapter.ClickAddQuenstion;
import com.example.user.models.CauHoiModel;

import java.util.ArrayList;

public class ThemDeThiActivity extends AppCompatActivity {
    TextView txttendethi;
    ListView lvCacCauHoi;
    Button btnLuuThemdeThi;

    ArrayList<CauHoiModel> list;
    AdapterThemDeThi adapterThemDeThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_de_thi);
        setControl();
        setAction();



    }

    private void setAction() {
        list = new ArrayList<>();
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

    private void setControl() {
        txttendethi = findViewById(R.id.txttendethi);
        lvCacCauHoi = findViewById(R.id.lvCacCauHoi);
        btnLuuThemdeThi = findViewById(R.id.btnLuuThemdeThi);
    }
}