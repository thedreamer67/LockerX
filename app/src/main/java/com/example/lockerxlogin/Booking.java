package com.example.lockerxlogin;

public class Booking {
    private java.time.LocalDate startDate;
    private java.time.LocalTime startTime;
    private java.time.LocalDate endDate;
    private java.time.LocalTime endTime;

    public Booking(){} //empty constructor

    public Booking(java.time.LocalDate startDate, java.time.LocalTime startTime,
                   java.time.LocalDate endDate, java.time.LocalTime endTime){
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
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
}
