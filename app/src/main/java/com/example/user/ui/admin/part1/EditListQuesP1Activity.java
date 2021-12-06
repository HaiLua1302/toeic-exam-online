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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class EditListQuesP1Activity extends AppCompatActivity {

    private String urlImg, result, idExam ,idQues;
    private int iNumQues;

    private TextView txtNameExam,txtnameFileIMG,txtNumQues;
    private EditText edtResult;
    private Button btnGetImg, btnSaveEdit;
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
        setContentView(R.layout.activity_edit_a_question_p1);
        getSupportActionBar().setTitle("Edit Desc Quesion");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        txtNameExam = findViewById(R.id.txtContentQuestionEditAdminTitleP1);
        txtNumQues = findViewById(R.id.txtNumEditAdminP1);
        edtResult = findViewById(R.id.edtEditResultP1);
        txtnameFileIMG = findViewById(R.id.txtFilePathAudioEditAdmin1);

        btnGetImg = findViewById(R.id.btnGetImgEditAdminP1);
        btnSaveEdit = findViewById(R.id.btnSaveEditAQuestionP1);
        imgQues = findViewById(R.id.imgViewEditAdminP1);

        idExam = bundle.getString("idExam");
        iNumQues = bundle.getInt("numQues");
        result = bundle.getString("result");
        urlImg = bundle.getString("urlImg");
        idQues = bundle.getString("idQues");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        txtNameExam.setText(idExam);
        txtNumQues.setText(String.valueOf(iNumQues));
        Picasso.get().load(urlImg).into(imgQues);
        edtResult.setText(result);
        getNameAudioFromUrl();

        btnGetImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveEditQues();
            }
        });

    }

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
            txtnameFileIMG.setText(filePath_IMG.toString());
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



    private void editAQuesImg(String url_img){
        //getting the reference of question part 1 node
        databaseQuest_p1 = FirebaseDatabase.getInstance().getReference().child("List_Ques1");
        String Result = edtResult.getText().toString();
        ClsPartP1 clsPartP1 = new ClsPartP1(Result,url_img,idQues);
        String child = idExam + "/" + idQues;
        databaseQuest_p1.child(child).setValue(clsPartP1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                }
            }
        });
    }

    private void editAQues(){
        //getting the reference of question part 1 node
        databaseQuest_p1 = FirebaseDatabase.getInstance().getReference().child("List_Ques1");
        String Result = edtResult.getText().toString();
        ClsPartP1 clsPartP1 = new ClsPartP1(Result,urlImg,idQues);
        String child = idExam + "/" + idQues;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Edit Question...");
        progressDialog.show();
        databaseQuest_p1.child(child).setValue(clsPartP1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(EditListQuesP1Activity.this, "Đã lưu thay đổi thành công ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveEditQues()
    {

        //create id question
        String ID_Quest = "Exam1" + "_" + getTime();
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(urlImg);
        String link = storageReference.getName();

        StorageReference removeImg = storage.getReference("images_exam").child(link);
        removeImg.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        if(filePath_IMG != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);

            progressDialog.setTitle("Edit Question...");
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
                                    editAQuesImg(url_IMG);
                                    progressDialog.dismiss();
                                    Toast.makeText(EditListQuesP1Activity.this, "Đã lưu thay đổi thành công ", Toast.LENGTH_SHORT).show();
                                    txtnameFileIMG.setText(String.valueOf(url_IMG));
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(EditListQuesP1Activity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        else {
            editAQues();
        }
    }

    private void getNameAudioFromUrl(){
       DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Ques1/"+idExam).child(idQues);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                urlImg = snapshot.child("url_img").getValue().toString();
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(urlImg);
                String nameImg = storageReference.getName();
                txtnameFileIMG.setText(nameImg);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

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
        }
        return super.onOptionsItemSelected(item);
    };
}