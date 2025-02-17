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

public class FlipFitAdminDAO implements FlipFitAdminDAOInterface {
    private Connection connection;

    public FlipFitAdminDAO() {
        this.connection = FlipFitDBConnection.getConnection();
    }

    @Override
    public void registerAdmin(FlipFitAdmin admin) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_ADMIN_REGISTER)) {
            stmt.setInt(1, admin.getId());
            stmt.executeUpdate();
        }
    }

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

    @Override
    public void rejectGym(int gymId) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMCENTER_REJECT)) {
            stmt.setInt(1, gymId);
            stmt.executeUpdate();
        }
    }

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

    @Override
    public void approveGymOwner(int ownerId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMOWNER_APPROVE)) {
            stmt.setInt(1, ownerId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void rejectGymOwner(int ownerId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMOWNER_REJECT)) {
            stmt.setInt(1, ownerId);
            stmt.executeUpdate();
        }
    }

    @Override
    public Map<FlipFitGymOwner, String> viewGymOwnerStatus() throws SQLException {
    Map<FlipFitGymOwner, String> ownerMap = new HashMap<>();
    try (PreparedStatement stmt = connection.prepareStatement(FlipFitConstants.FLIPFIT_SQL_GYMOWNER_LIST_ALL);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            FlipFitGymOwner owner = new FlipFitGymOwner();
            owner.setId(rs.getInt("id"));                  // From FlipFitGymOwner
            owner.setStatus(rs.getString("status"));       // From FlipFitGymOwner
            String ownerName = rs.getString("name");       // From FlipFitUser
            
            ownerMap.put(owner, ownerName);
        }
    }
    return ownerMap;
}

}
