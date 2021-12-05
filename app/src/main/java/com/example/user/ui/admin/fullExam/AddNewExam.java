package com.example.user.ui.admin.fullExam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtRecExamFull;
import com.example.user.ui.admin.AdminHome;
import com.example.user.ui.admin.ManagerExamActivity;
import com.example.user.ui.classExam.ClsRecExamFull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddNewExam extends AppCompatActivity {

    private EditText edtNameExam;
    private ImageView imgRefresh;
    private Button btnAddNewExam;
    private RecyclerView recyclerView;
    private AdtRecExamFull adtRecExamFull;
    private DatabaseReference ref;
    private List<ClsRecExamFull> clsRecExamFullList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_exam);
        getSupportActionBar().setTitle("Add New Exam");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        edtNameExam = findViewById(R.id.edtAddExamNameFull);
        imgRefresh = findViewById(R.id.imgRefreshFull);
        btnAddNewExam = findViewById(R.id.btnIntentToAddQuesFull);
        recyclerView = findViewById(R.id.recViewListQuestEditFull);
        getDataFirebase();

        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFirebase();
            }
        });
        btnAddNewExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idExam = edtNameExam.getText().toString();
                Intent intent = new Intent(AddNewExam.this, AddNewAPart.class);
                intent.putExtra("idExam",idExam);
                intent.putExtra("idExam1","...");
                intent.putExtra("idQues1","...");
                intent.putExtra("idExam2","...");
                intent.putExtra("idQues2","...");
                intent.putExtra("idExam3","...");
                intent.putExtra("idQues3","...");
                intent.putExtra("idExam4","...");
                intent.putExtra("idQues4","...");
                intent.putExtra("idExam5","...");
                intent.putExtra("idQues5","...");
                intent.putExtra("idExam6","...");
                intent.putExtra("idQues6","...");
                intent.putExtra("idExam7","...");
                intent.putExtra("idQues7","...");
                startActivity(intent);
            }
        });
    }

    private void getDataFirebase(){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            clsRecExamFullList = new ArrayList<>();
            List<String> getKey = new ArrayList<>();
            ref = FirebaseDatabase.getInstance().getReference("Exam");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String key = snapshot1.getKey();
                            getKey.add(key);
                            ClsRecExamFull examFull = snapshot1.getValue(ClsRecExamFull.class);
                            clsRecExamFullList.add(examFull);

                        }
                        adtRecExamFull = new AdtRecExamFull(getKey, clsRecExamFullList);
                        recyclerView.setAdapter(adtRecExamFull);
                    }else {
                        Toast.makeText(AddNewExam.this,"Dữ liệu lôi vui lòng kiểm tra Firebase!",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

    }

    /*
  gettime current
  * */
    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return  currentDateandTime;
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               this.finish();
               return true;
            case R.id.home_bar_admin:
                Intent intent2 = new Intent(AddNewExam.this, AdminHome.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}