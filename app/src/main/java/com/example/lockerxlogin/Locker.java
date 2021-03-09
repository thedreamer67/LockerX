package com.example.lockerxlogin;
import java.util.ArrayList;

public class Locker extends LockerStructure {
    private int lockerID;
    private char size;  //'S'=small, 'M'=medium, 'L'=large
    private boolean status; // False = Unlocked, True = Locked
    //private ArrayList<Booking> booking;

    public Locker(){} //empty constructor

    public Locker(int structureID, int locationID, String location, String postalCode, String address, int lockerID, char size){
        super.structureID = structureID;
        super.locationID = locationID;
        super.location = location;
        super.postalCode = postalCode;
        super.address = address;
        this.lockerID = lockerID;
        this.size = size;
        //this.booking = new ArrayList<Booking>();
        this.status = false;
    }

    public void setLockerID(int lockerID){
        this.lockerID = lockerID;
    }
    public int getLockerID(){
        return this.lockerID;
    }

    public void setSize(char size){
        this.size = size;
    }
    public char getSize(){
        return this.size;
    }

//    public ArrayList<Booking> getBooking() {
//        return booking;
//    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setStatus(){
        if(this.status==false)
            this.status=true;
        else
            this.status=false;
    }
    public boolean getStatus(){
        return this.status;
    }
}
