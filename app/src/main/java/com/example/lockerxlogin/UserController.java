package com.example.lockerxlogin;

import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserController {
    private User currentUser;

    public UserController(User user){
        this.currentUser = user;
    }
    public boolean lockOrUnlock(LockerController lc, DatabaseController dc){
        return lc.lockOrUnlock(dc);
    }

    //method to create booking using the booking controller
    public void makeBooking(DatabaseController dc, BookingController bc, int lockerStructureID,
                            int lockerID, LocalDate startDate, LocalTime startTime,
                            LocalDate endDate, LocalTime endTime){

        bc.makeBooking(dc,currentUser.getEmail(),lockerStructureID,lockerID,startDate,startTime,endDate,endTime);
        //creates a new booking object and stores it in database using database controller
        makeRentalPayment();
    }
    public boolean makePayment(DatabaseController dc, float paymentAmount){
        float walletBalance = this.currentUser.getWalletBalance();
        if(walletBalance-paymentAmount>=0){
            this.currentUser.setWalletBalance(walletBalance-paymentAmount);
            dc.
            return true;
        }
        else
            return false;
    }
    public void returnLocker(BookingController bc){

    }

    //method to top up wallet balance
//    public void topUpWallet(int topUpAmount, DatabaseController dc) {
//        float newBalance = currentUser.getWalletBalance()+topUpAmount;
//        currentUser.setWalletBalance(newBalance);
//        dc.updateWalletBalance(currentUser.getEmail(), newBalance);
//    }
}
