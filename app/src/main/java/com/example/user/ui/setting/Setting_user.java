package com.example.user.ui.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Setting_user extends AppCompatActivity {
    public String selected = "Male";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_user);
        Button achie = findViewById(R.id.btn_achievement);
        achie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_user.this, Achievement_user.class);
                startActivity(intent);
            }
        });


        Button changelayout = findViewById(R.id.btn_theme);
        changelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_user.this, Changelayout_user.class);
                startActivity(intent);
            }
        });
        Button changeLanguage = findViewById(R.id.btn_language);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Setting_user.this, Changelanguage_user.class));
                finish();
            }
        });

        //Rule
        Button rule = findViewById(R.id.btn_rule);
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_user.this, Rule_user.class);
                startActivity(intent);
            }
        });
        //Tutorial
        Button tutorial = findViewById(R.id.btn_tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_user.this, Tutorial_user.class);
                startActivity(intent);
            }
        });

        //Feedback
        Button feedback = findViewById(R.id.btn_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_user.this, Feedback_user.class);
                startActivity(intent);
            }
        });

        Button theme = findViewById(R.id.btn_theme);
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    public void showDialog(){
        final String[] genders = {"Male", "Female"};
        AlertDialog.Builder builder =new AlertDialog.Builder(Setting_user.this);
        builder.setTitle("Choose language");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected = genders[i];
                Toast.makeText(Setting_user.this,"clicked"+selected,Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

}
