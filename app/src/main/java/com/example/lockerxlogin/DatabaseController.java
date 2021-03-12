package com.example.lockerxlogin;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DatabaseController {
    private static final String TAG = "DatabaseController"; //for Log

    DatabaseReference reff;
    TempDataStorage ds = new TempDataStorage();

    public DatabaseController(){}

    //method to store new user to db
    public void storeNewUser(String name, String email, String mobile) {
        reff = FirebaseDatabase.getInstance().getReference().child("User"); //reference to the "User" table of the db
        //store new user to db
        reff.child(mobile).child("name").setValue(name);
        reff.child(mobile).child("email").setValue(email);
        reff.child(mobile).child("mobile").setValue(mobile);
        reff.child(mobile).child("walletBalance").setValue(0);

    }


    //retrieve all users from the db
    public ArrayList retrieveAllUsers() {
//        ArrayList<User> userList = new ArrayList<User>();
//        User dummyUser = new User();
//        dummyUser.setName("error");
//        userList.add(dummyUser);
//        ds.setUserList(userList);
        reff = FirebaseDatabase.getInstance().getReference().child("User");
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ds.getUserList().clear();
//                    ds.setUserCount(snapshot.getChildrenCount());
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        User user = dataSnapshot.getValue(User.class);
                        ds.getUserList().add(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return ds.getUserList();
        //use this to check if data has been retrieved: ArrayList<User> userList = ds.retrieveAllUsers(); while (userList.get(0).getName()==="error") {userList=ds.retrieveAllUsers();}

    }


    //retrieve user using their email from db
    public User retrieveUserByEmail(String email) {
//        User resetUser = new User();
//        resetUser.setName("error");
//        ds.setUser(resetUser);
        Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        User user = new User(snapshot.child("name").getValue().toString(), snapshot.child("email").getValue().toString(), snapshot.child("mobile").getValue().toString(), Float.parseFloat(snapshot.child("walletBalance").getValue().toString()), Float.parseFloat(snapshot.child("lateFees").getValue().toString()));
                        User user = dataSnapshot.getValue(User.class);
                        ds.setUser(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return ds.getUser();
    }


    //retrieve user using their mobile from db
    public User retrieveUserByMobile(String mobile) {
//        User resetUser = new User();
//        resetUser.setName("error");
//        ds.setUser(resetUser);
        Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("mobile").equalTo(mobile);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        User user = new User(snapshot.child("name").getValue().toString(), snapshot.child("email").getValue().toString(), snapshot.child("mobile").getValue().toString(), Float.parseFloat(snapshot.child("walletBalance").getValue().toString()), Float.parseFloat(snapshot.child("lateFees").getValue().toString()));
                        ds.setUser(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return ds.getUser();
        //use this to check if data has been retrieved: User user = ds.retriveUserByMobile("target mobile"); while (user.getName()==="error") {user=ds.retrieveUserByMobile("target mobile");}
    }


    //retrieve user's mobile using their email from db
    //NOTE!!! when calling this method, make sure to do what's stated beside the return "error"; statement!
    //need to do this bc retrieving the data is asynchronous so other code may run before the data is even retrieved
    //so must check that the data is retrieved before you use it!
    public String retrieveMobileByEmail(String email) {
        ds.setStr("error");
        Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("email").equalTo(email); //return the child node of "User" whose "email"==email
        //the .orderByChild() in the prev line doesnt affect where the snapshot will be pointed to!
        //snapshot will be the path ~\User in the db, NOT ~\User\[key of the user with "email"==email]\email
        //.orderByChild("email").equalTo(email) will give the snapshot of ~\User and only its children whose "email"==email will be included
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "Key of this snapshot is: "+snapshot.getKey()); //key (last part of the path of the location of the snapshot)=User
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String mobile = dataSnapshot.child("mobile").getValue().toString();
                        ds.setStr(mobile);
//                        Log.d(TAG, "mobile: " + ds.getStr());
                    }
                }
//                Log.d(TAG, "mobile: "+ds.getStr());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return ds.getStr();
        //use this to check if data has been retrieved: email=ds.retrieveMobileByEmail("target email"); while (email==="error") {email=ds.retrieveMobileByEmail("target email");}
    }


    //retrieve total number of bookings from db
    ////NOTE!!! when calling this method, make sure to do what's stated beside the return "error"; statement!
    public long retrieveBookingCount() {
        ds.setLongNum(-1);  //set as -1 to check if it has changed to the correct bookingCount
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
        //use this to check if data has been retrieved: count=ds.retrieveBookingCount(); while (count==="-1") {count=ds.retrieveBookingCount();}
    }





    //retrieve all lockers of a structure using structureID
//    public ArrayList<Locker> retrieveAvailLockers(int structureID) {
//        ArrayList<Locker> lockerList = new ArrayList<Locker>();
//        Locker dummyLocker = new Locker();
//        dummyLocker.setSize('E');
//        lockerList.add(dummyLocker);
//        ds.setLockerList(lockerList);
//        Query query = FirebaseDatabase.getInstance().getReference().child("LockerStructure").orderByChild("postalCode").equalTo(postalCode);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    ds.getStructureList().clear();
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        LockerStructure lockerStructure = dataSnapshot.getValue(LockerStructure.class);
//                        ds.getStructureList().add(lockerStructure);
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
//        return ds.getStructureList();
//        //use this to check if data has been retrieved: ArrayList<LockerStructure> structureList = ds.retrieveStructureByPostalCode("123456"); while (structureList.get(0).getAddress()==="error") {structureList=ds.retrieveStructureByPostalCode("123456");}
//    }





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

    public ArrayList<Booking> retrieveBookedBookingList(){
        //i dont think you need to have any parameters here
        //dummy method
        ArrayList<Booking> booking = new ArrayList<Booking>();
        return booking;
    }

    //pls return me a list of lockerID filtered by locker size thanks
    public ArrayList<Integer> retrieveLockerIDFilterByLockerSize(int lockerStructureID, int lockerSize){
        ArrayList<Integer> dummy  = new ArrayList<Integer>();
        return dummy;
    }


    //output: list of locker ids of lockers which match the desired size which are avail for the booking selection
    //input: startDate, endDate, startTime, endTime, postalCode, lockerSize

    //two db calls to retrieve:
    //1) bookings with b
    //retrieve all bookings with status=='B'
    public ArrayList<Booking> retrieveBBookings() {
        ds.setbBookingList(new ArrayList<Booking>());
        Query query = FirebaseDatabase.getInstance().getReference().child("Booking").orderByChild("status").equalTo("B");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Booking booking = dataSnapshot.getValue(Booking.class);
                        ds.getbBookingList().add(booking);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return ds.getbBookingList();
    }

    //2) lockerIDs of lockers from lockerStructure with postal code == postalCode and of locker size == lockerSize
    //retrieve all LockerStructures from db that has a certain postalCode --> need to change to get the lockers
    public ArrayList<Locker> retrieveStructureByPostalCode(String postalCode, char lockerSize) {
        ds.setLockerSize(lockerSize);
        Query query = FirebaseDatabase.getInstance().getReference().child("LockerStructure").orderByChild("postalCode").equalTo(postalCode);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d(TAG, "onDataChange: in onDataChange of first query");
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        long structureID = (long) dataSnapshot.child("structureID").getValue();
                        ds.setStructureID(structureID);
//                        Log.d(TAG, "onDataChange: End of first query");
//                        Log.d(TAG, "onDataChange: structureID = " +structureID+"");
//                        Log.d(TAG, "onDataChange: Locker structure: "+dataSnapshot.child("Locker").getValue());

                        ArrayList<HashMap<String, Object>> lockerList = new ArrayList<HashMap<String, Object>>();
                        lockerList = (ArrayList<HashMap<String, Object>>) dataSnapshot.child("Locker").getValue();
                        if (lockerList.get(0) == null) {
                            lockerList.remove(0);
                        }
//                        Log.d(TAG, "onDataChange: hashmap: " +lockerList.get(1).get("lockerID"));
                        for (HashMap<String, Object> locker : lockerList) {
                            if (locker.get("size").equals(Character.toString(ds.getLockerSize()))) {
                                Locker matchingLocker = new Locker();
                                matchingLocker.setStructureID(structureID);
                                matchingLocker.setLockerID((Long) locker.get("lockerID"));
                                ds.getLockerList().add(matchingLocker);
//                                Log.d(TAG, "structureID: " +matchingLocker.getStructureID()+"");
//                                Log.d(TAG, "lockerID: " +matchingLocker.getLockerID()+"");
                            }
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return ds.getLockerList();
        //use this to check if data has been retrieved: ArrayList<LockerStructure> structureList = ds.retrieveStructureByPostalCode("123456"); while (structureList.get(0).getAddress()==="error") {structureList=ds.retrieveStructureByPostalCode("123456");}
    }

}