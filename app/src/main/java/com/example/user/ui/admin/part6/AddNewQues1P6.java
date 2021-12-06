package com.example.user.ui.admin.part6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtAddNew1P6;
import com.example.user.ui.adapterAdmin.AdtAddNew1P6;
import com.example.user.ui.adapterAdmin.AdtAddNew1P6;
import com.example.user.ui.admin.AdminHome;
import com.example.user.ui.admin.ManagerExamActivity;
import com.example.user.ui.classExam.ClsRecExamP6;
import com.example.user.ui.classExam.ClsRecExamP6;
import com.example.user.ui.classExam.ClsRecExamP6;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddNewQues1P6 extends AppCompatActivity {
    private EditText edtNameExam,edtParagraph;
    private ImageView imgRefresh;
    private Button btnSaveParagraph,btnDelExam;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private String getExam,id_Exam;
    private Intent intent;

    private AdtAddNew1P6 adtAddNew1P6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_ques1_p6);
        getSupportActionBar().setTitle("Add New Ques Part 6");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        edtNameExam = findViewById(R.id.edtAddExamName1P6);
        edtParagraph = findViewById(R.id.edtAddParagraphP6);
        imgRefresh = findViewById(R.id.imgRefresh1P6);
        btnSaveParagraph = findViewById(R.id.btnAddParagraph1P6);
        recyclerView = findViewById(R.id.recViewListQuest1P6);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        getExam = bundle.getString("idExam");
        edtNameExam.setText(getExam);
        id_Exam = edtNameExam.getText().toString();

        if (!getExam.isEmpty()){
            getSupportActionBar().setTitle("Edit Ques Part 6");
        }
        setDataToRecViewDefault();
        btnSaveParagraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQues();
            }
        });
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create id question
                setDataToRecViewDefault();
            }
        });

    }
    private void checkDuplicate(){
        ref = FirebaseDatabase.getInstance().getReference();
        Query query = ref.child("Ques_6").orderByChild("id_exam").equalTo(id_Exam);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    addQues();
                    Toast.makeText(AddNewQues1P6.this, "Thêm câu hỏi thành công ",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void addQues() {
        //create id question
        final ProgressDialog progressDialog_audio = new ProgressDialog(AddNewQues1P6.this);
        progressDialog_audio.setTitle("Save Exam...");
        progressDialog_audio.show();
        String paragraph = edtParagraph.getText().toString();
        String idExam = edtNameExam.getText().toString();
        String ID_Quest = "QuesP6_"+ getTime();
        ref = FirebaseDatabase.getInstance().getReference("Ques_6").child(idExam);
        ClsRecExamP6 clsRecExamP6 = new ClsRecExamP6(idExam,ID_Quest,paragraph);
        ref.child(ID_Quest).setValue(clsRecExamP6).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddNewQues1P6.this, "Thêm bộ đề thành công", Toast.LENGTH_SHORT).show();
                    progressDialog_audio.dismiss();
                    Intent intent = new Intent(AddNewQues1P6.this,AddNewQues2P6.class);
                    intent.putExtra("idExam",idExam);
                    intent.putExtra("idQues",ID_Quest);
                    intent.putExtra("paragraph",paragraph);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AddNewQues1P6.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setDataToRecViewDefault(){
        recyclerView.setLayoutManager(new LinearLayoutManager(AddNewQues1P6.this));
        id_Exam = edtNameExam.getText().toString();
        if (id_Exam.isEmpty()){
            return;
        }else {
            final ProgressDialog progressDialog = new ProgressDialog(AddNewQues1P6.this);
            progressDialog.setTitle("Wait...");
            progressDialog.show();
            //getting the reference of question part 1 node
            FirebaseRecyclerOptions<ClsRecExamP6> options =
                    new FirebaseRecyclerOptions.Builder<ClsRecExamP6>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("Ques_6").child(id_Exam), ClsRecExamP6.class)
                            .build();

            adtAddNew1P6 = new AdtAddNew1P6(options,id_Exam,this);
            recyclerView.setAdapter(adtAddNew1P6);
            progressDialog.dismiss();
            adtAddNew1P6.startListening();
        }
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
                intent = new Intent(AddNewQues1P6.this, AdminHome.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}