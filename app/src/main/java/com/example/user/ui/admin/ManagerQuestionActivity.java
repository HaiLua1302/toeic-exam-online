package com.example.user.ui.admin;

import androidx.annotation.NonNull;
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

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtRecExamByPart;
import com.example.user.ui.adapterAdmin.AdtSpinerPart;
import com.example.user.ui.admin.part1.AddNewQuesP1Activity;
import com.example.user.ui.admin.part2.AddNewQuesP2Activity;
import com.example.user.ui.classAdmin.clsPartExam;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class ManagerQuestionActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener{

    private Button btnFilter,btnAddNew;
    private Spinner spnFilter;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recViewQuestion;
    private ArrayList<clsPartExam> clsPartExams;
    private AdtSpinerPart adtSpinerPart;
    private AdtRecExamByPart recPartExam;
    private String clickPartName;
    private int posSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager_question);
        getSupportActionBar().setTitle("Exam Manager");

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
                        getDataFirebase("List_Ques3","Ques_3");
                        break;
                    case 3:
                        getDataFirebase("List_Ques4","Ques_4");
                        break;
                    case 4:
                        getDataFirebase2("List_Ques5","Ques_5");
                        break;
                    case 5:
                        getDataFirebase("List_Ques6","Ques_6");
                        break;
                    case 6:
                        getDataFirebase("List_Ques7","Ques_7");
                        break;
                    default:
                        getDataFirebase("List_Ques1","Ques_1");
                        break;
                }
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posSpinner = spnFilter.getSelectedItemPosition();
                switch(posSpinner) {
                    case 0:
                        Intent intent = new Intent(ManagerQuestionActivity.this, AddNewQuesP1Activity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(ManagerQuestionActivity.this, AddNewQuesP2Activity.class);
                        startActivity(intent2);
                    case 2:
                        //getDataFirebase("List_Ques3","Ques_3");
                        break;
                    case 3:
                       // getDataFirebase("List_Ques4","Ques_4");
                        break;
                    case 4:
                       // getDataFirebase2("List_Ques5","Ques_5");
                        break;
                    case 5:
                       // getDataFirebase("List_Ques6","Ques_6");
                        break;
                    default:
                      //  getDataFirebase("List_ques7","Ques_7");
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

    private void getDataFirebase(String reference,String child){
        List<String> getKey = new ArrayList<>();
        List<Integer> countTotal = new ArrayList<>();
        recViewQuestion.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(child);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    getKey.add(key);
                }
                DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference(reference);
                ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                        for (DataSnapshot snapshot3 : snapshot2.getChildren()){
                            long count = snapshot3.getChildrenCount();
                            countTotal.add((int) count);
                        }
                        recPartExam = new AdtRecExamByPart(getKey,countTotal,child);
                        recViewQuestion.setAdapter(recPartExam);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getDataFirebase2(String reference,String child){
        List<String> getKey = new ArrayList<>();
        List<Integer> countTotal = new ArrayList<>();
        recViewQuestion.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(child);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}