package com.example.user.ui.setting;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.user.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Changelayout_user extends AppCompatActivity {
    public String selected;
    private int checkedItem;
    private String CHECKEDITEM ="checked_item";
    private  SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_user);
        sharedPreferences = this.getSharedPreferences("themes",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switch (getCheckedItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
        setCheckedItem(checkedItem);
        Button theme = findViewById(R.id.btn_theme);
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }





    public void showDialog(){
        final String[] themes = this.getResources().getStringArray(R.array.theme);
        AlertDialog.Builder builder =new AlertDialog.Builder(Changelayout_user.this);
        builder.setTitle("Choose language");
        builder.setSingleChoiceItems(themes, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected = themes[i];
                checkedItem = i;
                Toast.makeText(Changelayout_user.this,"clicked"+selected,Toast.LENGTH_LONG).show();
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
    public void show() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_changelayout);

        RadioGroup rdGr = findViewById(R.id.themeGroup);
        RadioButton rdBtnDf = findViewById(R.id.radioDefault);
        RadioButton rdBtnD = findViewById(R.id.radioDark);
        if (rdGr == null) {
            Toast.makeText(Changelayout_user.this, "null", Toast.LENGTH_SHORT).show();
        } else {
            rdGr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case R.id.radioDefault:
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            break;
                        case R.id.radioDark:
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            break;

                    }
                }
            });
            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }

    public void changeLayout(){
        final RadioGroup radioGroup = findViewById(R.id.themeGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // on radio button check change
                switch (checkedId) {
                    case R.id.radioDefault:
                        // on below line we are checking the radio button with id.
                        // on below line we are setting the text to text view as light mode.
                        // on below line we are changing the theme to light mode.
                        Toast.makeText(Changelayout_user.this,"clicked",Toast.LENGTH_LONG).show();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case R.id.radioDark:
                        // this method is called when dark radio button is selected
                        // on below line we are setting dark theme text to our text view.
                        // on below line we are changing the theme to dark mode.
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        Toast.makeText(Changelayout_user.this,"clicked",Toast.LENGTH_LONG).show();

                        break;
                }
            }
        });
    }

}
