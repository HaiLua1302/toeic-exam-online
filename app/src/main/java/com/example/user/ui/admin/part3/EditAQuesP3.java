package com.example.user.ui.admin.part3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.user.ui.admin.AdminHome;
import com.example.user.ui.classExam.ClsListQuestionP3;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class EditAQuesP3 extends AppCompatActivity {
    private TextView txtNameExam, txtIdQues,txtNumCount;
    private EditText edtResult,edtAnsA,edtAnsB,edtAnsC,edtAnsD,edtContentExam;
    private Button btnSaveQuestion;
    private String idExam,idQues,result,quesContent,ansA,ansB,ansC,ansD;

    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ques1_p3);
        getSupportActionBar().setTitle("Edit Question Part 3");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");
        idQues = bundle.getString("idQues");
        result = bundle.getString("result");
        quesContent = bundle.getString("quesContent");
        ansA = bundle.getString("ansA");
        ansB = bundle.getString("ansB");
        ansC = bundle.getString("ansC");
        ansD = bundle.getString("ansD");

        txtNameExam = findViewById(R.id.txtNameExamQuesEditP3);
        txtIdQues = findViewById(R.id.txtNameQuesEditP3);
        edtContentExam = findViewById(R.id.edtQuesContentEditP3);
        edtAnsA = findViewById(R.id.edtAnsAEditP3);
        edtAnsB = findViewById(R.id.edtAnsBEditP3);
        edtAnsC = findViewById(R.id.edtAnsCEditP3);
        edtAnsD = findViewById(R.id.edtAnsDEditP3);
        edtResult = findViewById(R.id.edtResultEditP3);
        btnSaveQuestion = findViewById(R.id.btnAddAQuestionEditP3);

        txtNameExam.setText(idExam);
        txtIdQues.setText(idQues);
        edtContentExam.setText(quesContent);
        edtAnsA.setText(ansA);
        edtAnsB.setText(ansB);
        edtAnsC.setText(ansC);
        edtAnsD.setText(ansD);
        edtResult.setText(result);

        btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewQues();
            }
        });
        edtContentExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtContentExam.setText("");
            }
        });
        edtAnsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAnsA.setText("");
            }
        });
        edtAnsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAnsB.setText("");
            }
        });
        edtAnsC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAnsC.setText("");
            }
        });
        edtAnsD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAnsD.setText("");
            }
        });
        edtResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtResult.setText("");
            }
        });
    }

    private void saveNewQues(){
        String nameExam = txtNameExam.getText().toString();
        String idQues = txtIdQues.getText().toString();
        String contenQues = edtContentExam.getText().toString();
        String ansA = edtAnsA.getText().toString();
        String ansB = edtAnsB.getText().toString();
        String ansC = edtAnsC.getText().toString();
        String ansD = edtAnsD.getText().toString();
        String result = edtResult.getText().toString();
        //create id question

        String ID_Quest = nameExam +"_Ques_"+ getTime();
        String child = nameExam + "/" + idQues;
        ref = FirebaseDatabase.getInstance().getReference("List_Ques3").child(child);
        ClsListQuestionP3 clsRecExamP3 = new ClsListQuestionP3(idQues,contenQues,ansA,ansB,ansC,ansD,result);
        ref.setValue(clsRecExamP3).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditAQuesP3.this, "Thêm bộ đề thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditAQuesP3.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
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
                Intent intent2 = new Intent(EditAQuesP3.this, AdminHome.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}