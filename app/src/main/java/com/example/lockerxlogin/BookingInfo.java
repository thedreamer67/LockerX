package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

public class BookingInfo extends AppCompatActivity {

    private Button payBtn;
    private TextView textLocation,textDateDuration,textTimeDuration,textBookingHour,textRate,textSize,textLockerid,textTotalPay;
    String locationstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info);

        payBtn = findViewById(R.id.bookBtn);
        textLocation = findViewById(R.id.bLocation);
        textDateDuration = findViewById(R.id.bbookedDate);
        textTimeDuration = findViewById(R.id.bbookedTime);
        textBookingHour = findViewById(R.id.bbookedDuration);
        //textRate = findViewById(R.id.textRate);
        textSize = findViewById(R.id.btextSize);
        textLockerid = findViewById(R.id.btextLockerStructureid);
        textTotalPay = findViewById(R.id.btextTotalPay);

        locationstr = this.getIntent().getStringExtra("title");

        //textLocation.setText(locationstr);


    }
}