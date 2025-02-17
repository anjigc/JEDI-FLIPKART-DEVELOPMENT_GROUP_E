package com.flipkart.dao;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitSlot;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for FlipFit Customer DAO (Data Access Object).
 * This interface defines methods for customer-related operations 
 * such as registering customers, viewing available gyms and slots, 
 * booking gym slots, and checking bookings.
 */
public interface FlipFitCustomerDAOInterface {

    /**
     * Registers a new customer in the system.
     * @param customer The FlipFitCustomer object containing customer details.
     * @throws SQLException If a database access error occurs.
     */
    public void registerCustomer(FlipFitCustomer customer) throws SQLException;

    /**
     * Retrieves the list of all gym centers available for customers.
     * @return A list of FlipFitGymCentre objects representing the gym centers.
     * @throws SQLException If a database access error occurs.
     */
    public List<FlipFitGymCentre> viewGymList() throws SQLException;

    /**
     * Retrieves the list of available slots for a given gym.
     * @param gymId The unique identifier of the gym.
     * @return A list of FlipFitSlot objects representing available slots.
     * @throws SQLException If a database access error occurs.
     */
    public List<FlipFitSlot> viewAvailableSlots(int gymId) throws SQLException;

    /**
     * Books a gym slot for a customer.
     * @param customerId The unique identifier of the customer.
     * @param slotId The unique identifier of the slot to be booked.
     * @return A confirmation message indicating success or failure.
     * @throws SQLException If a database access error occurs.
     */
    public String bookGymSlot(int customerId, int slotId) throws SQLException;

    /**
     * Retrieves the list of bookings made by a specific customer.
     * @param customerId The unique identifier of the customer.
     * @return A list of FlipFitBooking objects representing the customer's bookings.
     * @throws SQLException If a database access error occurs.
     */
    public List<FlipFitBooking> viewMyBookings(int customerId) throws SQLException;
}
