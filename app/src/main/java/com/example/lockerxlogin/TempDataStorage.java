package com.example.lockerxlogin;

import java.util.ArrayList;

//this class is for retrieving data and being able to use the data outside of the onDataChange() method
public class TempDataStorage {
    private String str;
    private long longNum;
    private ArrayList<User> userList = new ArrayList<User>();
    private User user;
    private long userCount;

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
}
