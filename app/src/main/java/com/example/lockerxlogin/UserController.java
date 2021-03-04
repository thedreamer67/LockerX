package com.example.lockerxlogin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class UserController {
    private User currentUser;

    public UserController(User user){
        this.currentUser = user;
    }
    public boolean lockOrUnlock(LockerController lc, DatabaseController dc){
        return lc.lockOrUnlock(dc);
    }

    //method to create booking using the booking controller
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean makeBooking(DatabaseController dc, BookingController bc, int lockerStructureID,
                            int lockerID, LocalDate startDate, LocalTime startTime,
                            LocalDate endDate, LocalTime endTime){

        //deduct late fees first when making a booking
        if(currentUser.getLateFees()>0){
            if(makePayment(dc,currentUser.getLateFees())==false){
                return false;
            }
        }

        float rentalFees = bc.calculateRentalFees(dc,lockerStructureID, lockerID, startDate,
                startTime,endDate,endTime);
        if(makePayment(dc, rentalFees)==true){
            bc.makeBooking(dc,currentUser.getEmail(),lockerStructureID,lockerID,startDate,startTime,endDate,endTime);
            //creates a new booking object and stores it in database using database controller
            return true;
        }
        else
            return false;
    }
    public boolean makePayment(DatabaseController dc, float paymentAmount){
        float walletBalance = this.currentUser.getWalletBalance();
        if(walletBalance-paymentAmount>=0){
            this.currentUser.setWalletBalance(walletBalance-paymentAmount);
            dc.updateWalletBalance(this.currentUser.getEmail(),this.currentUser.getWalletBalance());
            return true;
        }
        else
            return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean returnLocker(DatabaseController dc, BookingController bc, LocalDate startDate,
                                LocalTime startTime, LocalDate endDate, LocalTime endTime,
                                int lockerStructureID, int lockerID){

        //retrieve status of booking, 1=booking expired, 2=booking in-progress, 3=reservation
        int status = bc.checkExpiredBooking(startDate,startTime,endDate,endTime);
        if(status==1){
            float lateFees = calculateLateFees(endDate,endTime);
            this.currentUser.setLateFees(this.currentUser.getLateFees()+lateFees);

            //set booking status to 'R', returned
            dc.setBookingStatus(this.currentUser.getEmail(), lockerStructureID, lockerID, startDate,
                    startTime, endDate, endTime, 'R');
            return true;
        }
        else if(status==2){
            dc.setBookingStatus(this.currentUser.getEmail(), lockerStructureID, lockerID, startDate,
                    startTime, endDate, endTime, 'R');
            return true;
        }
        else
            return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public float calculateLateFees(LocalDate endDate, LocalTime endTime){

        //merging date and time to datetime format
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.of(endDate,endTime);

        //calculating difference between start and end in minutes
        long differenceInMinutes = ChronoUnit.MINUTES.between(endDateTime,currentDateTime);

        //buffer time of 15minutes
        if(differenceInMinutes>15){
            float lateFees = (float) (differenceInMinutes*0.1); //10 cents per minute
            return lateFees;
        }
        else
            return 0;
    }

    //method to top up wallet balance
//    public void topUpWallet(int topUpAmount, DatabaseController dc) {
//        float newBalance = currentUser.getWalletBalance()+topUpAmount;
//        currentUser.setWalletBalance(newBalance);
//        dc.updateWalletBalance(currentUser.getEmail(), newBalance);
//    }
}
