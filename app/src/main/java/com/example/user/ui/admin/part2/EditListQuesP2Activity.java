package com.example.user.ui.admin.part2;

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
import com.example.user.ui.admin.part1.EditListQuesP1Activity;
import com.example.user.ui.classExam.ClsPartP1;
import com.example.user.ui.classExam.ClsPartP2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditListQuesP2Activity extends AppCompatActivity {

    private String  result, idExam ,idQues;

    private TextView txtNameExam,txtNameQues;
    private EditText edtResult;
    private Button btnSaveEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_a_question_p2);
        getSupportActionBar().setTitle("Edit Desc Quesion");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtNameExam = findViewById(R.id.txtContentQuestionEditAdminTitleP2);
        txtNameQues = findViewById(R.id.txtNameQuesP2);

        edtResult = findViewById(R.id.edtEditResultP2);

        btnSaveEdit = findViewById(R.id.btnSaveEditAQuestionP2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        result = bundle.getString("result");
        idExam = bundle.getString("idExam");
        idQues = bundle.getString("idQues");

        txtNameExam.setText(idExam);
        txtNameQues.setText(idQues);
        edtResult.setText(result);
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAQuestion();
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

    private void editAQuestion(){
        //getting the reference of question part 1 node
        DatabaseReference databaseQuest_p2 = FirebaseDatabase.getInstance().getReference().child("List_Ques2");
        String Result = edtResult.getText().toString();
        ClsPartP2 clsPartP2 = new ClsPartP2(idQues,Result);
        String child = idExam + "/" + idQues;
        databaseQuest_p2.child(child).setValue(clsPartP2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(EditListQuesP2Activity.this, "Đã lưu thay đổi thành công ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}