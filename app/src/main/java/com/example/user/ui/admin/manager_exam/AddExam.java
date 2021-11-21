package com.example.user.ui.admin.manager_exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.adapter.AdapterThemDeThi;
import com.example.user.adapter.ClickAddQuenstion;
import com.example.user.models.CauHoiModel;
import com.example.user.models.DeThi;
import com.example.user.ui.admin.ChonCauHoi;
import com.example.user.ui.setting.information_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
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
        addExam();
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
        String skey = mDatabase.push().getKey();
        SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String timeCurrent = getTime.format(new Date());
        String boDe = "";
        btnLuuThemdeThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DeThi deThi = new DeThi(skey,timeCurrent,boDe);
                        mDatabase.child(skey).setValue(deThi);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddExam.this, "error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
