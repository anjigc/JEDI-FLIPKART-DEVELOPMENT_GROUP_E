package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for FlipFit Gym Owner DAO (Data Access Object).
 * This interface defines methods for gym owner-related operations 
 * such as registering gym owners, managing gyms, adding/removing gym slots, 
 * and viewing gym details.
 */
public interface FlipFitGymOwnerDAOInterface {

    /**
     * Registers a new gym owner in the system.
     * @param gymOwner The FlipFitGymOwner object containing gym owner details.
     * @throws SQLException If a database access error occurs.
     */
    public void registerGymOwner(FlipFitGymOwner gymOwner) throws SQLException;

    /**
     * Adds a new gym to the system.
     * @param gymCentre The FlipFitGymCentre object containing gym details.
     * @throws SQLException If a database access error occurs.
     */
    public void addGym(FlipFitGymCentre gymCentre) throws SQLException;

    /**
     * Removes a gym from the system based on the provided gym ID.
     * @param gymId The unique identifier of the gym to be removed.
     * @throws SQLException If a database access error occurs.
     */
    public void removeGym(int gymId) throws SQLException;

    /**
     * Retrieves a list of all gyms owned by a specific gym owner.
     * @param ownerId The unique identifier of the gym owner.
     * @return A list of FlipFitGymCentre objects representing the gyms owned by the owner.
     * @throws SQLException If a database access error occurs.
     */
    public List<FlipFitGymCentre> viewGymList(int ownerId) throws SQLException;

    /**
     * Adds a new slot to a gym.
     * @param slot The FlipFitSlot object containing slot details.
     * @throws SQLException If a database access error occurs.
     */
    public void addGymSlot(FlipFitSlot slot) throws SQLException;

    /**
     * Removes a gym slot from the system based on the provided slot ID.
     * @param slotId The unique identifier of the slot to be removed.
     * @throws SQLException If a database access error occurs.
     */
    public void removeGymSlot(int slotId) throws SQLException;

    /**
     * Retrieves the list of slots available for a specific gym.
     * @param gymId The unique identifier of the gym.
     * @return A list of FlipFitSlot objects representing available slots in the gym.
     * @throws SQLException If a database access error occurs.
     */
    public List<FlipFitSlot> viewGymSlots(int gymId) throws SQLException;
}
