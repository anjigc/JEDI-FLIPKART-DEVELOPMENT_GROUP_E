package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;

import java.sql.SQLException;
import java.util.List;

public interface FlipFitGymOwnerDAOInterface {
    
    // Register a gym owner
    void registerGymOwner(FlipFitGymOwner gymOwner) throws SQLException;
    
    // Add a new gym
    void addGym(FlipFitGymCentre gymCentre) throws SQLException;
    
    // Remove an existing gym
    void removeGym(int gymId) throws SQLException;
    
    // View all gyms owned by a specific gym owner
    List<FlipFitGymCentre> viewGymList(int ownerId) throws SQLException;
    
    // Add slots to a gym
    void addGymSlot(FlipFitSlot slot) throws SQLException;
    
    // Remove a slot from a gym
    void removeGymSlot(int slotId) throws SQLException;
    
    // View all slots of a gym
    List<FlipFitSlot> viewGymSlots(int gymId) throws SQLException;
}