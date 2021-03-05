package com.example.lockerxlogin;

import java.util.ArrayList;

public class User implements Account {
    private String name;
    private String email;
    private String password;
    private float walletBalance;
    private float lateFees;
    private long mobile;
    //private ArrayList<Booking> booking;

    public User(){} //empty constructor

    public User(String name, String email, String password, long mobile, float walletBalance, float lateFees){
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.walletBalance = walletBalance;
        this.lateFees = lateFees;
        //this.booking = new ArrayList<Booking>();
    }

    public User(String name, String email, long mobile){
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        //this.booking = new ArrayList<Booking>();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setMobile(long mobile) {this.mobile=mobile;}
    public long getMobile() {return this.mobile;}

    public void setWalletBalance(float walletBalance) {
        this.walletBalance = walletBalance;
    }
    public float getWalletBalance() {
        return this.walletBalance;
    }

    public void setLateFees(float lateFees) {
        this.lateFees = lateFees;
    }
    public float getLateFees() {
        return this.lateFees;
    }

//    public ArrayList<Booking> getBooking() {
//        return this.booking;
//    }
//    public void insertBooking(int index, Booking booking) {
//        this.booking.add(index,booking);
//    }
}
