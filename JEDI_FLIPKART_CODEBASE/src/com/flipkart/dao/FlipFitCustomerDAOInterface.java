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
     *
     * This method allows a customer to book a specific gym slot identified by
     * the `slotId`. It marks the slot as booked, updates the availability of
     * seats for the selected slot, and creates a payment record for the transaction.
     *
     * The method performs the following actions:
     * 1. It creates a new booking entry for the customer in the database.
     * 2. It decreases the available seats for the selected gym slot.
     * 3. It creates a payment record for the transaction, marking the payment as completed.
     *
     * @param customerId The unique identifier of the customer who wants to book the slot.
     *                   This ID is used to associate the booking with the customer.
     * @param slotId The unique identifier of the slot to be booked. This ID is used to identify the slot.
     * @param slotPrice The price of the slot being booked. This is used to create a payment record for the transaction.
     * @return The transaction ID for the payment made, which serves as confirmation of the successful booking.
     * @throws SQLException If a database access error occurs, such as issues with executing the SQL queries.
     */
    public int bookGymSlot(int customerId, int slotId, double slotPrice) throws SQLException;

    /**
     * Retrieves the list of bookings made by a specific customer.
     *
     * This method fetches all the bookings made by the customer identified by the
     * provided `customerId`. It returns a list of `FlipFitBooking` objects, each
     * representing a specific booking made by the customer. The booking details include
     * the slot ID, gym ID, and the confirmation status of the booking.
     *
     * @param customerId The unique identifier of the customer whose bookings are to be fetched.
     *                   This ID is used to filter and retrieve the customer's bookings.
     * @return A list of `FlipFitBooking` objects representing the bookings made by the customer.
     *         Each booking includes details like the slot ID, gym ID, and booking status.
     * @throws SQLException If a database access error occurs or the SQL query fails.
     */
    public List<FlipFitBooking> viewMyBookings(int customerId) throws SQLException;

    /**
     * Retrieves the gym ID and slot price information for a given slot ID.
     *
     * This method fetches the associated gym ID and the price of the slot
     * for the specified slot ID from the database. It executes a SQL query to
     * retrieve these details from the `FlipFitSlot` and `FlipFitGymCentre` tables
     * by joining them on the `gymId` field.
     *
     * @param slotId The unique identifier of the slot for which gym and price information is requested.
     *               This is used to identify the specific slot in the database.
     * @return An Object array where the first element is the gym ID (Integer) and the second element is
     *         the slot price (Double). If no record is found for the given slot ID, it returns null.
     * @throws SQLException If a database access error occurs or the SQL query fails.
     */
    public Object[] getGymSlotInfo(int slotId) throws SQLException;
}
