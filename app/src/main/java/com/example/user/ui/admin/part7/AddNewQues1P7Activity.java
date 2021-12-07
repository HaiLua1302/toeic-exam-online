package com.example.user.ui.admin.part7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AddNew1P7Adapter;
import com.example.user.ui.admin.AdminHomeActivity;
import com.example.user.ui.classExam.ClsRecExamP7;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class AddNewQues1P7Activity extends AppCompatActivity {

    private EditText edtNameExam, edtParagraph;
    private ImageView imgRefresh;
    private Button btnSaveParagraph,btnClear;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private String getExam, id_Exam;
    private Intent intent;

    private AddNew1P7Adapter addNew1P7Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_ques1_p7);
        getSupportActionBar().setTitle("Add New Ques Part 7");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        edtNameExam = findViewById(R.id.edtAddExamName1P7);
        edtParagraph = findViewById(R.id.edtAddParagraphP7);
        imgRefresh = findViewById(R.id.imgRefresh1P7);
        btnSaveParagraph = findViewById(R.id.btnAddParagraph1P7);
        btnClear = findViewById(R.id.btnClear1P7);
        recyclerView = findViewById(R.id.recViewListQuest1P7);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        getExam = bundle.getString("idExam");
        edtNameExam.setText(getExam);
        id_Exam = edtNameExam.getText().toString();

        if (!getExam.isEmpty()) {
            getSupportActionBar().setTitle("Edit Ques Part 6");
        }
        setDataToRecViewDefault();
        btnSaveParagraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtParagraph.getText().toString().equals("")){
                    addQues();
                }else {
                    Toast.makeText(AddNewQues1P7Activity.this, "Chưa nhập văn bản!!!", Toast.LENGTH_SHORT).show();
                    edtParagraph.setError("Empty");
                    edtParagraph.requestFocus();
                }
            }
        });
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create id question
                setDataToRecViewDefault();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtParagraph.setText("");
            }
        });

    }

    private void addQues() {
        //create id question
        final ProgressDialog progressDialog_audio = new ProgressDialog(this);
        progressDialog_audio.setTitle("Save Exam...");
        progressDialog_audio.show();
        String paragraph = edtParagraph.getText().toString();
        String idExam = edtNameExam.getText().toString();
        String ID_Quest = "QuesP7_" + getTime();
        ref = FirebaseDatabase.getInstance().getReference("Ques_7").child(idExam);
        ClsRecExamP7 clsRecExamP7 = new ClsRecExamP7(idExam, ID_Quest, paragraph);
        ref.child(ID_Quest).setValue(clsRecExamP7).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddNewQues1P7Activity.this, "Thêm bộ đề thành công", Toast.LENGTH_SHORT).show();
                    progressDialog_audio.dismiss();
                    Intent intent = new Intent(AddNewQues1P7Activity.this, AddNewQues2P7Activity.class);
                    intent.putExtra("idExam", idExam);
                    intent.putExtra("idQues", ID_Quest);
                    intent.putExtra("paragraph", paragraph);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddNewQues1P7Activity.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setDataToRecViewDefault() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        id_Exam = edtNameExam.getText().toString();
        if (id_Exam.isEmpty()) {
            return;
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(AddNewQues1P7Activity.this);
            progressDialog.setTitle("Wait...");
            progressDialog.show();
            //getting the reference of question part 1 node
            FirebaseRecyclerOptions<ClsRecExamP7> options =
                    new FirebaseRecyclerOptions.Builder<ClsRecExamP7>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("Ques_7").child(id_Exam), ClsRecExamP7.class)
                            .build();

            addNew1P7Adapter = new AddNew1P7Adapter(options, id_Exam, this);
            recyclerView.setAdapter(addNew1P7Adapter);
            progressDialog.dismiss();
            addNew1P7Adapter.startListening();
            addNew1P7Adapter.notifyDataSetChanged();
        }
    }

    /*
   gettime current
   * */
    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.nav_bottom_home:
                Intent intent2 = new Intent(AddNewQues1P7Activity.this, AdminHomeActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ;
}