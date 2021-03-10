package com.example.lockerxlogin;

public class LockerStructure {
    protected int structureID;
//    protected int locationID;
    protected String postalCode;
    protected String address;

    public LockerStructure(){} //empty constructor

    public LockerStructure(int structureID, String postalCode, String address){
        this.structureID = structureID;
        this.postalCode = postalCode;
        this.address = address;
    }
    public void setStructureID(int structureID){
        this.structureID = structureID;
    }
    public int getStructureID(){
        return this.structureID;
    }

//    public void setLocationID(int lockerID){
//        this.locationID = locationID;
//    }
//    public int getLocationID(){
//        return this.locationID;
//    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
