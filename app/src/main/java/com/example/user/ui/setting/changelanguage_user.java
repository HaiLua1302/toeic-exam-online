package com.example.user.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.user.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class changelanguage_user extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button language = findViewById(R.id.btn_language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void dialogChangeLanguage(){
        LayoutInflater inflater = getLayoutInflater();
        View alert = inflater.inflate(R.layout.setting_user,null);
        CheckBox eng = alert.findViewById(R.id.btn_language);
    }
}
