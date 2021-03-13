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

        payBtn = findViewById(R.id.cancelBtn);
        textLocation = findViewById(R.id.textLocation);
        textDateDuration = findViewById(R.id.bookedDate);
        textTimeDuration = findViewById(R.id.bookedTime);
        textBookingHour = findViewById(R.id.bookedDuration);
        textRate = findViewById(R.id.textRate);
        textSize = findViewById(R.id.textSize);
        textLockerid = findViewById(R.id.textLockerStructureid);
        textTotalPay = findViewById(R.id.textTotalPay);

        locationstr = this.getIntent().getStringExtra("title");

        textLocation.setText(locationstr);


    }
}