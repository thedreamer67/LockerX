package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

public class BookingInfo extends AppCompatActivity {

    private Button payBtn;
    private TextView textLocation,textDateDuration,textTimeDuration,textBookingHour,textRate,textSize,textLockerid,textTotalPay;
    String locationstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info);

        payBtn = findViewById(R.id.payBtn);
        textLocation = findViewById(R.id.textLocation);
        textDateDuration = findViewById(R.id.textDateDuration);
        textTimeDuration = findViewById(R.id.textTimeDuration);
        textBookingHour = findViewById(R.id.textBookingHour);
        textRate = findViewById(R.id.textRate);
        textSize = findViewById(R.id.textSize);
        textLockerid = findViewById(R.id.textLockerid);
        textTotalPay = findViewById(R.id.textTotalPay);

        locationstr = this.getIntent().getStringExtra("title");

        textLocation.setText(locationstr);


    }
}