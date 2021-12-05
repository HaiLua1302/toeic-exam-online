package com.example.user.ui.admin.fullExam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtRecChoosePart;
import com.example.user.ui.adapterAdmin.AdtRecExamByPart;
import com.example.user.ui.adapterAdmin.AdtRecExamFull;
import com.example.user.ui.classExam.ClsRecExamFull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseAPart1 extends AppCompatActivity implements AdtRecChoosePart.OnShareClickedListener{

    private TextView txtIdExam,txtIdPart;
    private Button btnSavePart;
    private RecyclerView recyclerView;
    private Intent intent;
    private String idExam;
    private DatabaseReference ref;

    private AdtRecChoosePart adtRecChoosePart;
    private AdtRecChoosePart setIdPart = new AdtRecChoosePart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_a_part);

        txtIdExam = findViewById(R.id.txtIdExamFull1);
        txtIdPart = findViewById(R.id.txtNameExamChoose);
        txtIdExam = findViewById(R.id.txtIdExamFull1);
        recyclerView = findViewById(R.id.recViewExamPart1);
        btnSavePart = findViewById(R.id.btnSavaPart1);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");
        getDataFirebase();
        txtIdExam.setText(idExam);

        setIdPart.setOnShareClickedListener(this::ShareClicked);

        btnSavePart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPart = txtIdPart.getText().toString();
                intent = new Intent();
                intent.putExtra("idPart",idPart);
                setResult(2,intent);
                finish();
            }
        });

    }
    private void getDataFirebase(){
        String keyExam = txtIdExam.getText().toString();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> getKey = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("Ques_1");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String key = snapshot1.getKey();
                        getKey.add(key);
                    }
                    adtRecChoosePart = new AdtRecChoosePart(keyExam,getKey);
                    recyclerView.setAdapter(adtRecChoosePart);

                }else {
                    Toast.makeText(ChooseAPart1.this,"Dữ liệu lôi vui lòng kiểm tra Firebase!",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void ShareClicked(String url) {
        txtIdPart.setText(url);
    }
}