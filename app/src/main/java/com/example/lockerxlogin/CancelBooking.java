package com.example.lockerxlogin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

        textLocation = findViewById(R.id.textLocation);
        bookedDate = findViewById(R.id.bookedDate);
        bookedTime = findViewById(R.id.bookedTime);
        textSize = findViewById(R.id.textSize);
        textTotalPay = findViewById(R.id.textTotalPay);
        textLockerid = findViewById(R.id.textLockerid);
        textLockerStructureid = findViewById(R.id.textLockerStructureid);
        bookedDuration = findViewById(R.id.bookedDuration);


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

        //TODO diff between startTime and endTime
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//        Date endTimeD = (Date) formatter.parse(endTime);
//        Date startDateD = (Date) formatter.parse(startTime);
//        long convertedLend = endTimeD.getTime();
//        long convertedLstart = startDateD.getTime();
//        long diff = convertedLend-convertedLstart;
//        duration = String.valueOf(diff);
//
//
//        bookedDuration.setText(duration);



    }
}