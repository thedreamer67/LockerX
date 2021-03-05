package com.example.lockerxlogin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TopUpPage extends AppCompatActivity {
    Button topUp;
    TextView topUpBalance;
    Spinner paymentType;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        topUpBalance = findViewById(R.id.topUpBalance);
        topUp = findViewById(R.id.topUp);
        paymentType= findViewById(R.id.paymentType);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TopUpPage.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.name));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentType.setAdapter(myAdapter);


        reff = FirebaseDatabase.getInstance().getReference().child("User").child("12345678");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String balance = snapshot.child("walletBalance").getValue().toString();
                String FirstLine = ("You have \n$ ");
                topUpBalance.setText(FirstLine + balance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
