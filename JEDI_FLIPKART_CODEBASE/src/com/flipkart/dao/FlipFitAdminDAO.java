package com.flipkart.dao;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminDAO implements FlipFitAdminDAOInterface {
    private Connection connection;

    public FlipFitAdminDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerAdmin(FlipFitAdmin admin) throws SQLException {
        String sql = "INSERT INTO FlipFitAdmin (id) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, admin.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void approveGym(int gymId) throws SQLException {
        String sql = "UPDATE FlipFitGymCentre SET status = 'Approved' WHERE gymId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, gymId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void rejectGym(int gymId) throws SQLException {
        String sql = "UPDATE FlipFitGymCentre SET status = 'Rejected' WHERE gymId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, gymId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<FlipFitGymCentre> viewGymStatus() throws SQLException {
        List<FlipFitGymCentre> gymList = new ArrayList<>();
        String sql = "SELECT * FROM FlipFitGymCentre";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
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

    @Override
    public void approveGymOwner(int ownerId) throws SQLException {
        String sql = "UPDATE FlipFitGymOwner SET status = 'Approved' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ownerId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void rejectGymOwner(int ownerId) throws SQLException {
        String sql = "UPDATE FlipFitGymOwner SET status = 'Rejected' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ownerId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<FlipFitGymOwner> viewGymOwnerStatus() throws SQLException {
        List<FlipFitGymOwner> ownerList = new ArrayList<>();
        String sql = "SELECT * FROM FlipFitGymOwner";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setId(rs.getInt("id"));
                owner.setStatus(rs.getString("status"));
                ownerList.add(owner);
            }
        }
        return ownerList;
    }
}
