package com.flipkart.dao;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;

import java.sql.SQLException;
import java.util.List;

public interface FlipFitAdminDAOInterface {

    void registerAdmin(FlipFitAdmin admin) throws SQLException;
    void approveGym(int gymId) throws SQLException;
    void rejectGym(int gymId) throws SQLException;
    List<FlipFitGymCentre> viewGymStatus() throws SQLException;
    void approveGymOwner(int ownerId) throws SQLException;
    void rejectGymOwner(int ownerId) throws SQLException;
    List<FlipFitGymOwner> viewGymOwnerStatus() throws SQLException;
}