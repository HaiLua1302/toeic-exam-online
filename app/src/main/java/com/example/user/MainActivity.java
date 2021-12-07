package com.example.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.ui.classExam.ClsPartP1;

import com.example.user.ui.home.UserHome;
import com.example.user.ui.login.Login_user;
import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    //our database reference object
    DatabaseReference database_P1;
    //a list to store all the artist from firebase database
    List<ClsPartP1> lsQues_p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.begin_user);

        Button btn_login = findViewById(R.id.btn_batdau);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login_user.class);
                startActivity(intent);
            }
        });

    }
}