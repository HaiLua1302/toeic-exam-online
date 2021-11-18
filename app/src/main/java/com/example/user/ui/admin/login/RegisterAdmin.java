package com.example.user.ui.admin.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.MainActivity;
import com.example.user.R;
import com.example.user.ui.class_admin.cls_admin_info;
import com.example.user.ui.class_user.cls_user_info;
import com.example.user.ui.login.Login_user;
import com.example.user.ui.login.Register_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterAdmin extends AppCompatActivity {
    public Button btnRegisterAdmin;
    public EditText edtEmailAdmin, edtPassAdmin, edtNameAdmin,edtPassRERegAdmin;
    public TextView txtLoginAdmin;
    public ProgressBar process_loading;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        mAuth = FirebaseAuth.getInstance();

        btnRegisterAdmin = findViewById(R.id.btn_regAdmin);
        txtLoginAdmin = findViewById(R.id.txt_loginAdmin);

        edtNameAdmin = findViewById(R.id.edt_nameAdmin);
        edtEmailAdmin = findViewById(R.id.edt_EmailRegAdmin);
        edtPassAdmin = findViewById(R.id.edt_PassRegAdmin);
        edtPassRERegAdmin = findViewById(R.id.edt_pass_RERegAdmin);


        txtLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterAdmin.this, LoginAdmin.class));
            }
        });
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAdmin();
            }
        });
    }
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
    }

//    public void getControl(){
//        btnRegisterAdmin = findViewById(R.id.btn_regAdmin);
//        txtLoginAdmin = findViewById(R.id.txt_loginAdmin);
//        edtNameAdmin = findViewById(R.id.edt_user_nameAdmin);
//        edtEmailAdmin = findViewById(R.id.edt_emailAdmin);
//        edtPassAdmin = findViewById(R.id.edt_pass_regAdmin);
//        edtPassRERegAdmin = findViewById(R.id.edt_pass_re_regAdmin);
//    }
    public class ViewDialog {

        public void showDialog(Activity activity, String msg){
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
                    Intent intent = new Intent(RegisterAdmin.this, LoginAdmin.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }
    public void registerAdmin(){
        String nameAdmin = edtNameAdmin.getText().toString().trim();
        String emailAdmin = edtEmailAdmin.getText().toString().trim();
        String passAdmin = edtPassAdmin.getText().toString().trim();
        String passAgainAdmin = edtPassRERegAdmin.getText().toString().trim();
        String idAdmin = "";
        if (nameAdmin.isEmpty())
        {
            edtEmailAdmin.setError("Tên người dùng không được để trống!");
            edtEmailAdmin.requestFocus();
            return;
        }
        if (emailAdmin.isEmpty())
        {
            edtEmailAdmin.setError("Email không được để trống!");
            edtEmailAdmin.requestFocus();
            return;
        }
        if (passAdmin.isEmpty())
        {
            edtPassAdmin.setError("Mật khẩu không được để trống!");
            edtPassAdmin.requestFocus();
            return;
        }
        if (passAdmin.length() < 8)
        {
            edtPassAdmin.setError("Mật khẩu không đủ dài. Tối thiểu 8 ký tự!");
            edtPassAdmin.requestFocus();
            return;
        }
        if (passAgainAdmin.isEmpty())
        {
            edtPassRERegAdmin.setError("Mật khẩu nhập lại không trùng khớp!");
            edtPassRERegAdmin.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emailAdmin,passAdmin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    cls_admin_info clsAdminInfo = new cls_admin_info(mAuth.getUid(),nameAdmin,emailAdmin,passAdmin);
                    FirebaseDatabase.getInstance().getReference("Admin").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(clsAdminInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterAdmin.this,"Đăng ký thành công!",Toast.LENGTH_LONG).show();
                                RegisterAdmin.ViewDialog alert = new RegisterAdmin.ViewDialog();
                                alert.showDialog(RegisterAdmin.this, "Đăng kí thành công!");
                            }
                            else {
                                Toast.makeText(RegisterAdmin.this,"Đăng ký thất bại! Vui lòng thử lại",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(RegisterAdmin.this,"Đăng ký thất bại!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
