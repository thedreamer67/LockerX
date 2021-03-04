package com.example.lockerxlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText LEmail, LPassword;
    TextView LLoginPage, LRegisterBtn;
    ProgressBar LProgressBar;
    Button LLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LEmail = findViewById(R.id.LEmail);
        LPassword = findViewById(R.id.LPassword);
       //LLoginPage = findViewById(R.id.LLoginText);
       LProgressBar = findViewById(R.id.LprogressBar);
        LLoginBtn= findViewById(R.id.LLoginBtn);
        LRegisterBtn = findViewById(R.id.LRegister);
        fAuth = FirebaseAuth.getInstance();


        LLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                String email = LEmail.getText().toString().trim();
                String password = LPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    LEmail.setError("Email is required");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    LPassword.setError("Password is required");
                    return;
                }
                if (password.length() < 6) { //Change this constraint TODO{
                    LPassword.setError("Password must have at least a capital letter and a symbol");
                    return;
                }

                LProgressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in succesfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });






        LRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }




}