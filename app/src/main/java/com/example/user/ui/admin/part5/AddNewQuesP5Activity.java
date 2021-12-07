package com.example.user.ui.admin.part5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AddNewP5Adapter;
import com.example.user.ui.admin.AdminHomeActivity;
import com.example.user.ui.admin.ManagerExamActivity;
import com.example.user.ui.classExam.ClsPartP5;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewQuesP5Activity extends AppCompatActivity {

    private EditText edtNameExam;
    private ImageView imgRefresh;
    private Button btnAddNew;
    private RecyclerView recyclerView;
    private AddNewP5Adapter addNewP5Adapter;
    private Intent intent;
    private String idExam;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_ques_p5);
        getSupportActionBar().setTitle("Add New Exam Part 5");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        edtNameExam = findViewById(R.id.edtAddExamName1P5);
        imgRefresh = findViewById(R.id.imgRefresh1P5);
        btnAddNew = findViewById(R.id.btnSaveExamP5);
        recyclerView = findViewById(R.id.recViewListQuest1P5);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");
        edtNameExam.setText(idExam);
        if (!idExam.isEmpty()){
            getSupportActionBar().setTitle("Edit Ques Part 5");
        }

        setDataToRecViewDefault();
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkDuplicate();
                sendNameExam();
            }
        });
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataToRecViewDefault();
            }
        });
    }

    private void sendNameExam(){
        String nameExam = edtNameExam.getText().toString();
        intent = new Intent(AddNewQuesP5Activity.this, AddNewAQuesP5Activity.class);
        intent.putExtra("idExam",nameExam);
        intent.putExtra("idQues","");
        startActivity(intent);

    }
    /*private void checkDuplicate(){
        String id_Exam = edtNameExam.getText().toString();
        DatabaseReference refCheckDup = FirebaseDatabase.getInstance().getReference("Ques_5").child(id_Exam);
        ref = FirebaseDatabase.getInstance().getReference();
        refCheckDup.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    Query query = ref.child("Ques_5").get;
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(AddNewQuesP5.this, "Tên bộ đề đã có ",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                sendNameExam();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/
    private void setDataToRecViewDefault(){
        String nameExam = edtNameExam.getText().toString().trim();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (nameExam.isEmpty()){
            return;
        }else {
            //getting the reference of question part 1 node
            FirebaseRecyclerOptions<ClsPartP5> options =
                    new FirebaseRecyclerOptions.Builder<ClsPartP5>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("Ques_5").child(nameExam), ClsPartP5.class)
                            .build();

            addNewP5Adapter = new AddNewP5Adapter(options,nameExam,this);
            recyclerView.setAdapter(addNewP5Adapter);
            addNewP5Adapter.startListening();
        }
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
             case android.R.id.home:
                intent = new Intent(AddNewQuesP5Activity.this, ManagerExamActivity.class);
                startActivity(intent);
            case R.id.home_bar_admin:
                intent = new Intent(AddNewQuesP5Activity.this, AdminHomeActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

}