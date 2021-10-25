package com.example.user.ui.setting;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.R;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class changelanguage_user extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_user);
        Button language = findViewById(R.id.btn_language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
    }

//    public void dialogChangeLanguage() {
//        LayoutInflater inflater = getLayoutInflater();
//        View alert = inflater.inflate(R.layout.setting_user, null);
//        CheckBox eng = alert.findViewById(R.id.btn_language);
//    }

    public void show() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_changelanguage);

        RadioGroup rdGr = findViewById(R.id.groupLanguage);
        RadioButton rdBtnLanguageE = findViewById(R.id.radioLanguageE);
        RadioButton rdBtnLanguageV = findViewById(R.id.radioLanguageV);
        if (rdGr == null) {
            Toast.makeText(changelanguage_user.this, "null", Toast.LENGTH_SHORT).show();
        } else {
        }
            dialog.show();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setGravity(Gravity.CENTER);
    }
}
