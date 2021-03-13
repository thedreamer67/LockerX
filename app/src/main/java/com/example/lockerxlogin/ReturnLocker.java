package com.example.lockerxlogin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ReturnLocker extends AppCompatActivity {

    private String bookid;
    private String endDate;
    private String endTime;
    private String lockerid;
    private String startDate;
    private String startTime;
    private String status;
    private String structureid;
    private String mobile;
    private String location;
    private String size;
    private String totalPay;
    private String duration;

    TextView rLocation, rbookedDate, rbookedTime ,rbookedDuration, rtextSize, rtextLockerStructureid,
            rtextLockerid, rtextTotalPay;
    Button returnBtn;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_locker);

        rLocation = findViewById(R.id.rLocation);
        rbookedDate = findViewById(R.id.rbookedDate);
        rbookedTime = findViewById(R.id.rbookedTime);
        rtextSize = findViewById(R.id.rtextSize);
        rtextTotalPay = findViewById(R.id.rtextTotalPay);
        rtextLockerid = findViewById(R.id.rtextLockerid);
        rtextLockerStructureid = findViewById(R.id.rtextLockerStructureid);
        returnBtn = findViewById(R.id.returnBtn);
        rbookedDuration = findViewById(R.id.rbookedDuration);


        bookid = this.getIntent().getStringExtra("bookid");
        endDate = this.getIntent().getStringExtra("endDate");
        endTime = this.getIntent().getStringExtra("endTime");
        lockerid = this.getIntent().getStringExtra("lockerid");
        startDate = this.getIntent().getStringExtra("startDate");
        startTime = this.getIntent().getStringExtra("startTime");
        status = this.getIntent().getStringExtra("status");
        structureid = this.getIntent().getStringExtra("structureid");
        mobile = this.getIntent().getStringExtra("mobile");
        location = this.getIntent().getStringExtra("location");
        size = this.getIntent().getStringExtra("size");
        //totalPay = this.getIntent().getStringExtra("totalPay");


        rLocation.setText(location);
        rbookedDate.setText(startDate+" - "+endDate);
        rbookedTime.setText(startTime+" - "+endTime);
        rtextSize.setText(size);
        //textTotalPay.setText(totalPay);
        rtextLockerid.setText(lockerid);
        rtextLockerStructureid.setText(structureid);

        String strs = startDate +" "+ startTime;//2021-04-21 13:00:00
        String stre = endDate +" "+ endTime;//2021-04-21 13:00:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime sdateTime = LocalDateTime.parse(strs, formatter);
        LocalDateTime edateTime = LocalDateTime.parse(stre, formatter);
        Duration d = Duration.between(sdateTime,edateTime);
        long minutes = d.toMinutes();
        int hoursd = (int) Math.floor((int)minutes / 60);
        int min = (int)minutes % 60;
        duration = hoursd+" hours "+min+" minutes";
        rbookedDuration.setText(duration);
    }
}