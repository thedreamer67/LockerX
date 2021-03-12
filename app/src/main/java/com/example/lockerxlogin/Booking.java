package com.example.lockerxlogin;

public class Booking {
    private java.time.LocalDate startDate;
    private java.time.LocalTime startTime;
    private java.time.LocalDate endDate;
    private java.time.LocalTime endTime;
    private String email;
    private int lockerStructureID;
    private int lockerID;
    private char status;    //'B'=booked, 'R'=returned, 'C'=cancelled

    public Booking(){} //empty constructor

    public Booking(java.time.LocalDate startDate, java.time.LocalTime startTime,
                   java.time.LocalDate endDate, java.time.LocalTime endTime, String email,
                   int lockerStructureID, int lockerID, char status){
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.email = email;
        this.lockerStructureID = lockerStructureID;
        this.lockerID = lockerID;
        this.status = status;
    }

    public void setStartDate(java.time.LocalDate startDate){
        this.startDate = startDate;
    }
    public java.time.LocalDate getStartDate(){
        return this.startDate;
    }

    public void setStartTime(java.time.LocalTime startTime){
        this.startTime = startTime;
    }
    public java.time.LocalTime getStartTime(){
        return this.startTime;
    }

    public void setEndDate(java.time.LocalDate endDate){
        this.endDate = endDate;
    }
    public java.time.LocalDate getEndDate(){
        return this.endDate;
    }

    public void setEndTime(java.time.LocalTime endTime){
        this.endTime = endTime;
    }
    public java.time.LocalTime getEndTime(){
        return this.endTime;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){ return this.email; }

    public void setLockerStructureID(int lockerStructureID){
        this.lockerStructureID = lockerStructureID;
    }
    public int getLockerStructureID(){
        return this.lockerStructureID;
    }

    public void setLockerID(int lockerID){
        this.lockerID = lockerID;
    }
    public int getLockerID(){
        return this.lockerID;
    }

    public void setStatus(char status){
        this.status = status;
    }
    public char getStatus(){
        return this.status;
    }
}
