package com.example.lockerxlogin;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.lockerxlogin.Register.isNumeric;

public class TopUpPage extends AppCompatActivity {
    Button topUp;
    EditText topUpValue;
    TextView topUpBalance, topUpAmount;
    Spinner paymentType;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        topUpAmount = findViewById(R.id.topUpAmount);
        topUpBalance = findViewById(R.id.topUpBalance);
        topUpValue = findViewById(R.id.topUpValue);
        topUp = findViewById(R.id.topUp);
        paymentType = findViewById(R.id.paymentType);
        String FirstLine = ("You have \n$ ");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TopUpPage.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.name));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentType.setAdapter(myAdapter);


        reff = FirebaseDatabase.getInstance().getReference().child("User").child("12345678");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String balance = snapshot.child("walletBalance").getValue().toString();
                topUpBalance.setText(FirstLine + balance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        topUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Amount = topUpValue.getText().toString().trim();

                if (TextUtils.isEmpty(Amount)) {
                    topUpValue.setError("Please Enter an Amount");
                    return;
                } else if (!isNumeric(Amount)) {
                    topUpValue.setError("Please enter only digits");
                } else if (Float.parseFloat(Amount) <= 0.01) {
                    topUpValue.setError("Please enter a valid value");
                } else {
                    reff.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Float balance = Float.parseFloat(snapshot.child("walletBalance").getValue().toString());
                            Float amount = Float.parseFloat(Amount);
                            reff.child("walletBalance").setValue((amount + balance));
                            topUpBalance.setText(FirstLine + (amount + balance));
                            Toast.makeText(TopUpPage.this, "Top Up Success!", Toast.LENGTH_SHORT).show();
                            reff = FirebaseDatabase.getInstance().getReference().child("User").child("12345678");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }

                    });
                }
            }
        });
    }
}
