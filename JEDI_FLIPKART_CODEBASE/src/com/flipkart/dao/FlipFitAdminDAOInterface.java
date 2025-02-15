package com.flipkart.dao;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;

import java.sql.SQLException;
import java.util.List;

public interface FlipFitAdminDAOInterface {

    public void registerAdmin(FlipFitAdmin admin) throws SQLException;
    public void approveGym(int gymId) throws SQLException;
    public void rejectGym(int gymId) throws SQLException;
    public List<FlipFitGymCentre> viewGymStatus() throws SQLException;
    public void approveGymOwner(int ownerId) throws SQLException;
    public void rejectGymOwner(int ownerId) throws SQLException;
    public List<FlipFitGymOwner> viewGymOwnerStatus() throws SQLException;
}