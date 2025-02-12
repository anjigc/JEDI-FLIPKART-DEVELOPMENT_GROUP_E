package com.flipkart.bean;

import java.util.List;

public class GymOwner extends User{
    private String address;
    private String panNo;
    private String Aadhaar;
    private List<Gym> gyms;

    // Getters and Setters
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPanNo() {
        return panNo;
    }
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getAadhaar() {
        return Aadhaar;
    }
    public void setAadhaar(String aadhaar) {
        this.Aadhaar = aadhaar;
    }

    public List<Gym> getGyms() {
        return gyms;
    }
    public void setGyms(List<Gym> gyms) {
        this.gyms = gyms;
    }
}
