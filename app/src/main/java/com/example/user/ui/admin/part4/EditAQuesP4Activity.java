package com.example.user.ui.admin.part4;

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
import com.example.user.ui.admin.AdminHomeActivity;
import com.example.user.ui.classExam.ClsListQuestionP4;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class EditAQuesP4Activity extends AppCompatActivity {
    private TextView txtNameExam, txtIdQues,txtNumCount;
    private EditText edtResult,edtAnsA,edtAnsB,edtAnsC,edtAnsD,edtContentExam;
    private Button btnSaveQuestion,btnClear;
    private String idExam,idQues,result,quesContent,ansA,ansB,ansC,ansD;
    private DatabaseReference ref;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_a_ques_p4);
        getSupportActionBar().setTitle("Edit Question Part 4");
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

        txtNameExam = findViewById(R.id.txtNameExamQuesEditP4);
        txtIdQues = findViewById(R.id.txtNameQuesEditP4);
        edtContentExam = findViewById(R.id.edtQuesContentEditP4);
        edtAnsA = findViewById(R.id.edtAnsAEditP4);
        edtAnsB = findViewById(R.id.edtAnsBEditP4);
        edtAnsC = findViewById(R.id.edtAnsCEditP4);
        edtAnsD = findViewById(R.id.edtAnsDEditP4);
        edtResult = findViewById(R.id.edtResultEditP4);
        btnSaveQuestion = findViewById(R.id.btnAddAQuestionEditP4);
        btnClear = findViewById(R.id.btnClearEditP4);

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
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtContentExam.setText("");
                edtAnsA.setText("");
                edtAnsB.setText("");
                edtAnsC.setText("");
                edtAnsD.setText("");
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
        ref = FirebaseDatabase.getInstance().getReference("List_Ques4").child(child);
        ClsListQuestionP4 clsRecExamP4 = new ClsListQuestionP4(idQues,contenQues,ansA,ansB,ansC,ansD,result);
        ref.setValue(clsRecExamP4).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditAQuesP4Activity.this, "Thêm bộ đề thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditAQuesP4Activity.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
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
                Intent intent2 = new Intent(EditAQuesP4Activity.this, AdminHomeActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}