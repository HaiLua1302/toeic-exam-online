package com.example.user.ui.admin.part6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AddNew2P6Adapter;
import com.example.user.ui.admin.ManagerExamActivity;
import com.example.user.ui.classExam.ClsListQuestionP6;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddNewQues2P6Activity extends AppCompatActivity {
    private TextView txtNameExam, txtNameQues, txtParagraph;
    private Button btnEditParagraph,btnAddNew,btnDelExam;
    private ImageView imgRefesh;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private String idExam,idQues,paragraph,child;
    private Intent intent;
    private AddNew2P6Adapter adtNewQues2P6;
    private List<ClsListQuestionP6>listQuestionP6s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_ques2_p6);
        getSupportActionBar().setTitle("Question Part 6 Manager");
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

        txtNameExam = findViewById(R.id.txtNameExam2P6);
        txtNameQues = findViewById(R.id.txtNameQues2P6);
        txtParagraph = findViewById(R.id.txtFilePathEditAudio2P6);
        btnEditParagraph = findViewById(R.id.btnEditAudio2P6);
        btnAddNew = findViewById(R.id.btnAddNewQues2P6);
        btnDelExam = findViewById(R.id.btnDelExam2P6);
        imgRefesh = findViewById(R.id.imgRefreshEdit2P6);
        recyclerView = findViewById(R.id.recViewListQuestEdit2P6);

        txtNameExam.setText(idExam);
        txtNameQues.setText(idQues);
        txtParagraph.setText(paragraph);
        setDataToRecViewDefault(idExam,idQues);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataAddAQues(idExam,idQues);
            }
        });
        btnEditParagraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataEditParagraph(idExam,idQues,paragraph);
            }
        });
        imgRefesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataToRecViewDefault(idExam,idQues);
                getParagraph();
            }
        });
        btnDelExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog dialog = new ViewDialog();
                dialog.showDialog(AddNewQues2P6Activity.this,"Bạn Có Chắc Là Muốn Xóa Nó Chứ ? ");
            }
        });
    }

    private void getParagraph(){
        child = idExam +"/"+idQues;
        ref = FirebaseDatabase.getInstance().getReference("Ques_6").child(child);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    paragraph = snapshot.child("paragraph").getValue().toString();
                    txtParagraph.setText(paragraph);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void sendDataAddAQues(String getIdExam,String getIdQues){
        intent = new Intent(AddNewQues2P6Activity.this, AddNewAQuesP6Activity.class);
        intent.putExtra("idExam",getIdExam);
        intent.putExtra("idQues",getIdQues);
        intent.putExtra("idQues",getIdQues);
        startActivity(intent);
    }
    private void sendDataEditParagraph(String getIdExam,String getIdQues,String getParagraph){
        intent = new Intent(AddNewQues2P6Activity.this, EditParagraphP6Activity.class);
        intent.putExtra("idExam",getIdExam);
        intent.putExtra("idQues",getIdQues);
        intent.putExtra("paragraph",getParagraph);
        startActivity(intent);
    }
    private void setDataToRecViewDefault(String getIdExam,String getIdQues){

        listQuestionP6s = new ArrayList<>();
        String child = getIdExam +"/"+getIdQues+"/Question";
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<ClsListQuestionP6> options =
                new FirebaseRecyclerOptions.Builder<ClsListQuestionP6>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("List_Ques6").child(child), ClsListQuestionP6.class)
                        .build();
        adtNewQues2P6 = new AddNew2P6Adapter(options,getIdExam,idQues,this);
        recyclerView.setAdapter(adtNewQues2P6);
        adtNewQues2P6.startListening();
        adtNewQues2P6.notifyDataSetChanged();
    }

    private void delQuestion(){
        String getIdExam = txtNameExam.getText().toString();
        String getIdQues = txtNameQues.getText().toString();
        String child = getIdExam +"/"+getIdQues;
        FirebaseDatabase.getInstance().getReference("List_Ques6")
                .child(child)
                .setValue(null)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        FirebaseDatabase.getInstance().getReference()
                                .child("Ques_6")
                                .child(child)
                                .setValue(null)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(AddNewQues2P6Activity.this, "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AddNewQues2P6Activity.this, ManagerExamActivity.class);
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