package com.example.lockerxlogin;

//this class is for retrieving data and being able to use the data outside of the onDataChange() method
public class TempDataStorage {
    private String str;
    private long longNum;

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
}
