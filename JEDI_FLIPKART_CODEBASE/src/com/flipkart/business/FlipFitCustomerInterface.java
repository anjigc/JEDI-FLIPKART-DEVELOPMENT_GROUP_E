package com.flipkart.business;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.exception.*;

public interface FlipFitCustomerInterface {
    public void registerCustomer(int id, int age, String address) throws InvalidCustomerDataException;
    public void viewGymList() throws NoGymsFoundException;
    public void selectGym() throws NoSlotsAvailableException, GymNotFoundException;
    public void viewAvailableSlots(List<FlipFitSlot> slots) throws NoSlotsAvailableException;
    public void bookGymSlot(int customerId) throws BookingFailedException;
    public void viewMyBookings(int customerId) throws NoBookingsFoundException;
}
