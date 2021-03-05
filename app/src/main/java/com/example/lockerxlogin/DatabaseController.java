package com.example.lockerxlogin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;

public class DatabaseController {
    DatabaseReference reff;

    public DatabaseController(){}

    //method to store new user to db
    public void storeNewUser(String name, String email, long mobile) {
        reff = FirebaseDatabase.getInstance().getReference().child("User"); //reference to the "User" table of the db
        String smobile = mobile+"";
        //store new user to db
        reff.child(smobile).child("name").setValue(name);
        reff.child(smobile).child("email").setValue(email);
        reff.child(smobile).child("mobile").setValue(mobile);
        reff.child(smobile).child("walletBalance").setValue(0);
    }

    public void storeLockerStatus(int lockerID,int lockerStructureID){
        //insert codes here to store new status of locker
    }
    public void createBooking(String email, int lockerStructureID, int lockerID,
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

    public char retrieveLockerSize(int lockerStructureID, int lockerID){
        //database query to retrieve locker size
        return 'S'; //dummy value
    }

    //method to update wallet balance
    public void updateWalletBalance(String email, float newBalance) {
//        reff = FirebaseDatabase.getInstance().getReference().child("User").
//        reff.setValue()
    }



}
