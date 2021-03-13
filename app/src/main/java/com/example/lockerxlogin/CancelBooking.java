package com.example.lockerxlogin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class CancelBooking extends AppCompatActivity {

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

    TextView textLocation, bookedDate, bookedTime, textSize, textTotalPay, textLockerid, textLockerStructureid, bookedDuration;
    Button cancelBtn;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_booking);

        textLocation = findViewById(R.id.cLocation);
        bookedDate = findViewById(R.id.cbookedDate);
        bookedTime = findViewById(R.id.cbookedTime);
        textSize = findViewById(R.id.ctextSize);
        textTotalPay = findViewById(R.id.ctextTotalPay);
        textLockerid = findViewById(R.id.ctextLockerid);
        textLockerStructureid = findViewById(R.id.ctextLockerStructureid);
        bookedDuration = findViewById(R.id.cbookedDuration);


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


        textLocation.setText(location);
        bookedDate.setText(startDate+" - "+endDate);
        bookedTime.setText(startTime+" - "+endTime);
        textSize.setText(size);
        //textTotalPay.setText(totalPay);
        textLockerid.setText(lockerid);
        textLockerStructureid.setText(structureid);


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
        bookedDuration.setText(duration);



    }
}