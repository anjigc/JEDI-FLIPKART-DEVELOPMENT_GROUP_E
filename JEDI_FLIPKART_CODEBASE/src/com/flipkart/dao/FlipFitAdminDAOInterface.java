package com.flipkart.dao;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Interface for FlipFit Admin DAO (Data Access Object).
 * This interface defines methods for admin-related operations 
 * such as registering admins, approving or rejecting gyms and gym owners, 
 * and viewing their approval statuses.
 */
public interface FlipFitAdminDAOInterface {

    /**
     * Registers a new admin in the system.
     * @param admin The FlipFitAdmin object containing admin details.
     * @throws SQLException If a database access error occurs.
     */
    public void registerAdmin(FlipFitAdmin admin) throws SQLException;

    /**
     * Approves a gym based on the provided gym ID.
     * @param gymId The unique identifier of the gym to be approved.
     * @throws SQLException If a database access error occurs.
     */
    public void approveGym(int gymId) throws SQLException;

    /**
     * Rejects a gym application based on the provided gym ID.
     * @param gymId The unique identifier of the gym to be rejected.
     * @throws SQLException If a database access error occurs.
     */
    public void rejectGym(int gymId) throws SQLException;

    /**
     * Retrieves the list of all gym centers along with their approval statuses.
     * @return A list of FlipFitGymCentre objects representing the gym centers.
     * @throws SQLException If a database access error occurs.
     */
    public List<FlipFitGymCentre> viewGymStatus() throws SQLException;

    /**
     * Approves a gym owner based on the provided owner ID.
     * @param ownerId The unique identifier of the gym owner to be approved.
     * @throws SQLException If a database access error occurs.
     */
    public void approveGymOwner(int ownerId) throws SQLException;

    /**
     * Rejects a gym owner application based on the provided owner ID.
     * @param ownerId The unique identifier of the gym owner to be rejected.
     * @throws SQLException If a database access error occurs.
     */
    public void rejectGymOwner(int ownerId) throws SQLException;

    /**
     * Retrieves the list of all gym owners along with their approval statuses.
     * @return A map where the key is the FlipFitGymOwner object and the value is the approval status.
     * @throws SQLException If a database access error occurs.
     */
    public Map<FlipFitGymOwner, String> viewGymOwnerStatus() throws SQLException;
}
