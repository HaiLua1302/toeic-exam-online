package com.example.user.ui.admin.part2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.admin.part1.AddNewListQuesP1Activity;
import com.example.user.ui.classExam.ClsPartP2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class AddNewListQuesP2Activity extends AppCompatActivity {

    private int countNumQues = 1;
    private String idExam;
    private TextView txtxNameExam,txtNumQues;
    private EditText edtResult;
    private Button btnSaveQuestion;

    DatabaseReference databaseQuest_p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_desc_p2);
        getSupportActionBar().setTitle("Add New Question Part 1");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");

        txtxNameExam = findViewById(R.id.txtContentQuestionAdminTitleP2);
        txtNumQues = findViewById(R.id.txtNumAddQuestP2);
        edtResult = findViewById(R.id.edtResultP2);
        btnSaveQuestion = findViewById(R.id.btnAddAQuestionP2);


        txtxNameExam.setText(idExam);
        txtNumQues.setText(String.valueOf(countNumQues));

        btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListQues();
            }
        });
    }

    private void addListQues(){
        //getting the reference of question part 1 node
        String checkEdt = edtResult.getText().toString();
        if (!checkEdt.matches("")){
            databaseQuest_p2 = FirebaseDatabase.getInstance().getReference("List_Ques2").child(idExam);
            String Result = edtResult.getText().toString();
            String child = idExam + "_" + getTime();
            ClsPartP2 clsPartP2 = new ClsPartP2(Result,child);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Question...");
            progressDialog.show();
            databaseQuest_p2.child(child).setValue(clsPartP2).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(AddNewListQuesP2Activity.this, "Thêm câu hỏi "+countNumQues+" thành công ", Toast.LENGTH_SHORT).show();
                        countNumQues++;
                        txtNumQues.setText(String.valueOf(countNumQues));
                    }
                }
            });
        }
        else {
            edtResult.setError("Đáp án không được để trống!");
            edtResult.requestFocus();
            return;
        }

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

    /*
    gettime current
    * */
    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return  currentDateandTime;
    }
}