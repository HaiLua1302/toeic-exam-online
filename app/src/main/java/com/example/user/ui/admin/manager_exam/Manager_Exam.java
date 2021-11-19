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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Manager_Exam extends AppCompatActivity {
    public Button btnAddExam;
    public DatabaseReference mDatabase ;
    public StorageReference storageRef ;
    public FirebaseAuth auth;
    ListView lvBoDe;
    ArrayList<DeThi> list;
    AdapterQuanLyDeThi adapterQuanLyDeThi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_de_thi_layout);
        lvBoDe = findViewById(R.id.lvBoDe);
        list = new ArrayList<DeThi>();
        getListExam();
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
//        list.add(new DeThi("sdf", "a", "de 1 "));
//        list.add(new DeThi("sdf", "a", "de 1 "));
//        list.add(new DeThi("sdf", "a", "de 1 "));
//        list.add(new DeThi("sdf", "a", "de 1 "));
        lvBoDe.setAdapter(adapterQuanLyDeThi);


        // bấm thêm đề thi:
        findViewById(R.id.btn_AddExam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddExam.class));
            }
        });


    }
    public void getListExam(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Examtest");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DeThi deThi = dataSnapshot.getValue(DeThi.class);
                    list.add(deThi);
                    adapterQuanLyDeThi.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
