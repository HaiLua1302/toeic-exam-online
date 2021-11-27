package com.example.user.ui.admin.part1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.adtRecViewQuestionP1;
import com.example.user.ui.classExam.ClsPartP1;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DescExamEditP1 extends AppCompatActivity {

    private TextView nameExam,numQues,path,timeCurrent,timeTotal;
    private ImageView imgRefesh,imgPlayPause;
    private SeekBar seekBar;
    private RecyclerView recyclerView;
    private Button getFileAudio,saveFileAudio,delExam;

    private List<ClsPartP1> clsPartP1List;
    private adtRecViewQuestionP1 adtRecViewQuetionP1;

    private String idExam;
    private int iNumQues = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_exam_edit_p1);
        getSupportActionBar().setTitle("Edit Exam");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");
        iNumQues = bundle.getInt("numQues");

        nameExam = findViewById(R.id.txtNameExamEditP1);
        //numQues = findViewById(R.id.txtNumEditQuestP1);
        path = findViewById(R.id.txtFilePathEditAudio1);
        timeCurrent = findViewById(R.id.txtCurrentTimeEditP1);
        timeTotal = findViewById(R.id.txtTimeTotalEditP1);

        imgRefesh = findViewById(R.id.imgRefreshEditP1);
        imgPlayPause = findViewById(R.id.imgPlayAudEditP1);

        seekBar = findViewById(R.id.playerSeekBarEditP1);

        recyclerView = findViewById(R.id.recViewListQuestEditP1);

        getFileAudio = findViewById(R.id.btnEditAudioP1);
        saveFileAudio = findViewById(R.id.btnSaveEditAudioP1);
        delExam = findViewById(R.id.btnDelExamP1);

        nameExam.setText(idExam);
        //numQues.setText(String.valueOf(iNumQues));
    }

    private void setDataRec(){
        clsPartP1List = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Part1").child(idExam);
    }
}