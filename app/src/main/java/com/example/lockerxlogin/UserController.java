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

    DatabaseController dc = new DatabaseController();
    BookingController bc = new BookingController();

    public UserController() {}


    //method to create booking using the booking controller
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean makeBooking(long structureID,
                               long lockerID, LocalDate startDate, LocalTime startTime,
                            LocalDate endDate, LocalTime endTime, char size){

        //deduct late fees first when making a booking
        if(Login.currUser.getLateFees()>0){
            if(makePayment(Login.currUser.getLateFees())==false){
                return false;
            }
        }

        float rentalFees = bc.calculateRentalFees(structureID, lockerID, startDate, startTime,endDate,endTime,size);
        if(makePayment(rentalFees)==true){
            bc.makeBooking(Login.currUser.getEmail(),structureID,lockerID,startDate,startTime,endDate,endTime);
            //creates a new booking object and stores it in database using database controller
            return true;
        }
        else
            return false;
    }


    public boolean makePayment(float paymentAmount){
        float walletBalance = Login.currUser.getWalletBalance();
        if(walletBalance-paymentAmount>=0){
            Login.currUser.setWalletBalance(walletBalance-paymentAmount);
            dc.updateWalletBalance(Login.currUser.getEmail(), Login.currUser.getWalletBalance());
            return true;
        }
        else
            return false;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean returnLocker(LocalDate startDate,
                                LocalTime startTime, LocalDate endDate, LocalTime endTime,
                                long structureID, long lockerID){

        //retrieve status of booking, 1=booking expired, 2=booking in-progress, 3=reservation
        int status = bc.checkExpiredBooking(startDate,startTime,endDate,endTime);
        if(status==1){
            float lateFees = calculateLateFees(endDate,endTime);
            Login.currUser.setLateFees(Login.currUser.getLateFees()+lateFees);

            //set booking status to 'R', returned
            dc.setBookingStatus(Login.currUser.getEmail(), structureID, lockerID, startDate,
                    startTime, endDate, endTime, 'R');
            return true;
        }
        else if(status==2){
            dc.setBookingStatus(Login.currUser.getEmail(), structureID, lockerID, startDate,
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Booking> getUserLockers(String mobile) {
        //start thread + loading screen
        ArrayList<Booking> userOBookings = new ArrayList<Booking>();
        ArrayList<Booking> userFBookings = new ArrayList<Booking>();
        ArrayList<Booking> userBBookings = dc.retrieveBBookingsForUser(mobile);
        ArrayList<Booking> userRBookings = dc.retrieveRBookingsForUser(mobile);
        ArrayList<Booking> userCBookings = dc.retrieveCBookingsForUser(mobile);
        //end thread

        LocalDate currDate = java.time.LocalDate.now();
        LocalTime currTime = java.time.LocalTime.now();

        //filter B bookings into ongoing (O) and future (F) bookings
        for (Booking booking : userBBookings) {
            if ((currDate.compareTo(booking.getStartDate())>=0) && (currDate.compareTo(booking.getEndDate())<=0)) {
                if ((currTime.compareTo(booking.getStartTime())>=0) && (currTime.compareTo(booking.getEndTime())<=0)) {
                    booking.setStatus('O');
                    userOBookings.add(booking);
                }
            }
            else {
                userFBookings.add(booking);
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

    public void updateUserName (String newName) {
        dc.setNewName(Login.currUser.getMobile(), newName);
        Login.currUser.setName(newName);
    }

    public void updateUserEmail (String newEmail) {
        dc.setNewEmail(Login.currUser.getMobile(), newEmail);
        Login.currUser.setEmail(newEmail);
    }

    public void updateUserMobile (String newMobile) {
        dc.setNewMobile(Login.currUser.getMobile(), Login.currUser.getName(), Login.currUser.getEmail(), newMobile,
                Login.currUser.getWalletBalance(), Login.currUser.getLateFees());
        Login.currUser.setMobile(newMobile);
    }

    public void updateUserWalletBalance (float newBalance) {
        dc.updateWalletBalance(Login.currUser.getMobile(), newBalance);
        Login.currUser.setWalletBalance(newBalance);
    }

    public void updateUserLateFees (float newLateFees) {
        dc.updateLateFees(Login.currUser.getMobile(), newLateFees);
        Login.currUser.setLateFees(newLateFees);
    }

}
