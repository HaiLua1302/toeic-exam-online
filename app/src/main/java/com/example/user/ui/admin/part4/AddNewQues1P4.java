package com.example.user.ui.admin.part4;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtAddNew1P4;
import com.example.user.ui.admin.AdminHome;
import com.example.user.ui.admin.ManagerExamActivity;
import com.example.user.ui.classExam.ClsRecExamP4;
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

public class AddNewQues1P4 extends AppCompatActivity {
    private EditText edtNameExam;
    private ImageView imgRefresh;
    private Button btnGetUrl,btnSaveAudio;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private Uri filePath_AUD;
    private TextView txtFilePathAudio;
    private String urlAud,idExam;
    private final int PICK_AUDIO_REQUEST = 1;
    private AdtAddNew1P4 adtAddNew1P4;
    private List<ClsRecExamP4> clsRecExamP4s;
    //Firebase
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private String id_Exam,getExam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_ques1_p4);
        getSupportActionBar().setTitle("Add New Ques Part 4");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //getting the reference of question part 1 node
        //databaseQuest_P4 = FirebaseDatabase.getInstance().getReference().child("Ques_P4");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnGetUrl = findViewById(R.id.btnAddAudio1P4);
        txtFilePathAudio = findViewById(R.id.txtFilePathAudio1P4);
        btnSaveAudio = findViewById(R.id.btnSaveAudio1P4);
        edtNameExam = findViewById(R.id.edtAddExamName1P4);
        imgRefresh = findViewById(R.id.imgRefresh1P4);
        recyclerView = findViewById(R.id.recViewListQuest1P4);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        getExam = bundle.getString("idExam");
        edtNameExam.setText(getExam);
        id_Exam = edtNameExam.getText().toString();

        if (!getExam.isEmpty()){
            getSupportActionBar().setTitle("Edit Ques Part 4");
        }

        setDataToRecViewDefault();
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
        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataToRecViewDefault();
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
     * This method is saving a new quest to the
     * Firebase Realtime Database
     * */
    private void checkDuplicate(){
        ref = FirebaseDatabase.getInstance().getReference();
        Query query = ref.child("Ques_4").orderByChild("id_exam").equalTo(id_Exam);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    saveAudio();
                    Toast.makeText(AddNewQues1P4.this, "Thêm câu hỏi thành công ",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void addQues(String url_audio) {
        //create id question
        String nameExam = edtNameExam.getText().toString();
        String ID_Quest = "QuesP4"+ id_Exam +"_"+ getTime();
        ref = FirebaseDatabase.getInstance().getReference("Ques_4").child(nameExam);
        ClsRecExamP4 clsRecExamP4 = new ClsRecExamP4(nameExam, url_audio,ID_Quest);
        ref.child(ID_Quest).setValue(clsRecExamP4).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddNewQues1P4.this, "Thêm bộ đề thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddNewQues1P4.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void saveAudio(){
        //create id question
        String ID_Quest = "P4"+ id_Exam +"_"+ getTime();
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
                                    storage = FirebaseStorage.getInstance();
                                    storageReference = storage.getReference();
                                    storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(urlAud);
                                    String link = storageReference.getName();
                                    txtFilePathAudio.setText(link);
                                    setDataToRecViewDefault();
                                    progressDialog_audio.dismiss();
                                    /*Intent intent = new Intent(AddNewQues1P4.this, AddNewQues2P4.class);
                                    intent.putExtra("idExam",id_Exam);
                                    startActivity(intent);*/
                                    addQues(urlAud);

                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog_audio.dismiss();
                            Toast.makeText(AddNewQues1P4.this, "Đã xảy ra lỗi vui lòng thử lại " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(AddNewQues1P4.this, "File Audio Chưa Được Chọn ", Toast.LENGTH_SHORT).show();
        }

    };

    private void getDatabyEdit()
    {
        String id_Exam = edtNameExam.getText().toString().trim();
        clsRecExamP4s = new ArrayList<>();
        if (id_Exam.isEmpty()){
            edtNameExam.setError("Tên đề không được để trống!");
            edtNameExam.requestFocus();
            return;
        }
        Intent intent = new Intent(AddNewQues1P4.this, AddNewQues2P4.class);
        intent.putExtra("idExam",id_Exam);
        startActivity(intent);
    }


    private void setDataToRecViewDefault(){
        String nameExam = edtNameExam.getText().toString().trim();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (nameExam.isEmpty()){
            return;
        }else {
            //getting the reference of question part 1 node
            FirebaseRecyclerOptions<ClsRecExamP4> options =
                    new FirebaseRecyclerOptions.Builder<ClsRecExamP4>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("Ques_4").child(nameExam), ClsRecExamP4.class)
                            .build();

            adtAddNew1P4 = new AdtAddNew1P4(options,nameExam,this);
            recyclerView.setAdapter(adtAddNew1P4);
            adtAddNew1P4.startListening();
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
                Intent intent = new Intent(AddNewQues1P4.this, ManagerExamActivity.class);
                startActivity(intent);
            case R.id.home_bar_admin:
                Intent intent2 = new Intent(AddNewQues1P4.this, AdminHome.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}