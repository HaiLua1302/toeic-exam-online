package com.example.user.ui.admin.fullExam;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.user.R;
import com.example.user.ui.admin.AdminHome;

public class AddNewAPart extends AppCompatActivity {

    private TextView txtNameExam,txtNamePart1,txtNamePart2,txtNamePart3,txtNamePart4,txtNamePart5,txtNamePart6,txtNamePart7;
    private ImageView imgRefresh,imgEditPart1,imgEditPart2,imgEditPart3,imgEditPart4,imgEditPart5,imgEditPart6,imgEditPart7;
    private Button btnAddNewExam;
    private String idExam,nameP1,nameP2,nameP3,nameP4,nameP5,nameP6,nameP7,idQues1,idQues2,idQues3,idQues4,idQues5,idQues6,idQues7;
    private Intent intent;
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
                intent = new Intent(AddNewAPart.this, ChooseAPart1.class);
                intent.putExtra("idExam",idExam);
                intent.putExtra("idPart",2);
                someActivityResultLauncher.launch(intent);
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

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.home_bar_admin:
                Intent intent2 = new Intent(AddNewAPart.this, AdminHome.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}