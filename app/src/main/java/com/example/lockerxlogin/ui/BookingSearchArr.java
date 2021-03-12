package com.example.lockerxlogin.ui;

public class BookingSearchArr {
    private String bookingId, endDate, endTime, lockerID, mobile, startDate, startTime, status, structureID;

    public BookingSearchArr(String bookingId, String endDate, String endTime, String lockerID, String mobile, String startDate, String startTime, String status, String structureID) {
        this.bookingId = bookingId;
        this.endDate = endDate;
        this.endTime = endTime;
        this.lockerID = lockerID;
        this.mobile = mobile;
        this.startDate = startDate;
        this.startTime = startTime;
        this.status = status;
        this.structureID = structureID;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLockerID() {
        return lockerID;
    }

    public String getMobile() {
        return mobile;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStatus() {
        return status;
    }

    public String getStructureID() {
        return structureID;
    }


}
