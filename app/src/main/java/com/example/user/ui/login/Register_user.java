package com.example.user.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.class_user.cls_user_info;
import com.example.user.ui.home.Main_home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;

import okhttp3.Cache;

public class Register_user extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public EditText user_name, email_user, pass_user, check_pass_again;
    public Button register;
    public CheckBox ckb_rules;
    public ProgressBar process_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);
        getSupportActionBar().setTitle("Đăng ký tài khoản");

        mAuth = FirebaseAuth.getInstance();

        user_name = findViewById(R.id.edt_user_name);
        email_user = findViewById(R.id.edt_email_reg);
        pass_user = findViewById(R.id.edt_pass_reg);
        check_pass_again = findViewById(R.id.edt_pass_re_reg);

        process_loading = findViewById(R.id.progressBar_loading);
        process_loading.setVisibility(View.INVISIBLE);

        //event click txt login
        TextView txt_login = findViewById(R.id.txt_login);
        txt_login.setPaintFlags(txt_login.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_user.this, Login_user.class);
                startActivity(intent);

            }
        });

        //event click txt skip
        TextView txt_skip_reg = findViewById(R.id.txt_skip_reg);
        txt_skip_reg.setPaintFlags(txt_skip_reg.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_skip_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_user.this, Main_home.class);
                startActivity(intent);

            }
        });

        //event click checkbox rules
        ckb_rules = findViewById(R.id.ckb__rules);
        ckb_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_rules();
            }
        });

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //event btn register
        register = findViewById(R.id.btn_reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register_user();
            }
        });

    };

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

    //show dialog rules
    public void ViewDialog_rules() {
        LayoutInflater inflater = LayoutInflater.from(Register_user.this);
        View view = inflater.inflate(R.layout.dialog_rules, null);
        TextView txt_dialog = view.findViewById(R.id.txt_dialog_rules);
        txt_dialog.setMovementMethod(new ScrollingMovementMethod());

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Register_user.this);
        alertDialog.setView(view);
        AlertDialog alert = alertDialog.create();
        alert.show();

        Button okie = alert.findViewById(R.id.btn_okie_rules);
        okie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                ckb_rules.setError(null);
            }
        });
    };

    //show dialog login success message
    public class ViewDialog {
        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_notification);

            TextView text = (TextView) dialog.findViewById(R.id.txt_dialog_notifi);
            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_okie);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Register_user.this, Login_user.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
            dialog.show();
    }
};

    //check validation
    public void register_user() {
        String userName = user_name.getText().toString().trim();
        String emailUser = email_user.getText().toString().trim();
        String passUser = pass_user.getText().toString().trim();
        String passAgainUser = check_pass_again.getText().toString().trim();


        if (userName.isEmpty()) {
            user_name.setError("Tên người dùng không được để trống!");
            user_name.requestFocus();
            return;
        }
        else if (!userName.matches("[a-zA-Z ]+")){
            user_name.setError("Tên người dùng không chứa ký tự không hợp lệ!");
            user_name.requestFocus();
            return;
        }
        if (emailUser.isEmpty()) {
            email_user.setError("Email không được để trống!");
            email_user.requestFocus();
            return;
        }
        else if (!emailUser.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            email_user.setError("Email không hợp lệ!");
            email_user.requestFocus();
            return;
        }
        if (passUser.isEmpty()) {
            pass_user.setError("Mật khẩu không được để trống!");
            pass_user.requestFocus();
            return;
        }
        if (passUser.length() < 8) {
            pass_user.setError("Mật khẩu không đủ dài. Tối thiểu 8 ký tự!");
            pass_user.requestFocus();
            return;
        }
        if (passAgainUser.isEmpty()) {
            check_pass_again.setError("Mật khẩu nhập lại không trùng khớp!");
            check_pass_again.requestFocus();
            return;
        }
        else if (passAgainUser.equals(passUser) == false) {
            check_pass_again.setError("Mật khẩu nhập lại không trùng khớp!");
            check_pass_again.requestFocus();
            return;
        }
        ckb_rules = findViewById(R.id.ckb__rules);
        if (!ckb_rules.isChecked()){
            ckb_rules.setError("\n Chấp nhận điều khoản để được đăng ký!");
            ckb_rules.requestFocus();
            return;
        }

        process_loading.setVisibility(View.VISIBLE);
        mAuth.fetchSignInMethodsForEmail(emailUser).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = !task.getResult().getSignInMethods().isEmpty();
                if(check){
                    process_loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(Register_user.this, "Email đã tồn tại!", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                cls_user_info clsUserInfo = new cls_user_info(1, userName,0, 0, passUser, emailUser,"");
                                FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(userName).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            process_loading.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Register_user.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                                            ViewDialog alert = new ViewDialog();
                                            alert.showDialog(Register_user.this, "Đăng kí thành công!");
                                        } else {
                                            process_loading.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Register_user.this, "Đăng ký thất bại! Vui lòng thử lại !", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            } else {
                                process_loading.setVisibility(View.INVISIBLE);
                                Toast.makeText(Register_user.this, "Đăng ký thất bại! Vui lòng kiểm tra lại !", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });


    }
}