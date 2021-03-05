package com.example.lockerxlogin;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DatabaseController {
    DatabaseReference reff;
    TempDataStorage ds = new TempDataStorage();

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

    //NEED TO CHANGE THIS
    //retrieve all users from the db
//    public ArrayList retrieveAllUsers() {
//        ArrayList<User> userList = new ArrayList<User>();
//        reff = FirebaseDatabase.getInstance().getReference().child("User");
//        reff.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                userList.clear();
//                if (snapshot.exists()) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        User user = dataSnapshot.getValue(User.class);
//                        userList.add(user);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        return userList;
//    }

    //NEED TO CHANGE THIS
    //retrieve user using their email from db
//    public ArrayList retrieveUserByID(String email) {
//        ArrayList<User> userList = new ArrayList<User>();
//        Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("email").equalTo(email);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                userList.clear();
//                if (snapshot.exists()) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        User user = dataSnapshot.getValue(User.class);
//                        userList.add(user);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        return userList;
//    }

    //NOT WORKING YET
    //retrieve user's mobile using their email from db
    public String retrieveMobileByEmail(String email) {
        reff = FirebaseDatabase.getInstance().getReference().child("User");
        Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ds.setStr(dataSnapshot.child("mobile").getValue().toString());
                    }
//                    User user = snapshot.getValue(User.class);
//                    ds.setStr(user.getMobile());
//                    ds.setStr(snapshot.getKey(String.class)); //wrong but idk why
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return ds.getStr();
    }

    //NOT WORKING YET
    //retrieve number of bookings from db
    public long retrieveBookingCount() {
        reff = FirebaseDatabase.getInstance().getReference().child("Booking");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ds.setLongNum(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return ds.getLongNum();
    }


    public void storeLockerStatus(int lockerID,int lockerStructureID){
        //insert codes here to store new status of locker
    }
    public void createBooking(String email, int lockerStructureID, int lockerID,
                              LocalDate startDate, LocalTime startTime,
                              LocalDate endDate, LocalTime endTime, char status){
        // insert codes to create a new row in booking table
    }

    /*@RequiresApi(api = Build.VERSION_CODES.O)
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
    }*/

    public void setBookingStatus(String email, int lockerStructureID, int lockerID, LocalDate startDate,
                                 LocalTime startTime, LocalDate endDate, LocalTime endTime, char status){
        //query to set booking status
    }

    public char retrieveLockerSize(int lockerStructureID, int lockerID){
        //database query to retrieve locker size
        return 'S'; //dummy value
    }

    //method to update wallet balance
    public void updateWalletBalance(String smobile, float newBalance) {
        reff = FirebaseDatabase.getInstance().getReference().child("User");
        Map<String, Object> walletUpdate = new HashMap<>();
        walletUpdate.put("walletBalance", newBalance);
        reff.child(smobile).updateChildren(walletUpdate);   //update walletBalance of user with smobile
    }



}
