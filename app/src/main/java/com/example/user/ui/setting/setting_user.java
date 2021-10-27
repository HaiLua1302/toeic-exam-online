package com.example.user.ui.setting;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class setting_user extends AppCompatActivity {
    public String selected;
    private int checkedItem;
    private String CHECKEDITEM ="checked_item";
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Setting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_user);

        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switch (getCheckedItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }

        //Rule
        Button rule = findViewById(R.id.btn_rule);
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setting_user.this, rule_user.class);
                startActivity(intent);
            }
        });
        //Tutorial
        Button tutorial = findViewById(R.id.btn_tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setting_user.this, tutorial_user.class);
                startActivity(intent);
            }
        });

        //Feedback
        Button feedback = findViewById(R.id.btn_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setting_user.this, feedback_user.class);
                startActivity(intent);
            }
        });

        Button theme = findViewById(R.id.btn_theme);
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLayout();
            }
        });
        Button language = findViewById(R.id.btn_language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguage();
            }
        });
    }


    public void showChangeLayout(){
        final String[] themes = this.getResources().getStringArray(R.array.theme);
        AlertDialog.Builder builder =new AlertDialog.Builder(setting_user.this);
        builder.setTitle("Choose Layout");
        builder.setSingleChoiceItems(themes, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected = themes[i];
                checkedItem = i;
                Toast.makeText(setting_user.this,"clicked"+selected,Toast.LENGTH_LONG).show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(selected == null){
                    selected = themes[i];
                    checkedItem = i;
                }else{
                    switch (selected){
                        case "Default":
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                            break;
                        case "Dark":
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            break;
                    }
                    setCheckedItem(checkedItem);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }
    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM,0);
    }
    private void setCheckedItem(int i){
        editor.putInt(CHECKEDITEM,i);
        editor.apply();
    }

    public void showChangeLanguage() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_changelanguage);

        RadioGroup rdGr = findViewById(R.id.groupLanguage);
        RadioButton rdBtnLanguageE = findViewById(R.id.radioLanguageE);
        RadioButton rdBtnLanguageV = findViewById(R.id.radioLanguageV);
        if (rdGr == null) {
            Toast.makeText(setting_user.this, "null", Toast.LENGTH_SHORT).show();
        } else {
        }
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

}
