package com.flipkart.business;

import com.flipkart.bean.FlipFitGymOwner;

public interface FlipFitGymOwnerInterface {
    public FlipFitGymOwner registerGymOwner(String name, String email, String password, String contact, String panNo, String address, String Aadhaar);
    public void addGym();
    public void removeGym();
    public void viewGymList();
    public void loginGymOwner(String email, String password);
    public void logoutGymOwner(String email);
}
