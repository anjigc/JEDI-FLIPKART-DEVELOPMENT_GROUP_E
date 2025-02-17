package com.flipkart.business;

import com.flipkart.bean.FlipFitGymOwner;

/**
 * Interface for FlipFit Gym Owner Business Logic.
 * This interface defines various methods related to gym owner operations
 * such as registering gym owners, adding/removing gyms, and viewing the list of gyms.
 */
public interface FlipFitGymOwnerInterface {

    /**
     * Registers a new gym owner.
     * @param id The unique identifier of the gym owner.
     * @param panNo The PAN number of the gym owner.
     * @param address The address of the gym owner.
     * @param Aadhaar The Aadhaar number of the gym owner.
     * @return The registered FlipFitGymOwner object.
     */
    public FlipFitGymOwner registerGymOwner(int id, String panNo, String address, String Aadhaar);

    /**
     * Adds a new gym for the gym owner.
     * @param ownerId The unique identifier of the gym owner.
     */
    public void addGym(int ownerId);

    /**
     * Removes a gym for the gym owner.
     */
    public void removeGym();

    /**
     * Displays the list of gyms owned by the gym owner.
     * @param ownerId The unique identifier of the gym owner.
     */
    public void viewGymList(int ownerId);
}
