package com.example.user.ui.admin.fullExam;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.admin.AdminHomeActivity;
import com.example.user.ui.admin.part5.AddNewAQuesP5Activity;
import com.example.user.ui.classExam.ClsRecExamFull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewAPartActivity extends AppCompatActivity {

    private TextView txtNameExam,txtNamePart1,txtNamePart2,txtNamePart3,txtNamePart4,txtNamePart5,txtNamePart6,txtNamePart7;
    private ImageView imgRefresh,imgEditPart1,imgEditPart2,imgEditPart3,imgEditPart4,imgEditPart5,imgEditPart6,imgEditPart7;
    private Button btnAddNewExam;
    private String idExam,nameP1,nameP2,nameP3,nameP4,nameP5,nameP6,nameP7,idQues1,idQues2,idQues3,idQues4,idQues5,idQues6,idQues7;
    private Intent intent;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_a_part);
        getSupportActionBar().setTitle("Choose Part Exam");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");
        nameP1 = bundle.getString("idExam1");
        nameP2 = bundle.getString("idExam2");
        nameP3 = bundle.getString("idExam3");
        nameP4 = bundle.getString("idExam4");
        nameP5 = bundle.getString("idExam5");
        nameP6 = bundle.getString("idExam6");
        nameP7 = bundle.getString("idExam7");

        idQues1 = bundle.getString("idQues1");
        idQues2 = bundle.getString("idQues2");
        idQues3 = bundle.getString("idQues3");
        idQues4 = bundle.getString("idQues4");
        idQues5 = bundle.getString("idQues5");
        idQues6 = bundle.getString("idQues6");
        idQues7 = bundle.getString("idQues7");


        txtNameExam = findViewById(R.id.txtNameExamFull);
        txtNamePart1 = findViewById(R.id.txtNameExam1Title);
        txtNamePart2 = findViewById(R.id.txtNameExam2Title);
        txtNamePart3 = findViewById(R.id.txtNameExam3Title);
        txtNamePart4 = findViewById(R.id.txtNameExam4Title);
        txtNamePart5 = findViewById(R.id.txtNameExam5Title);
        txtNamePart6 = findViewById(R.id.txtNameExam6Title);
        txtNamePart7 = findViewById(R.id.txtNameExam7Title);
        imgRefresh = findViewById(R.id.imgRefreshFull);

        imgEditPart1 = findViewById(R.id.imgEdit1Full);
        imgEditPart2 = findViewById(R.id.imgEdit2Full);
        imgEditPart3 = findViewById(R.id.imgEdit3Full);
        imgEditPart4 = findViewById(R.id.imgEdit4Full);
        imgEditPart5 = findViewById(R.id.imgEdit5Full);
        imgEditPart6 = findViewById(R.id.imgEdit6Full);
        imgEditPart7 = findViewById(R.id.imgEdit7Full);
        btnAddNewExam = findViewById(R.id.btnSaveFull);

        txtNameExam.setText(idExam);
        txtNamePart1.setText(nameP1);
        txtNamePart2.setText(nameP2);
        txtNamePart3.setText(nameP3);
        txtNamePart4.setText(nameP4);
        txtNamePart5.setText(nameP5);
        txtNamePart6.setText(nameP6);
        txtNamePart7.setText(nameP7);

        imgEditPart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddNewAPartActivity.this, ChooseAPart1Activity.class);
                intent.putExtra("idExam",idExam);
                someActivityResultLauncher.launch(intent);
            }
        });
        imgEditPart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddNewAPartActivity.this, ChooseAPart2Activity.class);
                intent.putExtra("idExam",idExam);
                someActivityResultLauncher2.launch(intent);
            }
        });
        imgEditPart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddNewAPartActivity.this, ChooseAPart3Activity.class);
                intent.putExtra("idExam",idExam);
                someActivityResultLauncher3.launch(intent);
            }
        });
        imgEditPart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddNewAPartActivity.this, ChooseAPart4Activity.class);
                intent.putExtra("idExam",idExam);
                someActivityResultLauncher4.launch(intent);
            }
        });
        imgEditPart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddNewAPartActivity.this, ChooseAPart5Activity.class);
                intent.putExtra("idExam",idExam);
                someActivityResultLauncher5.launch(intent);
            }
        });
        imgEditPart6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddNewAPartActivity.this, ChooseAPart6Activity.class);
                intent.putExtra("idExam",idExam);
                someActivityResultLauncher6.launch(intent);
            }
        });
        imgEditPart7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddNewAPartActivity.this, ChooseAPart7Activity.class);
                intent.putExtra("idExam",idExam);
                someActivityResultLauncher7.launch(intent);
            }
        });
        btnAddNewExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNew();
            }
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if(result.getData() != null){
                        String idPart = result.getData().getStringExtra("idPart");
                        txtNamePart1.setText(idPart);
                    }
                }
            });
    ActivityResultLauncher<Intent> someActivityResultLauncher2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if(result.getData() != null){
                        String idPart = result.getData().getStringExtra("idPart");
                        txtNamePart2.setText(idPart);
                    }
                }
            });
    ActivityResultLauncher<Intent> someActivityResultLauncher3 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if(result.getData() != null){
                        String idPart = result.getData().getStringExtra("idPart");
                        txtNamePart3.setText(idPart);
                    }
                }
            });
    ActivityResultLauncher<Intent> someActivityResultLauncher4 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if(result.getData() != null){
                        String idPart = result.getData().getStringExtra("idPart");
                        txtNamePart4.setText(idPart);
                    }
                }
            });
    ActivityResultLauncher<Intent> someActivityResultLauncher5 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if(result.getData() != null){
                        String idPart = result.getData().getStringExtra("idPart");
                        txtNamePart5.setText(idPart);
                    }
                }
            });
    ActivityResultLauncher<Intent> someActivityResultLauncher6 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if(result.getData() != null){
                        String idPart = result.getData().getStringExtra("idPart");
                        txtNamePart6.setText(idPart);
                    }
                }
            });
    ActivityResultLauncher<Intent> someActivityResultLauncher7 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if(result.getData() != null){
                        String idPart = result.getData().getStringExtra("idPart");
                        txtNamePart7.setText(idPart);
                    }
                }
            });

    private void saveNew(){
        nameP1 = txtNamePart1.getText().toString();
        nameP2 = txtNamePart2.getText().toString();
        nameP3 = txtNamePart3.getText().toString();
        nameP4 = txtNamePart4.getText().toString();
        nameP5 = txtNamePart5.getText().toString();
        nameP6 = txtNamePart6.getText().toString();
        nameP7 = txtNamePart7.getText().toString();
        if (nameP1.equals("...")||nameP2.equals("...")||nameP3.equals("...")||nameP4.equals("...")||nameP5.equals("...")||nameP6.equals("...")||nameP7.equals("...")){
            Toast.makeText(AddNewAPartActivity.this, "Lôi! Vui lòng kiểm tra lại các phần thi đã chọn!", Toast.LENGTH_SHORT).show();
        }else {
            ref = FirebaseDatabase.getInstance().getReference("Exam");
            ClsRecExamFull examFull = new ClsRecExamFull(idExam,nameP1,nameP2,nameP3,nameP4,nameP5,nameP6,nameP7);
            ref.child(idExam).setValue(examFull).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AddNewAPartActivity.this, "Thêm bộ đề thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddNewAPartActivity.this, "Thêm bộ đề thất bài vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                }
            });
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