package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText LEmail, LPassword;
    TextView LLoginPage, LRegisterBtn;
    ProgressBar LProgressBar;
    Button LLoginBtn;
    FirebaseAuth fAuth;
    //Hello
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LEmail = findViewById(R.id.LEmail);
        LPassword = findViewById(R.id.LPassword);
        LLoginPage = findViewById(R.id.LLoginText);
        LProgressBar = findViewById(R.id.LprogressBar);
        LLoginBtn= findViewById(R.id.LLoginBtn);
        LRegisterBtn = findViewById(R.id.LRegister);

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