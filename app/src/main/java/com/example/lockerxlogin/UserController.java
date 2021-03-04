package com.example.lockerxlogin;

public class UserController {
    private User currentUser;

    public UserController(User user){
        this.currentUser = user;
    }
    public boolean lockOrUnlock(LockerController lc, DatabaseController dc){
        return lc.lockOrUnlock(dc);
    }

    //method to create booking using the booking controller
    public void makeBooking(DatabaseController dc, BookingController bc,String lockerStructureID,
                               String lockerID, java.time.LocalDate startDate, java.time.LocalTime startTime,
                            java.time.LocalDate endDate,java.time.LocalTime endTime){

        bc.makeBooking(dc,currentUser.getEmail(),lockerStructureID,lockerID,startDate,startTime,endDate,endTime);
        //creates a new booking object and stores it in database using database controller
    }

    public void returnLocker(BookingController bc){

    }
}
