package com.example.lockerxlogin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;

public class DatabaseController {
    public DatabaseController(){}

    public void storeLockerStatus(int lockerID,int lockerStructureID){
        //insert codes here to store new status of locker
    }
    public void createBooking(String email, String lockerStructureID, String lockerID,
                              LocalDate startDate, LocalTime startTime,
                              LocalDate endDate, LocalTime endTime){
        // insert codes to create a new row in booking table
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate retrieveBookingStartDate(String email, int lockerID, int lockerStructureID){
        //database query to retrieve the date of booking
        LocalDate date = LocalDate.now(); //dummy value
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalTime retrieveBookingStartTime(String email, int lockerID, int lockerStructureID){
        //database query to retrieve the time of booking
        LocalTime time = LocalTime.now(); //dummy value
        return time;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate retrieveBookingEndDate(String email, int lockerID, int lockerStructureID){
        //database query to retrieve the date of booking
        LocalDate date = LocalDate.now(); //dummy value
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalTime retrieveBookingEndTime(String email, int lockerID, int lockerStructureID){
        //database query to retrieve the time of booking
        LocalTime time = LocalTime.now(); //dummy value
        return time;
    }

}
