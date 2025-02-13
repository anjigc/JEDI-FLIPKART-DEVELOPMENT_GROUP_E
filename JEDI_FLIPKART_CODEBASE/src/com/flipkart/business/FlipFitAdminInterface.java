package com.flipkart.business;

import com.flipkart.bean.FlipFitAdmin;

public interface FlipFitAdminInterface {
    public FlipFitAdmin registerAdmin(int id);
    public void approveGym();
    public void rejectGym();
    public void viewGymStatus();
    public void approveGymOwner();
    public void rejectGymOwner();
    public void viewGymOwnerStatus();
}
