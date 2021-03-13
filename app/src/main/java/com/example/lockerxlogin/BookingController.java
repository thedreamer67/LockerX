package com.example.lockerxlogin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class BookingController {

    //UserController uc = new UserController(Login.currUser);

    public BookingController(){}

    DatabaseController dc = new DatabaseController();


    public void makeBooking(String mobile, long structureID,
                               long lockerID, LocalDate startDate, LocalTime startTime,
                               LocalDate endDate, LocalTime endTime){

        /*I actually don't know if we need the statement below because never really use this object,
         just store in database only*/
        Booking booking = new Booking(startDate, startTime,endDate,endTime,
                mobile,structureID,lockerID,'B'); //booking status to 'B',booked

        dc.createBooking(mobile,structureID,lockerID,startDate,startTime,endDate,endTime,'B');
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    // returns 1 if expired, 2 if in progress, and 3 if not started
    public int checkExpiredBooking(LocalDate startDate, LocalTime startTime,
                                   LocalDate endDate, LocalTime endTime) {
        /*LocalDate startDate = dc.retrieveBookingStartDate(mobile, lockerID, lockerStructureID);
        LocalTime startTime = dc.retrieveBookingStartTime(mobile, lockerID, lockerStructureID);
        LocalDate endDate = dc.retrieveBookingEndDate(mobile, lockerID, lockerStructureID);
        LocalTime endTime = dc.retrieveBookingEndTime(mobile, lockerID, lockerStructureID);*/
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public float calculateRentalFees(long structureID, long lockerID, LocalDate startDate,
                                     LocalTime startTime, LocalDate endDate, LocalTime endTime, char lockerSize) {

        HashMap<Character, Float> priceMap = new HashMap<Character, Float>();
        priceMap.put('S', (float) 1);
        priceMap.put('M', (float) 2);
        priceMap.put('L', (float) 3);
        //priceMap = {'S'=1, 'M'=2, 'L'=3}



        //finding rental rate based on locker size

        float rentalRate = priceMap.get(lockerSize);


        //merging date and time to datetime format
        LocalDateTime startDateTime = LocalDateTime.of(startDate,startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate,endTime);

        //calculating difference between start and end in minutes
        long differenceInMinutes = ChronoUnit.MINUTES.between(startDateTime,endDateTime);

        return (rentalRate/60)*differenceInMinutes;
    }

}
