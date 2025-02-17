package com.flipkart.business;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.exception.*;

/**
 * Interface for FlipFit Admin Business Logic.
 * This interface defines various methods related to admin operations like 
 * registering admins, approving/rejecting gyms, and viewing gym or gym owner status.
 */
public interface FlipFitAdminInterface {

    /**
     * Registers a new admin.
     * @param id The unique identifier of the admin.
     * @return A FlipFitAdmin object representing the registered admin.
     */
    public FlipFitAdmin registerAdmin(int id);

    /**
     * Approves a gym application.
     * @throws GymNotFoundException If the gym is not found.
     * @throws DatabaseException If there's an issue with the database.
     */
    public void approveGym() throws GymNotFoundException, DatabaseException;

    /**
     * Rejects a gym application.
     * @throws GymNotFoundException If the gym is not found.
     * @throws DatabaseException If there's an issue with the database.
     */
    public void rejectGym() throws GymNotFoundException, DatabaseException;

    /**
     * Displays the status of all gyms.
     * @throws GymStatusNotFoundException If no gym status is found.
     * @throws DatabaseException If there's an issue with the database.
     */
    public void viewGymStatus() throws GymStatusNotFoundException, DatabaseException;

    /**
     * Approves a gym owner application.
     * @throws GymOwnerNotFoundException If the gym owner is not found.
     * @throws DatabaseException If there's an issue with the database.
     */
    public void approveGymOwner() throws GymOwnerNotFoundException, DatabaseException;

    /**
     * Rejects a gym owner application.
     * @throws GymOwnerNotFoundException If the gym owner is not found.
     * @throws DatabaseException If there's an issue with the database.
     */
    public void rejectGymOwner() throws GymOwnerNotFoundException, DatabaseException;

    /**
     * Displays the status of all gym owners.
     * @throws GymOwnerNotFoundException If no gym owner status is found.
     * @throws DatabaseException If there's an issue with the database.
     */
    public void viewGymOwnerStatus() throws GymOwnerNotFoundException, DatabaseException;
}
