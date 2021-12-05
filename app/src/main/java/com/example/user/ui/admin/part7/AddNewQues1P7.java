package com.example.user.ui.admin.part7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtAddNew1P7;
import com.example.user.ui.admin.AdminHome;
import com.example.user.ui.admin.ManagerExamActivity;
import com.example.user.ui.classExam.ClsRecExamP7;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class AddNewQues1P7 extends AppCompatActivity {

    private EditText edtNameExam,edtParagraph;
    private ImageView imgRefresh;
    private Button btnSaveParagraph,btnDelExam;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private String getExam,id_Exam;
    private Intent intent;

    private AdtAddNew1P7 adtAddNew1P7;
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
        btnDelExam = findViewById(R.id.btnDelExamP7);
        recyclerView = findViewById(R.id.recViewListQuest1P7);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        getExam = bundle.getString("idExam");
        edtNameExam.setText(getExam);
        id_Exam = edtNameExam.getText().toString();

        if (!getExam.isEmpty()){
            getSupportActionBar().setTitle("Edit Ques Part 6");
        }
        setDataToRecViewDefault();
        btnSaveParagraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQues();
            }
        });
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create id question
                setDataToRecViewDefault();
            }
        });
        btnDelExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewQues1P7.ViewDialog alert = new AddNewQues1P7.ViewDialog();
                alert.showDialog(AddNewQues1P7.this,"Bạn Có Chắc Là Muốn Xóa Nó Chứ ? ");
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
        String ID_Quest = "QuesP7_"+ getTime();
        ref = FirebaseDatabase.getInstance().getReference("Ques_7").child(idExam);
        ClsRecExamP7 clsRecExamP7 = new ClsRecExamP7(idExam,ID_Quest,paragraph);
        ref.child(ID_Quest).setValue(clsRecExamP7).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddNewQues1P7.this, "Thêm bộ đề thành công", Toast.LENGTH_SHORT).show();
                    progressDialog_audio.dismiss();
                    Intent intent = new Intent(AddNewQues1P7.this, AddNewQues2P7.class);
                    intent.putExtra("idExam",idExam);
                    intent.putExtra("idQues",ID_Quest);
                    intent.putExtra("paragraph",paragraph);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AddNewQues1P7.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setDataToRecViewDefault(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        id_Exam = edtNameExam.getText().toString();
        if (id_Exam.isEmpty()){
            return;
        }else {
            final ProgressDialog progressDialog = new ProgressDialog(AddNewQues1P7.this);
            progressDialog.setTitle("Wait...");
            progressDialog.show();
            //getting the reference of question part 1 node
            FirebaseRecyclerOptions<ClsRecExamP7> options =
                    new FirebaseRecyclerOptions.Builder<ClsRecExamP7>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("Ques_7").child(id_Exam), ClsRecExamP7.class)
                            .build();

            adtAddNew1P7 = new AdtAddNew1P7(options,id_Exam,this);
            recyclerView.setAdapter(adtAddNew1P7);
            progressDialog.dismiss();
            adtAddNew1P7.startListening();
        }
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
                Intent intent2 = new Intent(AddNewQues1P7.this, AdminHome.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
    private void delQuestion(){
        String getIdExam = edtNameExam.getText().toString();
        FirebaseDatabase.getInstance().getReference("List_Ques6")
                .child(getIdExam)
                .setValue(null)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        FirebaseDatabase.getInstance().getReference()
                                .child("Ques_6")
                                .child(getIdExam)
                                .setValue(null)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(AddNewQues1P7.this, "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AddNewQues1P7.this, ManagerExamActivity.class);
                                        startActivity(intent);
                                    }
                                });
                    }
                });
    }
    //show dialog login success message
    public class ViewDialog {
        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_delete);

            TextView text = (TextView) dialog.findViewById(R.id.txtTitleDel);
            text.setText(msg);

            Button dialogButtonYes = (Button) dialog.findViewById(R.id.btnYesDel);
            Button dialogButtonNo = (Button) dialog.findViewById(R.id.btnNoDel);
            dialogButtonYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delQuestion();
                    dialog.dismiss();
                }
            });
            dialogButtonNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}