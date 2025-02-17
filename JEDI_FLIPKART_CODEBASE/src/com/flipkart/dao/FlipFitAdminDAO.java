package com.flipkart.dao;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.constant.FlipFitConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flipkart.utils.FlipFitDBConnection;

/**
 * The {@code FlipFitAdminDAO} class provides the implementation for the data access layer responsible for performing
 * database operations related to FlipFitAdmin functionalities. It includes operations such as registering admins,
 * approving or rejecting gym centers and gym owners, and viewing the status of gyms and gym owners.
 * <p>
 * This class uses SQL queries defined in the {@link FlipFitConstants} class to interact with the database.
 * </p>
 */
public class FlipFitAdminDAO implements FlipFitAdminDAOInterface {
    private Connection connection;

    /**
     * Constructor that initializes the database connection.
     * Uses {@link FlipFitDBConnection#getConnection()} to obtain a connection to the database.
     */
    public FlipFitAdminDAO() {
        this.connection = FlipFitDBConnection.getConnection();
    }

    /**
     * Registers a new admin in the system.
     *
     * @param admin The {@link FlipFitAdmin} object containing the admin's details to be registered.
     * @throws SQLException If a database error occurs during registration.
     */
    @Override
    public void registerAdmin(FlipFitAdmin admin) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_ADMIN_REGISTER)) {
            stmt.setInt(1, admin.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Approves a gym center by updating its status to 'Approved' in the database.
     *
     * @param gymId The ID of the gym to be approved.
     * @throws SQLException If a database error occurs or if no gym is found with the given Gym ID.
     */
    @Override
    public void approveGym(int gymId) throws SQLException {
        int rowsAffected = 0;
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_APPROVE)) {
            stmt.setInt(1, gymId);
            rowsAffected = stmt.executeUpdate();
        }

        if (rowsAffected == 0) {
            throw new SQLException("No gym was approved with the given Gym ID: " + gymId);
        }
    }

    /**
     * Rejects a gym center by updating its status to 'Rejected' in the database.
     *
     * @param gymId The ID of the gym to be rejected.
     * @throws SQLException If a database error occurs during rejection.
     */
    @Override
    public void rejectGym(int gymId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_REJECT)) {
            stmt.setInt(1, gymId);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves the status of all gym centers.
     *
     * @return A list of {@link FlipFitGymCentre} objects representing the gym centers with their details (gymId, gymName, status).
     * @throws SQLException If a database error occurs during retrieval.
     */
    @Override
    public List<FlipFitGymCentre> viewGymStatus() throws SQLException {
        List<FlipFitGymCentre> gymList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_LIST_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FlipFitGymCentre gym = new FlipFitGymCentre();
                gym.setGymId(rs.getInt("gymId"));
                gym.setGymName(rs.getString("gymName"));
                gym.setStatus(rs.getString("status"));
                gymList.add(gym);
            }
        }
        return gymList;
    }

    /**
     * Approves a gym owner by updating their status to 'Approved' in the database.
     *
     * @param ownerId The ID of the gym owner to be approved.
     * @throws SQLException If a database error occurs during approval.
     */
    @Override
    public void approveGymOwner(int ownerId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMOWNER_APPROVE)) {
            stmt.setInt(1, ownerId);
            stmt.executeUpdate();
        }
    }

    /**
     * Rejects a gym owner by updating their status to 'Rejected' in the database.
     *
     * @param ownerId The ID of the gym owner to be rejected.
     * @throws SQLException If a database error occurs during rejection.
     */
    @Override
    public void rejectGymOwner(int ownerId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMOWNER_REJECT)) {
            stmt.setInt(1, ownerId);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves the status of all gym owners.
     *
     * @return A map where the keys are {@link FlipFitGymOwner} objects and the values are the names of the gym owners.
     * @throws SQLException If a database error occurs during retrieval.
     */
    @Override
    public Map<FlipFitGymOwner, String> viewGymOwnerStatus() throws SQLException {
        Map<FlipFitGymOwner, String> ownerMap = new HashMap<>();
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMOWNER_LIST_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setId(rs.getInt("id"));
                owner.setStatus(rs.getString("status"));
                String ownerName = rs.getString("name");

                ownerMap.put(owner, ownerName);
            }
        }
        return ownerMap;
    }
}
