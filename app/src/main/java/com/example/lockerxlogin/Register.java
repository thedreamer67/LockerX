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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText mEmail, mPassword, mName, mMobile;
    TextView mLoginBtn;
    Button mRegisterBtn;
    ProgressBar mprogressBar;
    FirebaseAuth fAuth;
    DatabaseReference reff;

    //number
    public static final String REG_NUMBER = ".*\\d+.*";
    //lowercase
    public static final String REG_UPPERCASE = ".*[A-Z]+.*";
    //uppercase
    public static final String REG_LOWERCASE = ".*[a-z]+.*";
    //special character
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.RName);
        mEmail = findViewById(R.id.REamil);
        mPassword = findViewById(R.id.RPassword);
        mMobile = findViewById(R.id.RPhone);
        //mLoginBtn = findViewById(R.id.LoginFromRegisterBtn);
        mprogressBar = findViewById(R.id.LprogressBar);
        fAuth = FirebaseAuth.getInstance();
        mRegisterBtn = findViewById(R.id.RRegisterBtn);
        String dest = mMobile.getText().toString().trim();
        // try to change the reference such that "user1"/"user2" is the mobile of the user instead
        reff = FirebaseDatabase.getInstance().getReference().child("User").child("user2"); //reference to the "User" table of the db

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
//        mLoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),Login.class));
//
//            }
//
//        });


        mRegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                long mobile = Long.parseLong(mMobile.getText().toString().trim());
                String smobile = mobile+"";


                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }
                if(!checkPasswordRule(password)){
                    mPassword.setError("Password must have at least 8 character with a uppercase, a lower case, a number and a symbol");
                    return;
                }

                if(TextUtils.isEmpty(smobile)){
                    mMobile.setError("Phone is required");
                    return;
                }

                if(smobile.length()!=7){
                    mMobile.setError("Please enter 8 digits only");
                }

                mprogressBar.setVisibility(View.GONE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // use dbcontroller instead
                            User user = new User(name, email, mobile, 0);
                            reff.setValue(user); //store new user to do
                            Toast.makeText(Register.this, "User created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }else{
                            Toast.makeText(Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }

        });





    }
    public boolean checkPasswordRule(String password){
        //return less 8
        if (password == null || password.length() <8 ) return false;
        int i = 0;
        if (password.matches(REG_NUMBER)) i++;
        if (password.matches(REG_LOWERCASE))i++;
        if (password.matches(REG_UPPERCASE)) i++;
        if (password.matches(REG_SYMBOL)) i++;

        if (i  < 4 )  return false;

        return true;
    }

}