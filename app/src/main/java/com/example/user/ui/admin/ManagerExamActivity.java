package com.example.user.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtRecExamByPart;
import com.example.user.ui.adapterAdmin.AdtSpinerPart;
import com.example.user.ui.admin.part1.AddNewQuesP1Activity;
import com.example.user.ui.admin.part2.AddNewQuesP2Activity;
import com.example.user.ui.admin.part3.AddNewQues1P3;
import com.example.user.ui.admin.part4.AddNewQues1P4;
import com.example.user.ui.admin.part5.AddNewQuesP5;
import com.example.user.ui.admin.part6.AddNewQues1P6;
import com.example.user.ui.admin.part7.AddNewQues1P7;
import com.example.user.ui.classAdmin.clsPartExam;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class ManagerExamActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener{

    private Button btnFilter,btnAddNew;
    private Spinner spnFilter;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recViewQuestion;
    private ArrayList<clsPartExam> clsPartExams;
    private AdtSpinerPart adtSpinerPart;
    private AdtRecExamByPart recPartExam;
    private String clickPartName , idkey;
    private int posSpinner;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager_question);
        getSupportActionBar().setTitle("Exam Manager");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        bottomNavigationView = findViewById(R.id.navigation_bottom_admin);
        btnFilter = findViewById(R.id.btnFilter);
        btnAddNew = findViewById(R.id.btnAddNewQuestion);
        spnFilter = findViewById(R.id.spnFilter);
        recViewQuestion = findViewById(R.id.recViewQuestionAdmin);

        initListPart();

        adtSpinerPart = new AdtSpinerPart(this,clsPartExams);
        spnFilter.setAdapter(adtSpinerPart);

        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clsPartExam clickClsPartExam = (com.example.user.ui.classAdmin.clsPartExam) parent.getItemAtPosition(position);
                clickPartName = clickClsPartExam.getPart();
                posSpinner = spnFilter.getSelectedItemPosition();
                switch(posSpinner) {
                    case 0:
                        getDataFirebase("List_Ques1","Ques_1");
                        break;
                    case 1:
                        getDataFirebase("List_Ques2","Ques_2");
                        break;
                    case 2:
                        getDataFirebase3("List_Ques3","Ques_3");
                        break;
                    case 3:
                        getDataFirebase3("List_Ques4","Ques_4");
                        break;
                    case 4:
                        getDataFirebase2("Ques_5");
                        break;
                    case 5:
                        getDataFirebase3("List_Ques6","Ques_6");
                        break;
                    case 6:
                        getDataFirebase3("List_Ques7","Ques_7");
                        break;
                    default:
                        getDataFirebase("List_Ques1","Ques_1");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posSpinner = spnFilter.getSelectedItemPosition();
                switch(posSpinner) {
                    case 0:
                        getDataFirebase("List_Ques1","Ques_1");
                        break;
                    case 1:
                        getDataFirebase("List_Ques2","Ques_2");
                        break;
                    case 2:
                        getDataFirebase3("List_Ques3","Ques_3");
                        break;
                    case 3:
                        getDataFirebase3("List_Ques4","Ques_4");
                        break;
                    case 4:
                        getDataFirebase2("Ques_5");
                        break;
                    case 5:
                        getDataFirebase3("List_Ques6","Ques_6");
                        break;
                    case 6:
                        getDataFirebase3("List_Ques7","Ques_7");
                        break;
                    default:
                        getDataFirebase("List_Ques1","Ques_1");
                        break;
                }
            }
        });
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                btnFilter.performClick();
            }
        });
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posSpinner = spnFilter.getSelectedItemPosition();
                switch(posSpinner) {
                    case 0:
                        intent = new Intent(ManagerExamActivity.this, AddNewQuesP1Activity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ManagerExamActivity.this, AddNewQuesP2Activity.class);
                        startActivity(intent);
                    case 2:
                        intent = new Intent(ManagerExamActivity.this, AddNewQues1P3.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(ManagerExamActivity.this, AddNewQues1P4.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(ManagerExamActivity.this, AddNewQuesP5.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(ManagerExamActivity.this, AddNewQues1P6.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(ManagerExamActivity.this, AddNewQues1P7.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    default:
                        intent = new Intent(ManagerExamActivity.this, AddNewQuesP1Activity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                btnFilter.performClick();
            }
        });
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }

    private void initListPart(){
        clsPartExams = new ArrayList<>();
        clsPartExams.add(new clsPartExam("Part 1"));
        clsPartExams.add(new clsPartExam("Part 2"));
        clsPartExams.add(new clsPartExam("Part 3"));
        clsPartExams.add(new clsPartExam("Part 4"));
        clsPartExams.add(new clsPartExam("Part 5"));
        clsPartExams.add(new clsPartExam("Part 6"));
        clsPartExams.add(new clsPartExam("Part 7"));
    }

    private void getDataFirebase(String ListQues_id,String Ques_id){
        List<String> getKey = new ArrayList<>();
        List<Integer> countTotal = new ArrayList<>();
        recViewQuestion.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Ques_id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = dataSnapshot.getKey();
                        getKey.add(key);
                    }
                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference(ListQues_id);
                    ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            if (snapshot2.exists()){
                                for (DataSnapshot snapshot3 : snapshot2.getChildren()){
                                    long count = snapshot3.getChildrenCount();
                                    if(count == 0){
                                        countTotal.add(0);
                                    }else {
                                        countTotal.add((int) count);
                                    }

                                }

                                recPartExam = new AdtRecExamByPart(getKey,countTotal,Ques_id);
                                recViewQuestion.setAdapter(recPartExam);
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getDataFirebase2(String child){
        List<String> getKey = new ArrayList<>();
        List<Integer> countTotal = new ArrayList<>();
        recViewQuestion.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(child);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = dataSnapshot.getKey();
                        getKey.add(key);
                        long count = dataSnapshot.getChildrenCount();
                        countTotal.add((int) count);
                        // Toast.makeText(ManagerQuestionActivity.this, countTotal.toString(),Toast.LENGTH_LONG).show();
                    }
                    recPartExam = new AdtRecExamByPart(getKey,countTotal,child);
                    recViewQuestion.setAdapter(recPartExam);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDataFirebase3(String ListQues_id,String Ques_id){
        List<String> getKey = new ArrayList<>();
        List<Integer> countTotal = new ArrayList<>();
        recViewQuestion.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Ques_id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = dataSnapshot.getKey();
                        getKey.add(key);
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            long count = dataSnapshot1.getChildrenCount();
                            countTotal.add((int) count);
                        }
                    }
                    recPartExam = new AdtRecExamByPart(getKey,countTotal,Ques_id);
                    recViewQuestion.setAdapter(recPartExam);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                return true;
            case R.id.home_bar_admin:
                intent = new Intent(ManagerExamActivity.this, AdminHome.class);
                startActivity(intent);
                break;
            case R.id.exam_logout:
                try {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(ManagerExamActivity.this, "Cập nhật dữ liêu thành công ",Toast.LENGTH_SHORT).show();
                    intent = new Intent(ManagerExamActivity.this, LoginAdmin.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(ManagerExamActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                intent = new Intent(ManagerExamActivity.this, AdminHome.class);
                startActivity(intent);
                break;
        }
        return false;
    }
}