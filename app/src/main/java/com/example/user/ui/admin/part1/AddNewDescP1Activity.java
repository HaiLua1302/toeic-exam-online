package com.example.user.ui.admin.part1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.classExam.ClsPartP1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class AddNewDescP1Activity extends AppCompatActivity {

    public String idExam,urlAudio;
    private int countNumQues;

    private TextView txtxNameExam,txtnameFileIMG,txtNumQues;
    private EditText edtResult;
    private Button btnGetImg,btnSaveQuestion;
    private ImageView imgQues;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath_IMG;
    //our database reference object
    DatabaseReference databaseQuest_p1;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_desc_p1);
        getSupportActionBar().setTitle("Thêm câu hỏi");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");
        urlAudio = bundle.getString("urlAudio");
        countNumQues = bundle.getInt("count");

        txtxNameExam = findViewById(R.id.txtContentQuestionAdminTitleP1);
        txtnameFileIMG = findViewById(R.id.txtFilePathAudioP1);
        txtNumQues = findViewById(R.id.txtNumAddQuestP1);
        edtResult = findViewById(R.id.edtResultP1);
        btnGetImg = findViewById(R.id.btnGetImgP1);
        btnSaveQuestion = findViewById(R.id.btnAddAQuestionP1);
        imgQues = findViewById(R.id.imgViewP1);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        txtxNameExam.setText(idExam);
        txtNumQues.setText(String.valueOf(countNumQues));

        btnGetImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuesImage();
            }
        });
    }

    /*
     * This method is choose img to the
     * */
    private void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //noinspection deprecation
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath_IMG = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath_IMG);
                imgQues.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    };


    private void addListQues(int NumQues,String url_img){
        //getting the reference of question part 1 node
        databaseQuest_p1 = FirebaseDatabase.getInstance().getReference().child("List_Ques1");
        String Result = edtResult.getText().toString();
        ClsPartP1 clsPartP1 = new ClsPartP1(Result,url_img);
        String child = idExam + "/" + NumQues;
        databaseQuest_p1.child(child).setValue(clsPartP1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                }
            }
        });
    }

    private void saveQuesImage()
    {

        //create id question
        String ID_Quest = idExam + "_" + getTime();

        if(filePath_IMG != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            final ProgressDialog progressDialog_audio = new ProgressDialog(this);

            progressDialog.setTitle("Uploading Image...");
            progressDialog.show();

            StorageReference ref_IMG = storageReference.child("images_exam/"+  ID_Quest);
            ref_IMG.putFile(filePath_IMG)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref_IMG.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url_IMG = uri.toString();
                                    addListQues(countNumQues,url_IMG);
                                    progressDialog.dismiss();
                                    Toast.makeText(AddNewDescP1Activity.this, "Thêm câu hỏi "+countNumQues+" thành công ", Toast.LENGTH_SHORT).show();
                                    countNumQues++;
                                    txtNumQues.setText(String.valueOf(countNumQues));
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddNewDescP1Activity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
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

    /*
    gettime current
    * */
    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return  currentDateandTime;
    }
}