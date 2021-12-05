package com.example.user.ui.login;


import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.user.R;
import com.example.user.ui.home.UserHome;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.annotations.Nullable;

public class Login_user extends AppCompatActivity {

    public Button btn_Login;
    public EditText edit_Email, edit_Pass;
    public ProgressBar process_loading;
    private FirebaseAuth mAuth;

    //login by google
    public Button signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private  String TAG = "Đăng nhập";
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
        getSupportActionBar().setTitle("Đăng Nhập"); //Đăng nhập


        btn_Login = findViewById(R.id.btn_login);
        edit_Email = findViewById(R.id.edt_email);
        edit_Pass = findViewById(R.id.edt_pass_login);
        process_loading = findViewById(R.id.progressBar_loading_login);
        process_loading.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //event click textview register
        TextView txt_register = findViewById(R.id.txt_register);
        txt_register.setPaintFlags(txt_register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_user.this, Register_user.class);
                startActivity(intent);

            }
        });

        //event click textview forgotpass
        TextView txt_forgot_pass = findViewById(R.id.txt_quenmatkhau);
        txt_forgot_pass.setPaintFlags(txt_forgot_pass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_user.this, Forgot_pass_user.class);
                startActivity(intent);
            }
        });

        //event click textview skip
        TextView txt_skip = findViewById(R.id.txt_skip);
        txt_skip.setPaintFlags(txt_skip.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_user.this, UserHome.class);
                startActivity(intent);

            }
        });

        //login by google
        ////event click button sign in google
        signInButton = findViewById(R.id.btn_login_gg);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //noinspection deprecation
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(Login_user.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            Toast.makeText(Login_user.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        //check if the account is null
        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login_user.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login_user.this, UserHome.class);
                        startActivity(intent);
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);

                    } else {
                        Toast.makeText(Login_user.this, "Failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
        else{
            Toast.makeText(Login_user.this, "acc failed", Toast.LENGTH_SHORT).show();
        }
    }

    //repair pass
    private void updateUI(FirebaseUser fUser){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null){
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            Toast.makeText(Login_user.this,personName + personEmail ,Toast.LENGTH_SHORT).show();
        }

    }

    //check login
    public void login() {
       String email = edit_Email.getText().toString().trim();
       String pass = edit_Pass.getText().toString().trim();

        if (email.isEmpty()) {
            edit_Email.setError("Email không được để trống!");
            edit_Email.requestFocus();
            return;
        }

        else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            edit_Email.setError("Email không hợp lệ!");
            edit_Email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            edit_Pass.setError("Mật khẩu không được để trống!");
            edit_Pass.requestFocus();
            return;
        }

        process_loading.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    process_loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login_user.this, UserHome.class);
                    startActivity(intent);
                } else {
                    process_loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại ! Vui lòng kiểm tra lại Email & Mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}