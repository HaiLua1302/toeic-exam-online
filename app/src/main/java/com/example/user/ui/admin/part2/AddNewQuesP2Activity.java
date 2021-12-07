package com.example.user.ui.admin.part2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.RecQuesP2Adapter;
import com.example.user.ui.admin.AdminHomeActivity;
import com.example.user.ui.classExam.ClsPartP2;
import com.example.user.ui.classExam.ClsRecExamP2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddNewQuesP2Activity extends AppCompatActivity {

    private Uri filePath_AUD;
    private final int PICK_AUDIO_REQUEST = 1;

    //view objects
    private EditText edtNameExam;
    private Button btnGetUrl,btnSaveAudio,btnIntentToAddQues;
    private RecyclerView recyclerView;
    private TextView txtFilePathAudio;
    private ImageView imgRefresh;

    private List<ClsRecExamP2> recExamP2List;
    private List<ClsPartP2> clsPartP2s;
    private String urlAud,idExam;


    private RecQuesP2Adapter RecQuesP2Adapter;

    //our database reference object
    private DatabaseReference databaseQuest_P2;
    //Firebase
    private FirebaseStorage storage;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_audio_p2);
        getSupportActionBar().setTitle("Add New Part 2");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //getting the reference of question part 1 node
        //databaseQuest_P2 = FirebaseDatabase.getInstance().getReference().child("Ques_P2");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        edtNameExam = findViewById(R.id.edtAddExamNameP2);
        btnGetUrl = findViewById(R.id.btnAddAudioP2);
        btnSaveAudio = findViewById(R.id.btnSaveAudioP2);
        btnIntentToAddQues = findViewById(R.id.btnIntentToAddQuesP2);
        txtFilePathAudio = findViewById(R.id.txtFilePathAudioP2);
        recyclerView = findViewById(R.id.recViewListQuestEditP2);;
        imgRefresh = findViewById(R.id.imgRefreshP2);;
        setDataToRecViewDefault();
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDataToRecView();
                setDataToRecViewDefault();

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
                checkDuplicate();
            }
        });

        btnIntentToAddQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatabyEdit();
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
            txtFilePathAudio.setText(filePath_AUD.toString());
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
    private void checkDuplicate(){
        String id_Exam = edtNameExam.getText().toString();
        databaseQuest_P2 = FirebaseDatabase.getInstance().getReference();
        Query query = databaseQuest_P2.child("Ques_2").orderByChild("id_exam").equalTo(id_Exam);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(AddNewQuesP2Activity.this, "Tên bộ đề đã có ",Toast.LENGTH_SHORT).show();
                }
                else {
                    saveAudio();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void addQues(String url_audio) {
        //getting the reference of question part 1 node
        idExam = edtNameExam.getText().toString();
        databaseQuest_P2 = FirebaseDatabase.getInstance().getReference().child("Ques_2");
        ClsRecExamP2 clsRecExamP2 = new ClsRecExamP2(idExam, url_audio);
        databaseQuest_P2.child(idExam).setValue(clsRecExamP2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddNewQuesP2Activity.this, "Thêm bộ đè thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddNewQuesP2Activity.this, AddNewListQuesP2Activity.class);
                    intent.putExtra("idExam",idExam);
                   /* intent.putExtra("urlAudio",url_audio);
                    intent.putExtra("count",1);*/
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AddNewQuesP2Activity.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
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
                                    addQues(urlAud);
                                    progressDialog_audio.dismiss();
                                    edtNameExam.setInputType(InputType.TYPE_NULL);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog_audio.dismiss();
                            Toast.makeText(AddNewQuesP2Activity.this, "Đã xảy ra lỗi vui lòng thử lại " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
        else {
            Toast.makeText(AddNewQuesP2Activity.this, "File Audio Chưa Được Chọn ", Toast.LENGTH_SHORT).show();
        }

    };

    private void getDatabyEdit()
    {
        String id_Exam = edtNameExam.getText().toString().trim();
        recExamP2List = new ArrayList<>();
        if (id_Exam.isEmpty()){
            edtNameExam.setError("Tên đề không được để trống!");
            edtNameExam.requestFocus();
            return;
        }
        Intent intent = new Intent(AddNewQuesP2Activity.this, AddNewListQuesP2Activity.class);
        intent.putExtra("idExam",id_Exam);
        startActivity(intent);
    }


    private void setDataToRecViewDefault(){
        String nameExam = edtNameExam.getText().toString().trim();
        if (nameExam.isEmpty()){
            return;
        }else{
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<ClsPartP2> options =
                new FirebaseRecyclerOptions.Builder<ClsPartP2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("List_Ques2").child(nameExam), ClsPartP2.class)
                        .build();

        RecQuesP2Adapter = new RecQuesP2Adapter(options,nameExam,this);
        recyclerView.setAdapter(RecQuesP2Adapter);
        RecQuesP2Adapter.startListening();}
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.nav_bottom_home:
                Intent intent2 = new Intent(AddNewQuesP2Activity.this, AdminHomeActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

}