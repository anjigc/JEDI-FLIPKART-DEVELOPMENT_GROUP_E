package com.flipkart.business;

import com.flipkart.bean.Admin;

public class AdminService {

    public boolean approveGym(int gymId) {
        System.out.println("Gym with ID: " + gymId + " has been approved.");
        return true;
    }

    public boolean rejectGym(int gymId) {
        System.out.println("Gym with ID: " + gymId + " has been rejected.");
        return true;
    }

    public boolean removeGym(int gymId) {
        System.out.println("Gym with ID: " + gymId + " has been removed.");
        return true;
    }

    public boolean approveOwner(int ownerId) {
        System.out.println("Owner with ID: " + ownerId + " has been approved.");
        return true;
    }

    public boolean rejectOwner(int ownerId) {
        System.out.println("Owner with ID: " + ownerId + " has been rejected.");
        return true;
    }

    public boolean removeOwner(int ownerId) {
        System.out.println("Owner with ID: " + ownerId + " has been removed.");
        return true;
    }
}
