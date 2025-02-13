package com.flipkart.business;

import com.flipkart.bean.GymOwner;

public interface FlipFitGymOwnerInterface {
    public GymOwner registerGymOwner(String name, String email, String password, String contact, String panNo, String address, String Aadhaar);
    public void addGym();
    public void removeGym();
    public void viewGymList();
}
