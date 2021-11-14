package com.example.user.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.class_exam.ClsPartP1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class add_ques_p1 extends AppCompatActivity {

    private Uri filePath_IMG;
    private Uri filePath_AUD;
    private final int PICK_IMAGE_REQUEST = 22;
    private final int PICK_AUDIO_REQUEST = 1;

    //view objects
    EditText edtID,edtResult;
    Button btn_getIMG,btn_getAUD,btn_addP1;
    ImageView img_quest_p1;


    //a list to store all the Question from firebase database
    List<ClsPartP1> clsQues_p1;

    //our database reference object
    DatabaseReference databaseQuest_p1;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_part_1);

        //getting the reference of question part 1 node
        databaseQuest_p1 = FirebaseDatabase.getInstance().getReference().child("Ques_P1");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //getting views
        edtID = findViewById(R.id.edt_add_id_p1);
        edtResult = findViewById(R.id.edt_add_result_p1);
        img_quest_p1 = findViewById(R.id.img_upload_img_p1);

        btn_addP1 = findViewById(R.id.btn_add_p1);
        btn_getIMG = findViewById(R.id.btn_add_img_p1);
        btn_getAUD = findViewById(R.id.btn_add_aud_p1);

        //list to store question part 1
        clsQues_p1 = new ArrayList<>();

        //adding an onclicklistener to button
        btn_addP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQues_p1();
                //uploadImage();
            }
        });
        btn_getIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btn_getAUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAudio();
            }
        });
    };
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
                img_quest_p1.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if(requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath_AUD = data.getData();
        }
    };

    /*private void uploadImage() {


    };*/
    /*
     * This method is saving a new quest to the
     * Firebase Realtime Database
     * */
    public void addQues_p1(){
        //getting the values to save
        String id_quest = edtID.getText().toString().trim();
        String result_quest = edtResult.getText().toString().trim();



        //create id question
        String ID_Quest = id_quest + getTime();

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
                                    progressDialog.dismiss();
                                    //add audio
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
                                                                String url_AUD = uri_AUD.toString();

                                                               //cls_part_1 quest = new cls_part_1("",ID_Quest, result_quest, url_IMG,url_AUD,"");
                                                                //Saving the question
                                                               // databaseQuest_p1.child(ID_Quest).setValue(quest);
                                                                progressDialog_audio.dismiss();
                                                                Toast.makeText(add_ques_p1.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        progressDialog.dismiss();
                                                        Toast.makeText(add_ques_p1.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                                                .getTotalByteCount());
                                                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                                                    }
                                                });
                                    }
                                   // else {cls_part_1 quest = new cls_part_1("",ID_Quest, result_quest, url_IMG,"","");
                                   // databaseQuest_p1.child(ID_Quest).setValue(quest);}
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(add_ques_p1.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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

    };

    /*
     gettime current
     * */
    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return  currentDateandTime;
    }
}