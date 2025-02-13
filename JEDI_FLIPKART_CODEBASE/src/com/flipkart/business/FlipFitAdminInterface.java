package com.flipkart.business;

import com.flipkart.bean.Admin;

public interface FlipFitAdminInterface {
    public Admin registerAdmin(String name, String email, String password, String contact);
    public void approveGym();
    public void rejectGym();
    public void viewGymStatus();
    public void approveGymOwner();
    public void rejectGymOwner();
    public void viewGymOwnerStatus();
}
