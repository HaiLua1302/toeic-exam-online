package com.example.user.ui.admin.manager_exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.R;
import com.example.user.adapter.AdapterThemDeThi;
import com.example.user.adapter.ClickAddQuenstion;
import com.example.user.models.CauHoiModel;
import com.example.user.models.DeThi;
import com.example.user.ui.admin.ChonCauHoi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddExam extends AppCompatActivity {
    TextView txtTendethi;
    ListView lvCacCauHoi;
    Button btnLuuThemdeThi;
    public DatabaseReference mDatabase ;
    ArrayList<CauHoiModel> list;
    AdapterThemDeThi adapterThemDeThi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

    private void setControl() {
        txtTendethi = findViewById(R.id.txt_Tendethi);
        lvCacCauHoi = findViewById(R.id.lvCacCauHoi);
        btnLuuThemdeThi = findViewById(R.id.btnLuuThemdeThi);
    }
    private void addExam(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Examtest");
        btnLuuThemdeThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
