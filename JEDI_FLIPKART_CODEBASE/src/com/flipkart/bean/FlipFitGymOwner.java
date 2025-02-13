package com.flipkart.bean;

public class FlipFitGymOwner {
    private int id;
    private String address;
    private String panNo;
    private String Aadhaar;

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
}
