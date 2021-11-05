package com.example.user.ui.setting;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.example.user.R;
import com.example.user.ui.class_user.cls_user_info;
import com.example.user.ui.login.Login_user;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class information_user extends AppCompatActivity {

    TextView name, dOB, phone, email;
    ImageView image;
    Button logout, updated, getImage;

    private FirebaseUser userInfo;
    private DatabaseReference reference;
    private String userID;

    private Uri filePath;
    private String myUri = "";
    private UploadTask uploadTask;
    private int PICK_IMAGE_REQUEST = 22;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    List<cls_user_info> clsUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("User Detail");
        setContentView(R.layout.infor_user_detail);

        storageReference = FirebaseStorage.getInstance().getReference().child("picture");
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //information for user
        name = findViewById(R.id.txt_name_user_detail);
        dOB = findViewById(R.id.txt_name_user_DoB);
        phone = findViewById(R.id.txt_name_user_numP);
        email = findViewById(R.id.txt_name_user_email);
        image = findViewById(R.id.img_avata_user_detail);
        //button
        logout = findViewById(R.id.btn_logout_user);
        updated = findViewById(R.id.btn_edit_user);
        getImage = findViewById(R.id.btn_get_image);

        //get information of user

        clsUser = new ArrayList<>();
        userInfo = FirebaseAuth.getInstance().getCurrentUser();
        userID = userInfo.getUid();
//        if(dOB == null) {
//            dOB.setVisibility(View.GONE);
//        }else{
//            dOB.setVisibility(View.VISIBLE);
//            dOB.setText("1");
//        }
        reference = FirebaseDatabase.getInstance().getReference("User");
        storageReference.child(userID);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cls_user_info user = snapshot.getValue(cls_user_info.class);
                if (user == null) {
                    return;
                }
                String nameUser = user.name_user;
                String emailUser = user.mail_user;
                String dobUser = user.dob_user;
                String phoneUser = user.numberP_user;
                String imageUser = user.avata_user;

                name.setText(nameUser);
                dOB.setText(dobUser);
                phone.setText(phoneUser);
                email.setText(emailUser);
                Glide.with(information_user.this).load(imageUser).into(image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(information_user.this, "error", Toast.LENGTH_LONG).show();
            }
        });


        //logout user
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
        //updated user
        updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
                //uploadProFileImage();
            }
        });
        //change image
        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }
    //sign out user
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(information_user.this, "success", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(information_user.this, Login_user.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //update infor user
    private void updateUser() {
        if (filePath != null) {
            StorageReference fileRef = storageReference.child(userID);
            uploadTask = fileRef.putFile(filePath);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){

                        String nameUserUpdate = name.getText().toString();
                        String dobUserUpdate = dOB.getText().toString();
                        String phoneUserUpdate = phone.getText().toString();
                        Uri downloadUri = (Uri) task.getResult();
                        myUri = downloadUri.toString();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("avata_user",myUri);
                        map.put("id_user", userID);
                        map.put("name_user", nameUserUpdate);
                        map.put("dob_user", dobUserUpdate);
                        map.put("numberP_user", phoneUserUpdate);
                        reference = FirebaseDatabase.getInstance().getReference("User").child(userID);
                        reference.updateChildren(map);

                    }
                }
            });
        }
        if(filePath == null){
            String nameUserUpdate = name.getText().toString();
            String dobUserUpdate = dOB.getText().toString();
            String phoneUserUpdate = phone.getText().toString();
            //String emailUserUpdate = email.getText().toString();
            HashMap<String, Object> map = new HashMap<>();
            map.put("id_user", userID);
            map.put("name_user", nameUserUpdate);
            map.put("dob_user", dobUserUpdate);
            map.put("numberP_user", phoneUserUpdate);
            reference = FirebaseDatabase.getInstance().getReference("User").child(userID);
            reference.updateChildren(map);
        }else{
            Toast.makeText(information_user.this,"error",Toast.LENGTH_LONG).show();
        }
    }

    private void selectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            Glide.with(information_user.this).load(filePath).into(image);
            image.setImageURI(filePath);
            uploadPic();
        }
    }
    //upload image to storage with id user
    private void uploadPic(){
        userInfo = FirebaseAuth.getInstance().getCurrentUser();
        userID = userInfo.getUid();
        StorageReference refStore = storageReference.child(userID);
        refStore.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    Toast.makeText(information_user.this,"picked",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //add url image to realtime datbase
    private void uploadProFileImage(){
        if(filePath != null){
            StorageReference fileRef = storageReference.child(userID);
            uploadTask = fileRef.putFile(filePath);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = (Uri) task.getResult();
                        myUri = downloadUri.toString();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("avata_user",myUri);

                        reference = FirebaseDatabase.getInstance().getReference("User").child(userID);
                        reference.updateChildren(map);

                    }
                }
            });
        }
        else{
            Toast.makeText(information_user.this,"error",Toast.LENGTH_LONG).show();
        }
    }
    private String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}
