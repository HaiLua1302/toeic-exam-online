package com.example.user.ui.admin.part5;

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
import com.example.user.ui.admin.AdminHomeActivity;
import com.example.user.ui.classExam.ClsPartP5;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditAQuesP5Activity extends AppCompatActivity {

    private TextView txtNameExam, txtIdQues, txtNumCount;
    private EditText edtResult, edtAnsA, edtAnsB, edtAnsC, edtAnsD, edtContentExam;
    private Button btnSaveQuestion, btnClear;
    private String idExam, idQues,contenQues,ansA,ansB,ansC,ansD,result,iNoun;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_a_ques_p5);
        getSupportActionBar().setTitle("Edit Question Part 5");
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
        contenQues = bundle.getString("quesContent");
        ansA = bundle.getString("ansA");
        ansB = bundle.getString("ansB");
        ansC = bundle.getString("ansC");
        ansD = bundle.getString("ansD");
        result = bundle.getString("result");
        iNoun = bundle.getString("noun");

        txtNameExam = findViewById(R.id.txtNameExamQuesEditP5);
        txtIdQues = findViewById(R.id.txtNameQuesEditP5);
        txtNumCount = findViewById(R.id.txtNumAddQuesEditP5);
        edtContentExam = findViewById(R.id.edtQuesContentEditP5);
        edtAnsA = findViewById(R.id.edtAnsAEditP5);
        edtAnsB = findViewById(R.id.edtAnsBEditP5);
        edtAnsC = findViewById(R.id.edtAnsCEditP5);
        edtAnsD = findViewById(R.id.edtAnsDEditP5);
        edtResult = findViewById(R.id.edtResultEditP5);
        btnSaveQuestion = findViewById(R.id.btnEditAQuestionP5);
        btnClear = findViewById(R.id.btnClearEditP5);

        txtNameExam.setText(idExam);
        txtIdQues.setText(idQues);
        txtNumCount.setText(iNoun);
        edtContentExam.setText(contenQues);
        edtAnsA.setText(ansA);
        edtAnsB.setText(ansB);
        edtAnsC.setText(ansC);
        edtAnsD.setText(ansD);
        edtResult.setText(result);
        btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEditQues();
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
    private void saveEditQues() {
        idExam = txtNameExam.getText().toString();
        contenQues = edtContentExam.getText().toString();
        ansA = edtAnsA.getText().toString();
        ansB = edtAnsB.getText().toString();
        ansC = edtAnsC.getText().toString();
        ansD = edtAnsD.getText().toString();
        result = edtResult.getText().toString();

        //create id question

        String child = idExam +"/"+idQues;
        ref = FirebaseDatabase.getInstance().getReference("Ques_5").child(child);
        ClsPartP5 clsRecExamP5 = new ClsPartP5(child, idExam, contenQues, ansA, ansB, ansC, ansD, result);
        ref.setValue(clsRecExamP5).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(EditAQuesP5Activity.this, "Lưu thay đổi thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditAQuesP5Activity.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        // this event will enable the back
        // function to the button on press
        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    this.finish();
                    return true;
                case R.id.nav_bottom_home:
                    Intent intent2 = new Intent(EditAQuesP5Activity.this, AdminHomeActivity.class);
                    startActivity(intent2);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
        ;
    }
