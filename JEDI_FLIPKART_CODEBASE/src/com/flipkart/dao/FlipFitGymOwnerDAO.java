package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.constant.FlipFitConstants;
import com.flipkart.utils.FlipFitDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerDAO implements FlipFitGymOwnerDAOInterface {
    private Connection connection;

    public FlipFitGymOwnerDAO() {
        this.connection = FlipFitDBConnection.getConnection();
    }

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

    @Override
    public void addGym(FlipFitGymCentre gymCentre) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_ADD)) {
            stmt.setInt(1, gymCentre.getGymId());
            stmt.setString(2, gymCentre.getGymName());
            stmt.setString(3, gymCentre.getGymAddress());
            stmt.setInt(4, gymCentre.getOwnerId());
            stmt.setInt(5, gymCentre.getCapacity());
            stmt.setString(6, "Pending");
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeGym(int gymId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_REMOVE)) {
            stmt.setInt(1, gymId);
            stmt.executeUpdate();
        }
    }

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

    @Override
    public void removeGymSlot(int slotId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_SLOT_DELETE)) {
            stmt.setInt(1, slotId);
            stmt.executeUpdate();
        }
    }

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
}