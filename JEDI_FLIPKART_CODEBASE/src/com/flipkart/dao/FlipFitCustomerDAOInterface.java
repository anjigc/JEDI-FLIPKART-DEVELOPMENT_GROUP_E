package com.flipkart.dao;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitSlot;

import java.sql.SQLException;
import java.util.List;

public interface FlipFitCustomerDAOInterface {
    
    public void registerCustomer(FlipFitCustomer customer) throws SQLException;
    public List<FlipFitGymCentre> viewGymList() throws SQLException;
    public List<FlipFitSlot> viewAvailableSlots(int gymId) throws SQLException;
    public String bookGymSlot(int customerId, int slotId) throws SQLException;
    public List<FlipFitBooking> viewMyBookings(int customerId) throws SQLException;
}
