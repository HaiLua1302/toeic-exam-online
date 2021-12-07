package com.example.user.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.RecExamByPartAdapter;
import com.example.user.ui.adapterAdmin.SpinerPartAdapter;
import com.example.user.ui.admin.part1.AddNewQuesP1Activity;
import com.example.user.ui.admin.part2.AddNewQuesP2Activity;
import com.example.user.ui.admin.part3.AddNewQues1P3Activity;
import com.example.user.ui.admin.part4.AddNewQues1P4Activity;
import com.example.user.ui.admin.part5.AddNewQuesP5Activity;
import com.example.user.ui.admin.part6.AddNewQues1P6Activity;
import com.example.user.ui.admin.part7.AddNewQues1P7Activity;
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

    private ActionBar actionBar;

    private Button btnFilter,btnAddNew;
    private Spinner spnFilter;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recViewQuestion;
    private ArrayList<clsPartExam> clsPartExams;
    private SpinerPartAdapter spinerPartAdapter;
    private RecExamByPartAdapter recExamByPartAdapter;
    private String clickPartName , idkey , idQuesInit;
    private int posSpinner;
    private Intent intent;
    private DatabaseReference ref;
    private DatabaseReference ref1;

    private List<String> getKeyList;
    private List<Integer> countTotalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager_question);
        getSupportActionBar().setTitle("Exam Manager");
        // calling the action bar
        actionBar = getSupportActionBar();
        // showing the back button in action bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bottom_admin);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        actionBar.setDisplayHomeAsUpEnabled(true);

        bottomNavigationView = findViewById(R.id.navigation_bottom_admin);
        btnFilter = findViewById(R.id.btnFilter);
        btnAddNew = findViewById(R.id.btnAddNewQuestion);
        spnFilter = findViewById(R.id.spnFilter);
        recViewQuestion = findViewById(R.id.recViewQuestionAdmin);

        initListPart();
        initRecyclerView();
        spinerPartAdapter = new SpinerPartAdapter(this,clsPartExams);
        spnFilter.setAdapter(spinerPartAdapter);

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


        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posSpinner = spnFilter.getSelectedItemPosition();
                switch(posSpinner) {
                    case 1:
                        intent = new Intent(ManagerExamActivity.this, AddNewQuesP2Activity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(ManagerExamActivity.this, AddNewQues1P3Activity.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(ManagerExamActivity.this, AddNewQues1P4Activity.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(ManagerExamActivity.this, AddNewQuesP5Activity.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(ManagerExamActivity.this, AddNewQues1P6Activity.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(ManagerExamActivity.this, AddNewQues1P7Activity.class);
                        intent.putExtra("idExam","");
                        startActivity(intent);
                        break;
                    case 0:
                    default:
                        intent = new Intent(ManagerExamActivity.this, AddNewQuesP1Activity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        /*new Handler().post(new Runnable() {
            @Override
            public void run() {
                btnFilter.performClick();
            }
        });*/
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

    private void initRecyclerView(){
        getKeyList = new ArrayList<>();
        countTotalList = new ArrayList<>();
        recViewQuestion.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataFirebase(String ListQues_id,String Ques_id){
        ref = FirebaseDatabase.getInstance().getReference(Ques_id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    getKeyList.clear();
                    countTotalList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = dataSnapshot.getKey();
                        getKeyList.add(key);
                    }
                    ref1 = FirebaseDatabase.getInstance().getReference(ListQues_id);
                    ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            if (snapshot2.exists()){
                                for (DataSnapshot snapshot3 : snapshot2.getChildren()){
                                    long count = snapshot3.getChildrenCount();
                                    if(count == 0){
                                        countTotalList.add(0);
                                    }else {
                                        countTotalList.add((int) count);
                                    }

                                }
                                recExamByPartAdapter = new RecExamByPartAdapter(getKeyList, countTotalList,Ques_id);
                                recViewQuestion.setAdapter(recExamByPartAdapter);
                                recExamByPartAdapter.notifyDataSetChanged();
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

    private void getDataFirebase2(String Ques_id){
        ref = FirebaseDatabase.getInstance().getReference(Ques_id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    getKeyList.clear();
                    countTotalList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = dataSnapshot.getKey();
                        getKeyList.add(key);
                        long count = dataSnapshot.getChildrenCount();
                        countTotalList.add((int) count);
                        // Toast.makeText(ManagerQuestionActivity.this, countTotal.toString(),Toast.LENGTH_LONG).show();
                    }
                    recExamByPartAdapter = new RecExamByPartAdapter(getKeyList, countTotalList,Ques_id);
                    recViewQuestion.setAdapter(recExamByPartAdapter);
                    recExamByPartAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDataFirebase3(String ListQues_id,String Ques_id){
        ref = FirebaseDatabase.getInstance().getReference(Ques_id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    getKeyList.clear();
                    countTotalList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = dataSnapshot.getKey();
                        getKeyList.add(key);
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            long count = dataSnapshot1.getChildrenCount();
                            countTotalList.add((int) count);
                        }
                    }
                    recExamByPartAdapter = new RecExamByPartAdapter(getKeyList, countTotalList,Ques_id);
                    recViewQuestion.setAdapter(recExamByPartAdapter);
                    recExamByPartAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_bottom_home:
                    intent = new Intent(ManagerExamActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.nav_bottom_logout:
                    try {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(ManagerExamActivity.this, "Đăng xuất thành công ",Toast.LENGTH_SHORT).show();
                        intent = new Intent(ManagerExamActivity.this, LoginAdminActivity.class);
                        startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(ManagerExamActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    return true;
            }
            return false;
        }
    };
/*
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
    }*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}