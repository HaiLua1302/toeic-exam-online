package com.example.user.ui.admin.part6;

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
import com.example.user.ui.classExam.ClsListQuestionP7;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class AddNewAQuesP6Activity extends AppCompatActivity {

    private TextView txtIdExam,txtIdQues,txtCountQues;
    private EditText edtResult,edtAnsA,edtAnsB,edtAnsC,edtAnsD,edtContentExam;
    private Button btnSaveQuestion,btnClear;
    private String idExam,idQues,quesContent,AnsA,AnsB,AnsC,AnsD,Result,idQuesParent;
    private DatabaseReference ref;
    private int countQues = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_a_ques_p6);
        getSupportActionBar().setTitle("Add New Question Part 6");
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
        idQuesParent = bundle.getString("idQuesParent");
        quesContent = bundle.getString("quesContent");
        AnsA = bundle.getString("ansA");
        AnsB = bundle.getString("ansB");
        AnsC = bundle.getString("ansC");
        AnsD = bundle.getString("ansD");
        Result = bundle.getString("result");

        txtIdExam = findViewById(R.id.txtNameExamAQuesP6);
        txtIdQues = findViewById(R.id.txtNameAQuesP6);
        txtCountQues = findViewById(R.id.txtNumAddQuesP6);
        edtContentExam = findViewById(R.id.edtQuesContentP6);
        edtAnsA = findViewById(R.id.edtAnsAP6);
        edtAnsB = findViewById(R.id.edtAnsBP6);
        edtAnsC = findViewById(R.id.edtAnsCP6);
        edtAnsD = findViewById(R.id.edtAnsDP6);
        edtResult = findViewById(R.id.edtResultP6);
        btnSaveQuestion = findViewById(R.id.btnAddAQuestionP6);
        btnClear = findViewById(R.id.btnClearP6);

        txtIdExam.setText(idExam);
        txtIdQues.setText(idQues);
        edtContentExam.setText(quesContent);
        edtAnsA.setText(AnsA);
        edtAnsB.setText(AnsB);
        edtAnsC.setText(AnsC);
        edtAnsD.setText(AnsD);
        edtResult.setText(Result);

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

        btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewQues();
            }
        });
    }
    private void saveNewQues(){
        String nameExam = txtIdExam.getText().toString();
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
        ref = FirebaseDatabase.getInstance().getReference("List_Ques6").child(child);
        ClsListQuestionP7 clsRecExamP7 = new ClsListQuestionP7(ID_Quest,contenQues,ansA,ansB,ansC,ansD,result);
        ref.child(ID_Quest).setValue(clsRecExamP7).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddNewAQuesP6Activity.this, "Lưu câu hỏi thành công", Toast.LENGTH_SHORT).show();
                    countQues++;
                    txtCountQues.setText(String.valueOf(countQues));
                }
                else {
                    Toast.makeText(AddNewAQuesP6Activity.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
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
                Intent intent2 = new Intent(AddNewAQuesP6Activity.this, AddNewQues2P6Activity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}