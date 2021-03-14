package com.example.lockerxlogin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import com.example.lockerxlogin.ui.BookingViewModel;

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

    DatabaseController dc = new DatabaseController();


    TextView BselectedDateStart,BselectedDateEnd, BselectedStart, BselectedEnd,BcurrentLocation;
    Spinner BspinnerSize;
    Button BsearchBtn;
    ImageButton BdateBtnStart, BdateBtnEnd, BliveBtn,BstartBtn, BendBtn;
    DatePicker BdatePick;
    FirebaseAuth fAuth;
    FirebaseUser FBuser;
    String postal,size,title;
    ArrayList<Locker> avalockers;




    private BookingViewModel bViewModel;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        BspinnerSize = findViewById(R.id.lockerSize);
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
        BspinnerSize = findViewById(R.id.lockerSize);

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
        title = this.getIntent().getStringExtra("title");
        postal = this.getIntent().getStringExtra("postal");
        BcurrentLocation = findViewById(R.id.currentLocation);
        BcurrentLocation.setText(title);

        Toast.makeText(BookingActivity.this,title +"here", Toast.LENGTH_LONG).show();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(BookingActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.size));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BspinnerSize.setAdapter(myAdapter);
        BspinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (BspinnerSize.getSelectedItem().toString().equals("small (30x40x30cm)")) {
                    size = "S";
                }
                if (BspinnerSize.getSelectedItem().toString().equals("medium (40x50x40cm)")) {
                   size = "M";
                }
                if (BspinnerSize.getSelectedItem().toString().equals("large (60x60x60cm)")) {
                    size = "L";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                size = "S";
                return;
            }
        });
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

            case R.id.searchBtn:

               // alertDialog();
                searchAvailableLocker();
                //getAvaiLockers();



                break;
            default:

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getAvaiLockers() {

        //start new thread + loading screen!
        ArrayList<Booking> bBookings = dc.retrieveBBookings();
        ArrayList<Locker> availLockers = dc.retrieveMatchingLockers(postal, size.charAt(0));
        //end thread but still keep loading screen or
        //end thread then start new thread + loading screen
        ArrayList<Long> unavailLockers = new ArrayList<Long>();

        for (Booking booking : bBookings) {
            if ((booking.getStartDate().compareTo(startDate)>=0 && booking.getStartDate().compareTo(endDate)<=0) ||
                    (booking.getEndDate().compareTo(startDate)>=0 && booking.getEndDate().compareTo(endDate)<=0) ||
                    (booking.getStartDate().compareTo(startDate)<=0 && booking.getEndDate().compareTo(endDate)>=0)) {
                if ((booking.getStartTime().compareTo(startTime)>=0 && booking.getStartTime().compareTo(endTime)<=0) ||
                        (booking.getEndTime().compareTo(startTime)>=0 && booking.getEndTime().compareTo(endTime)<=0) ||
                        (booking.getStartTime().compareTo(startTime)<=0 && booking.getEndTime().compareTo(endTime)>=0)) {
                    unavailLockers.add(booking.getLockerID());
                }
            }
        }
        for (Locker locker : availLockers) {
            if (unavailLockers.contains(locker.getLockerID())) {
                availLockers.remove(locker);
            }
        }
        avalockers = availLockers;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void searchAvailableLocker(){
        //startActivity(new Intent(getApplicationContext(), MainFunc.class));
        if (startDate == null || endDate == null || startTime == null || endTime == null) {
            alertDialog();
        }
        else if (startDate.compareTo(java.time.LocalDate.now()) == 0 && startDate.compareTo(endDate) == 0){
            if (startTime.compareTo(java.time.LocalTime.now())<0){
               alertDialog();
            }
            else if (startTime.compareTo(endTime) >0){
               alertDialog();
            }
            else {
              //  Intent intent = new Intent(this, AvailableLockers.class);
                //startActivity(new Intent(getApplicationContext(), AvailableLockers.class));
               // intent.putExtra("title", title);
                getAvaiLockers();
                Intent intent = new Intent(this, AvailableLockers.class);
                //intent.putExtra("title", title);  //pass location
                intent.putExtra("avlockers",avalockers);

                //avail lockers that can be booked for the user's selection: ArrayList<Locker> availLockers
            }
        }
        else if (startDate.compareTo(endDate) == 0){
            if (startTime.compareTo(endTime) >0){

                alertDialog();

            }
            else{

                //startActivity(new Intent(getApplicationContext(), AvailableLockers.class));//AvailableLockers

                getAvaiLockers();
                Intent intent = new Intent(this, AvailableLockers.class);
                //intent.putExtra("title", title);  //pass location
                intent.putExtra("avlockers",avalockers);
                //avail lockers that can be booked for the user's selection: ArrayList<Locker> availLockers

            }
        }
        else {

            getAvaiLockers();
            Intent intent = new Intent(this, AvailableLockers.class);
            //intent.putExtra("title", title);  //pass location
            intent.putExtra("avlockers",avalockers);
            //avail lockers that can be booked for the user's selection: ArrayList<Locker> availLockers
            //intent.putExtra("title", title);//pass location


        }
    }
    private void alertDialog() {
        //final androidx.appcompat.app.AlertDialog.Builder continueBookingDialog = new AlertDialog.Builder();
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Please choose valid start date, end date and start time, end time!");
        dialog.setTitle("Dialog Box");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"cancel is clicked",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }



    private void showStartTimePicker() {//start

        TimePickerDialog timeDialog = new TimePickerDialog(this, TimePickerDialog.THEME_HOLO_DARK, new TimePickerDialog.OnTimeSetListener() {
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

        TimePickerDialog timeDialog = new TimePickerDialog(this, TimePickerDialog.THEME_HOLO_DARK, new TimePickerDialog.OnTimeSetListener() {
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
                        startDate = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();

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
                        endDate = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();

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