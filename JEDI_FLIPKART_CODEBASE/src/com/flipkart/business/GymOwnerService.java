package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;

public class GymOwnerService {
	
	public GymOwner registerGymOwner(String name, String email, String password, String contact, String panNo, String address, String Aadhaar) {
		GymOwner gymOwner = new GymOwner();
		gymOwner.setName(name);
		gymOwner.setEmail(email);
		gymOwner.setPassword(password);
		gymOwner.setContact(contact);
		gymOwner.setPanNo(panNo);
		gymOwner.setAddress(address);
		gymOwner.setAadhaar(Aadhaar);
		gymOwner.setRole("FlipFit Gym Owner");

        System.out.println("User with email " + email + " registered as FLipfit Gym Owner Successfully!");

        return gymOwner;
    }
	
    public boolean addGym(Gym gym, GymOwner gymOwner) {
        // Logic to add a new gym
        return true;
    }

    public boolean updateGym(Gym gym) {
        // Logic to update gym details
        return true;
    }

    public List<Gym> getGymsByOwnerId(int ownerId) {
        // Logic to get list of gyms by owner ID
        return null;
    }
}