package com.example.lockerxlogin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingController {

    public BookingController(){}

    public Booking makeBooking(DatabaseController dc, String email, String lockerStructureID,
                               String lockerID, LocalDate startDate, LocalTime startTime,
                               LocalDate endDate, LocalTime endTime){
        Booking booking = new Booking(startDate, startTime,endDate,endTime);
        dc.createBooking(email,lockerStructureID,lockerID,startDate,startTime,endDate,endTime);
        return booking;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean checkExpiredBooking(DatabaseController dc, String email, int lockerID,
                                       int lockerStructureID){
        LocalDate date = dc.retrieveBookingDate(email,lockerID,lockerStructureID);
        LocalTime time = dc.retrieveBookingTime(email,lockerID,lockerStructureID);
        if(date.compareTo(LocalDate.now())>0){
            return false; //returns false if booking not expired yet, it is a future reservation
        }
        else {
            if(date.compareTo(LocalDate.now())<0) {
                return true; //past booking based on date
            }
            else{
                //if(time.compareTo(LocalTime.now()))
            }
        }
    }
}
