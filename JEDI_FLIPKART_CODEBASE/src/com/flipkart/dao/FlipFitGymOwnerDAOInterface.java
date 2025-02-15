package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;

import java.sql.SQLException;
import java.util.List;

public interface FlipFitGymOwnerDAOInterface {
    public void registerGymOwner(FlipFitGymOwner gymOwner) throws SQLException;
    public void addGym(FlipFitGymCentre gymCentre) throws SQLException;
    public void removeGym(int gymId) throws SQLException;
    public List<FlipFitGymCentre> viewGymList(int ownerId) throws SQLException;
    public void addGymSlot(FlipFitSlot slot) throws SQLException;
    public void removeGymSlot(int slotId) throws SQLException;
    public List<FlipFitSlot> viewGymSlots(int gymId) throws SQLException;
}