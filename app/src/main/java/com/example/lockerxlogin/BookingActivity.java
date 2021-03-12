package com.example.lockerxlogin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import android.view.View;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import com.example.lockerxlogin.ui.BookingViewModel;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Toast;


import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener {

    private BookingViewModel mViewModel;
    // DatePickerDialog.OnDateSetListener
    private TimePicker timePicker;
    private DatePicker datePicker;
    private Calendar cal;
    private int sYear;
    private int sMonth;
    private int sDay;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int eYear;
    private int eMonth;
    private int eDay;
    private int shour;
    private int sminute;
    private int ehour;
    private int eminute;
    private LocalDate startDate, endDate;
    private LocalTime startTime, endTime;


    TextView BselectedDateStart,BselectedDateEnd, BselectedStart, BselectedEnd,BcurrentLocation;
    Spinner BspinnerSize;
    Button BsearchBtn;
    ImageButton BdateBtnStart, BdateBtnEnd, BliveBtn,BstartBtn, BendBtn;
    DatePicker BdatePick;
    FirebaseAuth fAuth;
    FirebaseUser FBuser;




    private BookingViewModel bViewModel;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        BstartBtn = findViewById(R.id.sBtn);
        BendBtn = findViewById(R.id.endBtn);
        BdateBtnStart = findViewById(R.id.dateBtnStart);
        BdateBtnEnd = findViewById(R.id.dateBtnEnd);
        BliveBtn = findViewById(R.id.liveBtn);
        BsearchBtn = findViewById(R.id.searchBtn);
        BselectedEnd = findViewById(R.id.selectedEnd);
        BselectedStart = findViewById(R.id.selectedStart);

        BselectedDateStart = findViewById(R.id.selectedDateStart);
        BselectedDateEnd = findViewById(R.id.selectedDateEnd);

        cal = Calendar.getInstance();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH)+1;
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        shour = cal.get(Calendar.HOUR_OF_DAY);
        sminute =cal.get(Calendar.MINUTE);
        ehour = cal.get(Calendar.HOUR_OF_DAY);
        eminute =cal.get(Calendar.MINUTE);
        //setTitle(year+"-"+month+"-"+day+"-"+hour+"-"+minute);


        BendBtn.setOnClickListener(this);
        BdateBtnStart.setOnClickListener(this);
        BdateBtnEnd.setOnClickListener(this);
        BliveBtn.setOnClickListener(this);
        BsearchBtn.setOnClickListener(this);
        BstartBtn.setOnClickListener(this);
        String title = this.getIntent().getStringExtra("title");
        BcurrentLocation = findViewById(R.id.currentLocation);
        BcurrentLocation.setText(title);

        Toast.makeText(BookingActivity.this,title +"here", Toast.LENGTH_LONG).show();
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.dateBtnStart:

                //getDate();
                //showDate();
                showStartDatePickerDialog();
                break;

            case R.id.dateBtnEnd:
                showEndDatePickerDialog();
                break;

            case R.id.endBtn:

                //timePicker = (TimePicker)findViewById(R.id.timePicker);
                //timePicker.setIs24HourView(true);
                showEndTimePicker();
                break;

            case R.id.sBtn:
                //timePicker = (TimePicker)findViewById(R.id.timePicker);
                //timePicker.setIs24HourView(true);
                showStartTimePicker();
                break;
            default:

        }
    }



    private void showStartTimePicker() {//start

        TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {

                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                BselectedStart.setText("Start time: "+sdf.format(cal.getTime()));//change to selected time
                startTime = LocalTime.of(hour, minute);

            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);

        timeDialog.show();

    }

    private void showEndTimePicker() {//end

        TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {

                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                BselectedEnd.setText("End time: "+sdf.format(cal.getTime()));//change to selected time
                endTime = LocalTime.of(hour, minute);

            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);

        timeDialog.show();



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showStartDatePickerDialog() {

        DatePickerDialog dateDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       // LogUtils.d(TAG, "onDateSet: year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);

                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        sYear = cal.get(Calendar.YEAR);
                        sMonth = cal.get(Calendar.MONTH);
                        sDay = cal.get(Calendar.DAY_OF_MONTH);


                        SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMMM yyyy");
                        BselectedDateStart.setText(sdfDate.format(cal.getTime()));//change to selected date
                        startDate = LocalDate.of(year,month,dayOfMonth);

                    }
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));


        dateDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        if (endDate != null){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR,eYear);
            c.set(Calendar.MONTH,eMonth);
            c.set(Calendar.DAY_OF_MONTH,eDay);
            dateDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        }

        dateDialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showEndDatePickerDialog() {

        DatePickerDialog dateDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // LogUtils.d(TAG, "onDateSet: year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);

                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        eYear = cal.get(Calendar.YEAR);
                        eMonth = cal.get(Calendar.MONTH);
                        eDay = cal.get(Calendar.DAY_OF_MONTH);

                        SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMMM yyyy");
                        BselectedDateEnd.setText(sdfDate.format(cal.getTime()));//change to selected date
                        endDate = LocalDate.of(year, month, dayOfMonth);

                    }
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR,sYear);
                c.set(Calendar.MONTH,sMonth);
                c.set(Calendar.DAY_OF_MONTH,sDay);
        dateDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        //if (startDate.getDayOfWeek() != null){
        //    dateDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        //}
        dateDialog.show();



    }

}