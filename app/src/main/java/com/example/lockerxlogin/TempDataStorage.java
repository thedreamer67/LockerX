package com.example.lockerxlogin;

import java.lang.reflect.Array;
import java.util.ArrayList;

//this class is for retrieving data and being able to use the data outside of the onDataChange() method
public class TempDataStorage {
    private String str;
    private long longNum;
    private ArrayList<User> userList = new ArrayList<User>();
    private User user;
    private long userCount;
    private ArrayList<LockerStructure> structureList = new ArrayList<LockerStructure>();
    private ArrayList<Locker> lockerList = new ArrayList<Locker>();
    private char lockerSize;
    private long structureID;
    private ArrayList<Booking> bBookingList = new ArrayList<Booking>();


    public TempDataStorage() {
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public long getLongNum() {
        return longNum;
    }

    public void setLongNum(long longNum) {
        this.longNum = longNum;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public void clearUserList(ArrayList<User> userList) {
        this.userList.clear();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public ArrayList<LockerStructure> getStructureList() {
        return structureList;
    }

    public void setStructureList(ArrayList<LockerStructure> structureList) {
        this.structureList = structureList;
    }

    public ArrayList<Locker> getLockerList() {
        return lockerList;
    }

    public void setLockerList(ArrayList<Locker> lockerList) {
        this.lockerList = lockerList;
    }

    public char getLockerSize() {
        return lockerSize;
    }

    public void setLockerSize(char lockerSize) {
        this.lockerSize = lockerSize;
    }

    public long getStructureID() {
        return structureID;
    }

    public void setStructureID(long structureID) {
        this.structureID = structureID;
    }

    public ArrayList<Booking> getbBookingList() {
        return bBookingList;
    }

    public void setbBookingList(ArrayList<Booking> bBookingList) {
        this.bBookingList = bBookingList;
    }
}
