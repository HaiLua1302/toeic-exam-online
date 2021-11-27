package com.example.user.ui.admin.part1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.adtRecViewQuestionP1;
import com.example.user.ui.classExam.ClsPartP1;
import com.example.user.ui.classExam.ClsRecExamP1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddNewAudioP1Activity extends AppCompatActivity {

    private Uri filePath_AUD;
    private final int PICK_AUDIO_REQUEST = 1;
    //view objects
    private EditText edtNameExam;
    private Button btnGetUrl,btnSaveAudio,btnIntentToAddQues;
    private RecyclerView recyclerView;
    private TextView txtFilePathAudio;
    private ImageView imgRefresh;

    private List<ClsRecExamP1> recExamP1List;
    private List<ClsPartP1> clsPartP1s;
    private String urlAud,idExam;

    private com.example.user.ui.adapterAdmin.adtRecViewQuestionP1 adtRecViewQuestionP1;

    //our database reference object
    private DatabaseReference databaseQuest_p1;
    //Firebase
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_question_p1);

        //getting the reference of question part 1 node
        databaseQuest_p1 = FirebaseDatabase.getInstance().getReference().child("Ques_P1");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        edtNameExam = findViewById(R.id.edtAddExamNameP1);
        btnGetUrl = findViewById(R.id.btnAddAudioP1);
        btnSaveAudio = findViewById(R.id.btnSaveAudioP1);
        btnIntentToAddQues = findViewById(R.id.btnIntentToAddQuesP1);
        txtFilePathAudio = findViewById(R.id.txtFilePathAudio1);
        recyclerView = findViewById(R.id.recViewListQuestEdit);;
        imgRefresh = findViewById(R.id.imgRefreshP1);;

        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataToRecView();

            }
        });

        btnGetUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAudio();
            }
        });
        btnSaveAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAudio();
            }
        });

        btnIntentToAddQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatabyEdit();
                Toast.makeText(AddNewAudioP1Activity.this, "Cập nhật dữ liệu thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*
     * This method is choose img to the
     * */
    private void chooseAudio(){
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //noinspection deprecation
        startActivityForResult(Intent.createChooser(intent, "Select Audio"), PICK_AUDIO_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath_AUD = data.getData();
        }
    };
    /*
     gettime current
     * */
    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return  currentDateandTime;
    }
    /*
     * This method is saving a new quest to the
     * Firebase Realtime Database
     * */
    private void addQues(String url_Audio) {
        //getting the reference of question part 1 node
        String id_Exam = edtNameExam.getText().toString();
        databaseQuest_p1 = FirebaseDatabase.getInstance().getReference().child("Ques_P1");
        ClsRecExamP1 clsRecExamP1 = new ClsRecExamP1(id_Exam, url_Audio);
        databaseQuest_p1.child(id_Exam).setValue(clsRecExamP1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                }
            }
        });
    }
    private void saveAudio(){
        //getting the values to save
        String id_quest = edtNameExam.getText().toString().trim();

        //create id question
        String ID_Quest = id_quest +"_"+ getTime();
            //add audio
        final ProgressDialog progressDialog_audio = new ProgressDialog(this);
            if(filePath_AUD != null) {
                progressDialog_audio.setTitle("Uploading Audio...");
                progressDialog_audio.show();
                StorageReference ref_AUD = storageReference.child("audio_exam/" + ID_Quest);
                ref_AUD.putFile(filePath_AUD)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                //creating an question Object
                                ref_AUD.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri_AUD) {
                                        urlAud = uri_AUD.toString();
                                        idExam = edtNameExam.getText().toString();
                                        //cls_part_1 quest = new cls_part_1("",ID_Quest, result_quest, url_IMG,url_AUD,"");
                                        //Saving the question
                                        // databaseQuest_p1.child(ID_Quest).setValue(quest);
                                        addQues(urlAud);
                                        progressDialog_audio.dismiss();
                                        Toast.makeText(AddNewAudioP1Activity.this, "Thêm file audio thành công", Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(AddNewP1Activity.this, url_AUD, Toast.LENGTH_SHORT).show();
                                        edtNameExam.setInputType(InputType.TYPE_NULL);
                                        Intent intent = new Intent(AddNewAudioP1Activity.this, AddNewDescP1Activity.class);
                                        intent.putExtra("idExam",idExam);
                                        intent.putExtra("urlAudio",urlAud);
                                        intent.putExtra("count",1);
                                        startActivity(intent);
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog_audio.dismiss();
                                Toast.makeText(AddNewAudioP1Activity.this, "Đã xảy ra lỗi vui lòng thử lại " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                        .getTotalByteCount());
                                progressDialog_audio.setMessage("Uploaded " + (int) progress + "%");
                            }
                        });
            }

    };

    private void getDatabyEdit()
    {
        String nameExam = edtNameExam.getText().toString().trim();

        recExamP1List = new ArrayList<>();
        if (nameExam.isEmpty()){
            edtNameExam.setError("Tên đề không được để trống!");
            edtNameExam.requestFocus();
            return;
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ques_1").child(nameExam);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    idExam = snapshot.child("id_exam").getValue().toString();
                    urlAud = snapshot.child("url_audio").getValue().toString();

                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("List_Ques1").child(idExam);
                    ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                long count = snapshot.getChildrenCount();
                                Intent intent = new Intent(AddNewAudioP1Activity.this,AddNewDescP1Activity.class);
                                intent.putExtra("idExam",idExam);
                                intent.putExtra("urlAudio",urlAud);
                                intent.putExtra("count",(int)count);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });


                }
                else {
                    edtNameExam.setError("Tên đề không không hợp lệ!");
                    edtNameExam.requestFocus();
                    return;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setDataToRecView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clsPartP1s = new ArrayList<>();

        String nameExam = edtNameExam.getText().toString().trim();
        if (nameExam.isEmpty()){
            edtNameExam.setError("Tên đề không được để trống!");
            edtNameExam.requestFocus();
            return;
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Ques1").child(nameExam);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ClsPartP1 clsPartP1 = dataSnapshot.getValue(ClsPartP1.class);
                        clsPartP1s.add(clsPartP1);
                    }
                    adtRecViewQuestionP1 = new adtRecViewQuestionP1(clsPartP1s);
                    recyclerView.setAdapter(adtRecViewQuestionP1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}