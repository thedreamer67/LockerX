package com.example.lockerxlogin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserController {


    private User currentUser;

    DatabaseController dc = new DatabaseController();

    public UserController(User user){
        this.currentUser = user;
    }
//    public boolean lockOrUnlock(LockerController lc, DatabaseController dc){
//        return lc.lockOrUnlock(dc);
//    }

    //method to create booking using the booking controller
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean makeBooking(DatabaseController dc, BookingController bc, long structureID,
                               long lockerID, LocalDate startDate, LocalTime startTime,
                            LocalDate endDate, LocalTime endTime){

        //deduct late fees first when making a booking
        if(currentUser.getLateFees()>0){
            if(makePayment(dc,currentUser.getLateFees())==false){
                return false;
            }
        }

        float rentalFees = bc.calculateRentalFees(dc,structureID, lockerID, startDate,
                startTime,endDate,endTime);
        if(makePayment(dc, rentalFees)==true){
            bc.makeBooking(dc,currentUser.getEmail(),structureID,lockerID,startDate,startTime,endDate,endTime);
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
                                long structureID, long lockerID){

        //retrieve status of booking, 1=booking expired, 2=booking in-progress, 3=reservation
        int status = bc.checkExpiredBooking(startDate,startTime,endDate,endTime);
        if(status==1){
            float lateFees = calculateLateFees(endDate,endTime);
            this.currentUser.setLateFees(this.currentUser.getLateFees()+lateFees);

            //set booking status to 'R', returned
            dc.setBookingStatus(this.currentUser.getEmail(), structureID, lockerID, startDate,
                    startTime, endDate, endTime, 'R');
            return true;
        }
        else if(status==2){
            dc.setBookingStatus(this.currentUser.getEmail(), structureID, lockerID, startDate,
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

    //returns the ArrayList of Bookings of the user with mobile==mobile
    //order: O (ongoing), F (future), R (history), C (cancelled)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Booking> getUserLockers(String mobile) {
        //start thread + loading screen
        ArrayList<Booking> userOBookings = dc.retrieveOBookingsForUser(mobile);
        ArrayList<Booking> userFBookings = dc.retrieveBBookingsForUser(mobile);
        ArrayList<Booking> userRBookings = dc.retrieveRBookingsForUser(mobile);
        ArrayList<Booking> userCBookings = dc.retrieveCBookingsForUser(mobile);
        //end thread

        for (Booking booking : userOBookings) {
            if (userFBookings.contains(booking)) {
                userFBookings.remove(booking);
            }
            else {
                booking.setStatus('O');
            }
        }



        //sort all the sub-lists using startDate, then startTime
        Comparator<Booking> bookingComparator = Comparator.comparing(Booking::getStartDate).thenComparing(Booking::getStartTime);
        Collections.sort(userOBookings, bookingComparator);
        Collections.sort(userFBookings, bookingComparator);
        Collections.sort(userRBookings, bookingComparator);
        Collections.sort(userCBookings, bookingComparator);


        ArrayList<Booking> userBookings = new ArrayList<Booking>();
        //combine all 4 lists into userBookings list
        userBookings.addAll(userOBookings);
        userBookings.addAll(userFBookings);
        userBookings.addAll(userRBookings);
        userBookings.addAll(userCBookings);
        return userBookings;

    }

}
