package com.example.lockerxlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    Button btnConfirmChangePw;
    EditText plainTextOldPassword, plainTextNewPassword , plainTextNewConfirmPassword;
    FirebaseAuth fAuth;
    FirebaseUser fUser;
    String confirmedPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        fAuth = FirebaseAuth.getInstance();
        plainTextOldPassword = findViewById(R.id.plainTextOldPassword);
        plainTextNewPassword = findViewById(R.id.plainTextNewPassword);
        plainTextNewConfirmPassword = findViewById(R.id.plainTextOldPasswordCfm);
        btnConfirmChangePw = findViewById(R.id.btnConfirmChangePw);
        fUser = fAuth.getCurrentUser();

        btnConfirmChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "On Click listener clicked");
                if( String.valueOf(plainTextNewConfirmPassword.getText()).equals( String.valueOf(plainTextNewPassword.getText()))){
                    Log.d("TAG", "Entered if statement");

                    fAuth.signInWithEmailAndPassword(Login.currUser.getEmail(), String.valueOf(plainTextOldPassword.getText())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("TAG", "Succesfully signed in ");
                                fUser.updatePassword(String.valueOf(plainTextNewPassword.getText())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(ChangePassword.this, "Password has been changed!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MainFunc.class));
                                        }else{
                                            Toast.makeText(ChangePassword.this, "Error! Password not changed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }

                            else {
                                Toast.makeText(ChangePassword.this, "Old password does not match!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

                else {
                    Toast.makeText(ChangePassword.this, "New password and confirm password does not match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}