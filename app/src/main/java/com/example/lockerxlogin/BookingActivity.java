package com.example.lockerxlogin;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.View;

import java.text.SimpleDateFormat;
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
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;


import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {

    private BookingViewModel mViewModel;
    // DatePickerDialog.OnDateSetListener
    private TimePicker timePicker;
    private DatePicker datePicker;
    private Calendar cal;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int shour;
    private int sminute;
    private int ehour;
    private int eminute;


    TextView BselectedDate, BselectedStart, BselectedEnd,BcurrentLocation;
    Spinner BspinnerSize;
    Button BsearchBtn;
    ImageButton BdateBtn, BliveBtn,BstartBtn, BendBtn;
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
        BdateBtn = findViewById(R.id.dateBtn);
        BliveBtn = findViewById(R.id.liveBtn);
        BsearchBtn = findViewById(R.id.searchBtn);
        BselectedEnd = findViewById(R.id.selectedEnd);
        BselectedStart = findViewById(R.id.selectedStart);

        BselectedDate = findViewById(R.id.seletedDate);

        cal = Calendar.getInstance();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH)+1;
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        shour = cal.get(Calendar.HOUR_OF_DAY);
        sminute =cal.get(Calendar.MINUTE);
        ehour = cal.get(Calendar.HOUR_OF_DAY);
        eminute =cal.get(Calendar.MINUTE);
        //setTitle(year+"-"+month+"-"+day+"-"+hour+"-"+minute);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);



        BendBtn.setOnClickListener(this);
        BdateBtn.setOnClickListener(this);
        BliveBtn.setOnClickListener(this);
        BsearchBtn.setOnClickListener(this);
        BstartBtn.setOnClickListener(this);
        String title = this.getIntent().getStringExtra("title");
        BcurrentLocation = findViewById(R.id.currentLocation);
        BcurrentLocation.setText(title);
        Toast.makeText(BookingActivity.this,title +"here", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.dateBtn:

                //getDate();
                //showDate();
                showDatePickerDialog();
                break;
            case R.id.endBtn:
                timePicker = (TimePicker)findViewById(R.id.timePicker);
                timePicker.setIs24HourView(true);

                showTimePicker();
            case R.id.sBtn:

                timePicker = (TimePicker)findViewById(R.id.timePicker);
                timePicker.setIs24HourView(true);
                showTimePicker2();
                break;
            default:

        }
    }



    private void showTimePicker2() {//start
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay+":"+minute);
                //BselectedTime.setText(hourOfDay+":"+minute);
            }


        });
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay+":"+minute);
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay, shour,sminute);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");

                BselectedStart.setText(sdf.format(c.getTime()));//change here

                sdf = new SimpleDateFormat("HH MM");

            }
        },shour,sminute,true).show();

//        GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay, shour,sminute);
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
//
//        BselectedStart.setText(sdf.format(c.getTime()));
//
//        sdf = new SimpleDateFormat("HH MM");
        //BselectedTime.setText(shour+":"+sminute);

    }

    private void showTimePicker() {//end
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay+":"+minute);
                ehour = hourOfDay;
                eminute = minute;
                System.out.println(ehour);
                BselectedEnd.setText(ehour+":"+eminute);
            }


        });
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay+":"+minute);
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay, ehour,eminute);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");


                //change here
               BselectedEnd.setText(ehour+":"+eminute);

                sdf = new SimpleDateFormat("HH MM");

            }
        },ehour,eminute,true).show();

//        GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay, ehour,eminute);
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
//
//        BselectedEnd.setText(sdf.format(c.getTime()));
//
//        sdf = new SimpleDateFormat("HH MM");
        //BselectedTime.setText(ehour+":"+eminute);


    }

    private void showDatePickerDialog() {


        DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH)+1;
                mDay = c.get(Calendar.DAY_OF_MONTH);
                updateDisplay();
            }
        };

        DatePickerDialog d = new DatePickerDialog(this,
                mDateSetListener, mYear, mMonth, mDay);
        d.show();

    }
    private void updateDisplay(){
        GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

        //BselectedDate.setText(sdf.format(c.getTime()));//change to selected date

        sdf = new SimpleDateFormat("yyyy-MM-dd");

        // transDateString=sdf.format(c.getTime());
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        BselectedDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
    }

//    @Override
//    public void onTimeSet(DatePicker timePicker, int year, int month, int dayOfMonth) {
//        BselectedDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
//    }


}