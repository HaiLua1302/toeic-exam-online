package com.example.user.ui.admin.part6;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtAddNew2P6;
import com.example.user.ui.admin.ManagerExamActivity;
import com.example.user.ui.admin.part4.AddNewQues2P4;
import com.example.user.ui.classExam.ClsListQuestionP6;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddNewQues2P6 extends AppCompatActivity {
    private TextView txtNameExam, txtNameQues, txtParagraph;
    private Button btnEditParagraph,btnAddNew,btnDelExam;
    private ImageView imgRefesh;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private String idExam,idQues,paragraph;
    private Intent intent;
    private AdtAddNew2P6 adtNewQues2P6;
    private List<ClsListQuestionP6>listQuestionP6s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_ques2_p6);
        getSupportActionBar().setTitle("Question Part 6 Manager");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");
        idQues = bundle.getString("idQues");
        paragraph = bundle.getString("paragraph");

        txtNameExam = findViewById(R.id.txtNameExam2P6);
        txtNameQues = findViewById(R.id.txtNameQues2P6);
        txtParagraph = findViewById(R.id.txtFilePathEditAudio2P6);
        btnEditParagraph = findViewById(R.id.btnEditAudio2P6);
        btnAddNew = findViewById(R.id.btnAddNewQues2P6);
        btnDelExam = findViewById(R.id.btnDelExam2P6);
        imgRefesh = findViewById(R.id.imgRefreshEdit2P6);
        recyclerView = findViewById(R.id.recViewListQuestEdit2P6);

        txtNameExam.setText(idExam);
        txtNameQues.setText(idQues);
        txtParagraph.setText(paragraph);
        setDataToRecViewDefault(idExam,idQues);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataAddAQues(idExam,idQues);
            }
        });
        btnEditParagraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataEditParagraph(idExam,idQues,paragraph);
            }
        });
        imgRefesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataToRecViewDefault(idExam,idQues);
            }
        });
    }

    private void sendDataAddAQues(String getIdExam,String getIdQues){
        intent = new Intent(AddNewQues2P6.this,AddNewAQuesP6.class);
        intent.putExtra("idExam",getIdExam);
        intent.putExtra("idQues",getIdQues);
        startActivity(intent);
    }
    private void sendDataEditParagraph(String getIdExam,String getIdQues,String getParagraph){
        intent = new Intent(AddNewQues2P6.this,EditParagraphP6.class);
        intent.putExtra("idExam",getIdExam);
        intent.putExtra("idQues",getIdQues);
        intent.putExtra("paragraph",getParagraph);
        startActivity(intent);
    }
    private void setDataToRecViewDefault(String getIdExam,String getIdQues){

        listQuestionP6s = new ArrayList<>();
        String child = getIdExam +"/"+getIdQues+"/Question";
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<ClsListQuestionP6> options =
                new FirebaseRecyclerOptions.Builder<ClsListQuestionP6>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("List_Ques6").child(child), ClsListQuestionP6.class)
                        .build();
        adtNewQues2P6 = new AdtAddNew2P6(options,getIdExam,this);
        recyclerView.setAdapter(adtNewQues2P6);
        adtNewQues2P6.startListening();
    }

    private void delQuestion(String getIdExam,String getIdQues){
        String child = getIdExam +"/"+getIdQues;
        FirebaseDatabase.getInstance().getReference("List_Ques6")
                .child(child)
                .setValue(null)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        FirebaseDatabase.getInstance().getReference()
                                .child("Ques_6")
                                .child(child)
                                .setValue(null)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(AddNewQues2P6.this, "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AddNewQues2P6.this, ManagerExamActivity.class);
                                        startActivity(intent);
                                    }
                                });
                    }
                });
    }
    // this event will enable the back
    // function to the button on press
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