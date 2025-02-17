package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.constant.FlipFitConstants;
import com.flipkart.utils.FlipFitDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code FlipFitGymOwnerDAO} class provides the implementation of the data access layer responsible for performing
 * database operations related to gym owners. It includes functionality for registering gym owners, adding/removing gym centers,
 * managing gym slots, and viewing gym details.
 * <p>
 * This class interacts with the database using SQL queries defined in the {@link FlipFitConstants} class.
 * </p>
 */
public class FlipFitGymOwnerDAO implements FlipFitGymOwnerDAOInterface {
    private Connection connection;

    /**
     * Constructor that initializes the database connection.
     * Uses {@link FlipFitDBConnection#getConnection()} to obtain a connection to the database.
     */
    public FlipFitGymOwnerDAO() {
        this.connection = FlipFitDBConnection.getConnection();
    }

    /**
     * Registers a new gym owner in the system.
     *
     * @param gymOwner The {@link FlipFitGymOwner} object containing the gym owner's details to be registered.
     * @throws SQLException If a database error occurs during registration.
     */
    @Override
    public void registerGymOwner(FlipFitGymOwner gymOwner) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMOWNER_REGISTER)) {
            stmt.setInt(1, gymOwner.getId());
            stmt.setString(2, gymOwner.getPanNo());
            stmt.setString(3, gymOwner.getAddress());
            stmt.setString(4, gymOwner.getAadhaar());
            stmt.setString(5, "Pending");
            stmt.executeUpdate();
        }
    }

    /**
     * Adds a new gym center to the system.
     *
     * @param gymCentre The {@link FlipFitGymCentre} object containing the gym center's details to be added.
     * @throws SQLException If a database error occurs during gym center addition.
     */
    @Override
    public void addGym(FlipFitGymCentre gymCentre) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_ADD)) {
            stmt.setInt(1, gymCentre.getGymId());
            stmt.setString(2, gymCentre.getGymName());
            stmt.setString(3, gymCentre.getGymAddress());
            stmt.setInt(4, gymCentre.getOwnerId());
            stmt.setInt(5, gymCentre.getCapacity());
            stmt.setString(6, "Pending");
            stmt.setInt(7, gymCentre.getSlotPrice());
            stmt.executeUpdate();
        }
    }

    /**
     * Removes a gym center from the system based on its gym ID.
     *
     * @param gymId The ID of the gym to be removed.
     * @throws SQLException If a database error occurs during gym center removal.
     */
    @Override
    public void removeGym(int gymId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_REMOVE)) {
            stmt.setInt(1, gymId);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a list of gym centers owned by a specific gym owner.
     *
     * @param ownerId The ID of the gym owner whose gyms are to be retrieved.
     * @return A list of {@link FlipFitGymCentre} objects representing the gym centers owned by the specified owner.
     * @throws SQLException If a database error occurs during the retrieval.
     */
    @Override
    public List<FlipFitGymCentre> viewGymList(int ownerId) throws SQLException {
        List<FlipFitGymCentre> gymCentres = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_LIST_BY_OWNER)) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FlipFitGymCentre gym = new FlipFitGymCentre();
                    gym.setGymId(rs.getInt("gymId"));
                    gym.setGymName(rs.getString("gymName"));
                    gym.setGymAddress(rs.getString("gymAddress"));
                    gym.setOwnerId(rs.getInt("ownerId"));
                    gym.setCapacity(rs.getInt("capacity"));
                    gym.setStatus(rs.getString("status"));
                    gymCentres.add(gym);
                }
            }
        }
        return gymCentres;
    }

    /**
     * Adds a new gym slot for a specific gym.
     *
     * @param slot The {@link FlipFitSlot} object containing the slot's details to be added.
     * @throws SQLException If a database error occurs during slot addition.
     */
    @Override
    public void addGymSlot(FlipFitSlot slot) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_SLOT_ADD)) {
            stmt.setInt(1, slot.getGymId());
            stmt.setString(2, slot.getStartTime());
            stmt.setString(3, slot.getEndTime());
            stmt.setInt(4, slot.getAvailableSeats());
            stmt.executeUpdate();
        }
    }

    /**
     * Removes a gym slot from the system based on its slot ID.
     *
     * @param slotId The ID of the slot to be removed.
     * @throws SQLException If a database error occurs during slot removal.
     */
    @Override
    public void removeGymSlot(int slotId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_SLOT_DELETE)) {
            stmt.setInt(1, slotId);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a list of available gym slots for a specific gym.
     *
     * @param gymId The ID of the gym whose available slots are to be retrieved.
     * @return A list of {@link FlipFitSlot} objects representing the available gym slots.
     * @throws SQLException If a database error occurs during retrieval.
     */
    @Override
    public List<FlipFitSlot> viewGymSlots(int gymId) throws SQLException {
        List<FlipFitSlot> slots = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_SLOT_LIST_ALL_AVAILABLE)) {
            stmt.setInt(1, gymId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FlipFitSlot slot = new FlipFitSlot();
                    slot.setSlotId(rs.getInt("slotId"));
                    slot.setGymId(rs.getInt("gymId"));
                    slot.setStartTime(rs.getString("startTime"));
                    slot.setEndTime(rs.getString("endTime"));
                    slot.setAvailableSeats(rs.getInt("availableSeats"));
                    slots.add(slot);
                }
            }
        }
        return slots;
    }


    /**
     * Retrieves the status of a gym owner based on the owner ID.
     *
     * @param ownerId The ID of the gym owner whose status is to be retrieved.
     * @return The status of the gym owner.
     * @throws SQLException If a database error occurs during status retrieval.
     */
    @Override
    public String getGymOwnerStatus(int ownerId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMOWNER_STATUS)) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getString("status") : null;
            }
        }
    }
}
