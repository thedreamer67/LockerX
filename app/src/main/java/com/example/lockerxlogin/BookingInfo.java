package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingInfo extends AppCompatActivity {

    //BookingController bc = new BookingController();
    UserController uc = new UserController();
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
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO update datebase: new booking + update wallet
                //uc.makeBooking();
                //uc.makePayment();//todo if makePayment return false then jump tp top up page

                //TODO jumo to locker fragment page
            }
        });

        //textLocation.setText(locationstr);


    }
}