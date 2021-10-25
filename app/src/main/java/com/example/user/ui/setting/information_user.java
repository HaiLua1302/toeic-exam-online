package com.example.user.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.R;
import com.example.user.ui.class_user.cls_user_info;
import com.example.user.ui.login.Login_user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class information_user extends AppCompatActivity {

    TextView name_user, dOB, phone, email;
    ImageView imageUser;
    Button logout, updated, getImage;
    FirebaseUser userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_user);
//        cls_user_info user_info = new cls_user_info();
//        user_info.name_user = findViewById(R.id.txt_name_user_detail);
        //information for user
        name_user = findViewById(R.id.txt_name_user_detail);
        dOB = findViewById(R.id.txt_name_user_DoB);
        phone = findViewById(R.id.txt_name_user_numP);
        email = findViewById(R.id.txt_name_user_email);
        imageUser = findViewById(R.id.img_avata_user_detail);
        //button
        logout = findViewById(R.id.btn_logout_user);
        updated = findViewById(R.id.btn_edit_user);
        getImage = findViewById(R.id.btn_get_image);

        //get information of user
        //dOB.setText(userInfo.get);
        userInfo = FirebaseAuth.getInstance().getCurrentUser();
        name_user.setText(userInfo.getDisplayName());
        phone.setText(userInfo.getPhoneNumber());
        email.setText(userInfo.getEmail());
        Glide.with(information_user.this).load(userInfo.getPhotoUrl()).into(imageUser);

        //logout user
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(information_user.this, Login_user.class));
                finish();
            }
        });

        updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(information_user.this, update_information_user.class
                ));
            }
        });

    }
}
