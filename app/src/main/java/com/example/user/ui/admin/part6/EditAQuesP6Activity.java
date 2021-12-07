package com.example.user.ui.admin.part6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.classExam.ClsListQuestionP6;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditAQuesP6Activity extends AppCompatActivity {
    private TextView txtIdExam,txtIdQues,txtCountQues;
    private EditText edtResult,edtAnsA,edtAnsB,edtAnsC,edtAnsD,edtContentExam;
    private Button btnSaveQuestion,btnClear;
    private String idExam,idQues,quesContent,AnsA,AnsB,AnsC,AnsD,Result,edit,idQuesParent;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_a_ques_p6);
        getSupportActionBar().setTitle("Edit A Question Part 6");
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

        txtIdExam = findViewById(R.id.txtNameExamAQuesEditP6);
        txtIdQues = findViewById(R.id.txtNameAQuesEditP6);
        btnClear = findViewById(R.id.btnClearAQuesEditP6);
        edtContentExam = findViewById(R.id.edtQuesContentAQuesEditP6);
        edtAnsA = findViewById(R.id.edtAnsAAQuesEditP6);
        edtAnsB = findViewById(R.id.edtAnsBAQuesEditP6);
        edtAnsC = findViewById(R.id.edtAnsCAQuesEditP6);
        edtAnsD = findViewById(R.id.edtAnsDAQuesEditP6);
        edtResult = findViewById(R.id.edtResultAQuesEditP6);
        btnSaveQuestion = findViewById(R.id.btnAddAQuestionAQuesEditP6);


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
                saveEditQues();
            }
        });
    }
    private void saveEditQues(){
        String getIdExam = txtIdExam.getText().toString();
        String getContenQues = edtContentExam.getText().toString();
        String getAnsA = edtAnsA.getText().toString();
        String getAnsB = edtAnsB.getText().toString();
        String getAnsC = edtAnsC.getText().toString();
        String getAnsD = edtAnsD.getText().toString();
        String getResult = edtResult.getText().toString();

        String child = getIdExam +"/"+idQuesParent+"/Question";
        ref = FirebaseDatabase.getInstance().getReference("List_Ques6").child(child);
        ClsListQuestionP6 clsRecExamP6 = new ClsListQuestionP6(idQues,getContenQues,getAnsA,getAnsB,getAnsC,getAnsD,getResult);
        ref.child(idQues).setValue(clsRecExamP6).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditAQuesP6Activity.this, "Lưu thay đổi thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditAQuesP6Activity.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.home_bar_admin:
                Intent intent2 = new Intent(EditAQuesP6Activity.this, AddNewQues2P6Activity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}