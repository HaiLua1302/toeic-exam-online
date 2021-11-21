package com.example.user.ui.setting;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.R;
import com.example.user.ui.class_user.cls_user_info;
import com.example.user.ui.login.Login_user;
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
import com.google.firebase.storage.StorageReference;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class setting_user extends AppCompatActivity {
    public String selected;
    private int checkedItem;
    private int REQUEST_CODE = 1;
    private String CHECKEDITEM = "checked_item";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private double VERSION = 1.0;
    private FirebaseUser userInfo;
    private DatabaseReference reference;
    private StorageReference storageRef;
    private String userID;
    private TextView nameUser;
    private ImageView imageUser;

    Context context;
    Resources resources;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Setting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_user);
        //display name user on settings screen
        TextView nameUser = findViewById(R.id.txt_name_user);
        ImageView imageUser = findViewById(R.id.img_avata_user);
        userInfo = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = userInfo.getUid();

//        nameUser.setText(userInfo.getDisplayName());
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cls_user_info user = snapshot.getValue(cls_user_info.class);
                if(user!=null){
                    String name = user.name_user;
                    String image = user.avata_user;

                    nameUser.setText("Xin chao " +name+"!");
                    Glide.with(setting_user.this).load(image).into(imageUser);
                }
                else{
                    nameUser.setText("Xin chao!");
                    Glide.with(setting_user.this).load(getDrawable(R.drawable.a1)).into(imageUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(setting_user.this,"error", Toast.LENGTH_LONG).show();
            }
        });

        if(LocaleHelper.getLanguage(setting_user.this).equalsIgnoreCase("en"))
        {
            context = LocaleHelper.setLocale(setting_user.this,"en");
            resources =context.getResources();
            setTitle(resources.getString(R.string.app_name));
            checkedItem = 0;
        }else if(LocaleHelper.getLanguage(setting_user.this).equalsIgnoreCase("vi")){
            context = LocaleHelper.setLocale(setting_user.this,"hi");
            resources =context.getResources();
            setTitle(resources.getString(R.string.app_name));
            checkedItem =1;
        }
        //change themes
        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switch (getCheckedItem()) {
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
                showDialogSMS();
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
                changeLanguage();
            }
        });
        //navigation to screen user detail
        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(setting_user.this, information_user.class));
            }
        });
        Button version = findViewById(R.id.btn_version);
        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(setting_user.this,"V "+VERSION,Toast.LENGTH_LONG).show();
            }
        });
        Button achieviement = findViewById(R.id.btn_achievement);
        achieviement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(setting_user.this, achievement_user.class));
            }
        });
    }

    public void showChangeLayout() {
        final String[] themes = this.getResources().getStringArray(R.array.theme);
        AlertDialog.Builder builder = new AlertDialog.Builder(setting_user.this);
        builder.setTitle("Choose Layout");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected = themes[i];
                checkedItem = i;
                Toast.makeText(setting_user.this, "clicked " + selected, Toast.LENGTH_LONG).show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (selected == null) {
                    selected = themes[i];
                    checkedItem = i;
                } else {
                    switch (selected) {
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

    private int getCheckedItem() {
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }

    private void setCheckedItem(int i) {
        editor.putInt(CHECKEDITEM, i);
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

    private void changeLanguage(){
        String[] languages = this.getResources().getStringArray(R.array.language);
        AlertDialog.Builder builder = new AlertDialog.Builder(setting_user.this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(languages, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(languages[i].equals("English")){
                    context = LocaleHelper.setLocale(setting_user.this,"en");
                    resources = context.getResources();
                    checkedItem = 0;
                    Toast.makeText(setting_user.this,"clicked",Toast.LENGTH_LONG).show();
                }
                if(languages[i].equals("Vietnamese")){
                    context = LocaleHelper.setLocale(setting_user.this,"vi");
                    resources = context.getResources();
                    checkedItem = 1;
                    Toast.makeText(setting_user.this,"clicked",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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

    public void showDialogSMS() {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bottom);

        Button btn_sms = dialog.findViewById(R.id.btn_sms);
        LinearLayout layoutFbackEmail = dialog.findViewById(R.id.layoutFbackEmail);

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(setting_user.this, "Email is Clicked", Toast.LENGTH_LONG).show();
                sendSMS("","0334385803","test");
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkPermission(String permission) {
        boolean ok = false;
        int check = checkSelfPermission(permission);
        if(check == PackageManager.PERMISSION_GRANTED){
            ok = true;
        }
        return ok;
    }
    public static void sendSMS(String status, String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE) {
            if (permissions.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showDialogSMS();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
