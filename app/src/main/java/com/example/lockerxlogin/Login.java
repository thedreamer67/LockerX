package com.example.lockerxlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText LEmail, LPassword;
    TextView LLoginPage, LRegisterBtn, LForgetPw;
    ProgressBar LProgressBar;
    Button LLoginBtn;
    FirebaseAuth fAuth;
    FirebaseUser FBuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LEmail = findViewById(R.id.LEmail);
        LPassword = findViewById(R.id.LPassword);
        //LLoginPage = findViewById(R.id.LLoginText);
        LForgetPw = findViewById(R.id.LForgetPassword);
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
                //
                //LProgressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        // add else here
                        if (task.isSuccessful()) {
                            FBuser = fAuth.getCurrentUser();
                            if(!FBuser.isEmailVerified()){
                                LProgressBar.setVisibility(View.VISIBLE);
                                EditText resendVerificationEditText = new EditText(v.getContext());
                                final AlertDialog.Builder resendVerificationMailDialog = new AlertDialog.Builder(v.getContext());
                                resendVerificationMailDialog.setTitle("Resend verification email");
                                resendVerificationMailDialog.setMessage("Enter your email to resent verification link");
                                resendVerificationMailDialog.setView(resendVerificationEditText);

                                resendVerificationMailDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FBuser.getEmail();
                                        FBuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid){
                                                Toast.makeText(Login.this, "Verification email has been sent!", Toast.LENGTH_SHORT).show();
                                                LProgressBar.setVisibility(View.GONE);//
                                            }

                                        }).addOnFailureListener(new OnFailureListener(){
                                            @Override
                                            public void onFailure(@NonNull Exception e){
                                                Log.d("TAG", "OnFailure: Email not sent " +e.getMessage());
                                                LProgressBar.setVisibility(View.GONE);
                                            }

                                        });

                                    }
                                });
                                resendVerificationMailDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Automatically close the dialog
                                    }
                                });
                                resendVerificationMailDialog.show();


                            }
                            else {
                                Toast.makeText(Login.this, "Logged in succesfully.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        } else {
                            Toast.makeText(Login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });


        LForgetPw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
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