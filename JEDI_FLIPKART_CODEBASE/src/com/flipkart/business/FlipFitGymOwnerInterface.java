package com.flipkart.business;

import com.flipkart.bean.FlipFitGymOwner;

public interface FlipFitGymOwnerInterface {
    public FlipFitGymOwner registerGymOwner(int id, String panNo, String address, String Aadhaar);
    public void addGym(int ownerId);
    public void removeGym();
    public void viewGymList();
}
