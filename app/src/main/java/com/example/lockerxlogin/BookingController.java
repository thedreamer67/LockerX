package com.example.lockerxlogin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingController {

    public BookingController(){}

    public void makeBooking(DatabaseController dc, String email, String lockerStructureID,
                               String lockerID, LocalDate startDate, LocalTime startTime,
                               LocalDate endDate, LocalTime endTime){
        Booking booking = new Booking(startDate, startTime,endDate,endTime);
        dc.createBooking(email,lockerStructureID,lockerID,startDate,startTime,endDate,endTime);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    // returns 1 if expired, 2 if in progress, and 3 if not started
    public int checkExpiredBooking(DatabaseController dc, String email, int lockerID,
                                       int lockerStructureID) {
        LocalDate startDate = dc.retrieveBookingStartDate(email, lockerID, lockerStructureID);
        LocalTime startTime = dc.retrieveBookingStartTime(email, lockerID, lockerStructureID);
        LocalDate endDate = dc.retrieveBookingEndDate(email, lockerID, lockerStructureID);
        LocalTime endTime = dc.retrieveBookingEndTime(email, lockerID, lockerStructureID);
        if (startDate.compareTo(LocalDate.now()) > 0) {
            return 3; //future reservation
        } else if (endDate.compareTo(LocalDate.now()) < 0) {
            return 1; //expired since end date is earlier than today
        } else if (startDate.compareTo(LocalDate.now()) < 0 && endDate.compareTo(LocalDate.now()) > 0) {
            return 2; //in-progress, current date in between start and end date of booking
        } else if (endDate.compareTo(LocalDate.now()) == 0 && endTime.compareTo(LocalTime.now()) <= 0) {
            return 1; //expired, end time is before current time
        } else if (endDate.compareTo(LocalDate.now()) == 0 && endTime.compareTo(LocalTime.now()) > 0) {
            if (startTime.compareTo(LocalTime.now()) > 0)
                return 3; //start time more than current time, it's a reservation
            else
                return 2; // start time < current time and end time >0, in-progress
        } else
            return -1; //compulsory statement by program, will not reach this case
    }
}
