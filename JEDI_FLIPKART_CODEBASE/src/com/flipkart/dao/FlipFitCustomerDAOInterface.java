package com.flipkart.dao;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitSlot;

import java.sql.SQLException;
import java.util.List;

public interface FlipFitCustomerDAOInterface {
    
    void registerCustomer(FlipFitCustomer customer) throws SQLException;
    
    List<FlipFitGymCentre> viewGymList() throws SQLException;
    
    List<FlipFitSlot> viewAvailableSlots(int gymId) throws SQLException;
    
    String bookGymSlot(int customerId, int slotId) throws SQLException;
    
    List<FlipFitBooking> viewMyBookings(int customerId) throws SQLException;
}
