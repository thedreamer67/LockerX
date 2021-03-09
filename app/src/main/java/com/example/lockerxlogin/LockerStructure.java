package com.example.lockerxlogin;

public class LockerStructure {
    protected int structureID;
    protected int locationID;
    protected String location;
    protected String postalCode;
    protected String address;

    public LockerStructure(){} //empty constructor

    public LockerStructure(int structureID, int locationID, String location){
        this.structureID = structureID;
        this.locationID = structureID;
        this.location = location;
    }
    public void setStructureID(int lockerID){
        this.structureID = lockerID;
    }
    public int getStructureID(){
        return this.structureID;
    }

    public void setLocationID(int lockerID){
        this.locationID = locationID;
    }
    public int getLocationID(){
        return this.locationID;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }

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
