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
import com.example.user.ui.admin.AdminHome;
import com.example.user.ui.classExam.ClsListQuestionP4;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class AddNewAQuesP4 extends AppCompatActivity {

    private TextView txtNameExam, txtIdQues,txtNumCount;
    private EditText edtResult,edtAnsA,edtAnsB,edtAnsC,edtAnsD,edtContentExam;
    private Button btnSaveQuestion,btnClear;
    private String idExam,idQues;
    private int countQues = 1;
    private DatabaseReference ref;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_a_ques_p4);

        getSupportActionBar().setTitle("Add New Question Part 4");
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

        txtNameExam = findViewById(R.id.txtNameExamQuesP4);
        txtIdQues = findViewById(R.id.txtNameQuesP4);
        txtNumCount = findViewById(R.id.txtNumAddQuesP4);
        edtContentExam = findViewById(R.id.edtQuesContentP4);
        edtAnsA = findViewById(R.id.edtAnsAP4);
        edtAnsB = findViewById(R.id.edtAnsBP4);
        edtAnsC = findViewById(R.id.edtAnsCP4);
        edtAnsD = findViewById(R.id.edtAnsDP4);
        edtResult = findViewById(R.id.edtResultP4);
        btnSaveQuestion = findViewById(R.id.btnAddAQuestionP4);
        btnClear = findViewById(R.id.btnClearP4);

        txtNameExam.setText(idExam);
        txtIdQues.setText(idQues);

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
        String child = nameExam +"/"+idQues+"/Question";
        ref = FirebaseDatabase.getInstance().getReference("List_Ques4").child(child);
        ClsListQuestionP4 clsRecExamP4 = new ClsListQuestionP4(ID_Quest,contenQues,ansA,ansB,ansC,ansD,result);
        ref.child(ID_Quest).setValue(clsRecExamP4).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    countQues++;
                    txtNumCount.setText(String.valueOf(countQues));
                    Toast.makeText(AddNewAQuesP4.this, "Thêm câu hỏi thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddNewAQuesP4.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
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
                Intent intent2 = new Intent(AddNewAQuesP4.this, AdminHome.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}