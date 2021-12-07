package com.example.user.ui.admin.part7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditParagraphP7Activity extends AppCompatActivity {
    private TextView txtIdExam,txtIdQues;
    private EditText edtParagraph;
    private Button btnSaveEdit;
    private Intent intent;
    private String idExam,idQues,paragraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_paragraph_p7);
        getSupportActionBar().setTitle("Edit Question Part 7");
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

        txtIdExam = findViewById(R.id.txtNameExamQuesEditP7);
        txtIdQues = findViewById(R.id.txtNameQuesEditP7);
        edtParagraph = findViewById(R.id.edtQuesContentEditP7);
        btnSaveEdit = findViewById(R.id.btnAddAQuestionEditP7);

        txtIdExam.setText(idExam);
        txtIdQues.setText(idQues);
        edtParagraph.setText(paragraph);

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateParagraph(idExam,idQues);
            }
        });
    }
    private void updateParagraph(String getIdExam,String getIdQues){
        String ContentQues = edtParagraph.getText().toString();
        String child = getIdExam +"/"+getIdQues;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating...");
        progressDialog.show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ques_7").child(child);
        ref.child("paragraph").setValue(ContentQues).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EditParagraphP7Activity.this, "Sửa thành công!!!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditParagraphP7Activity.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
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