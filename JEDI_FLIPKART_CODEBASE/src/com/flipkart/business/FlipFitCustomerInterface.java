package com.flipkart.business;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.exception.*;

/**
 * Interface for FlipFit Customer Business Logic.
 * This interface defines various methods related to customer operations
 * such as registering customers, viewing gyms, selecting gyms, booking slots, and viewing bookings.
 */
public interface FlipFitCustomerInterface {

    /**
     * Registers a new customer.
     * @param id The unique identifier of the customer.
     * @param age The age of the customer.
     * @param address The address of the customer.
     * @throws InvalidCustomerDataException If the provided customer data is invalid.
     */
    public void registerCustomer(int id, int age, String address) throws InvalidCustomerDataException;

    /**
     * Displays the list of available gyms.
     * @throws NoGymsFoundException If no gyms are available.
     */
    public void viewGymList() throws NoGymsFoundException;

    /**
     * Allows the customer to select a gym.
     * @throws NoSlotsAvailableException If no slots are available at the selected gym.
     * @throws GymNotFoundException If the gym is not found.
     */
    public void selectGym() throws NoSlotsAvailableException, GymNotFoundException;

    /**
     * Displays the available slots for a selected gym.
     * @param slots A list of available slots.
     * @throws NoSlotsAvailableException If no slots are available.
     */
    public void viewAvailableSlots(List<FlipFitSlot> slots) throws NoSlotsAvailableException;

    /**
     * Allows the customer to book a gym slot.
     * @param customerId The ID of the customer booking the slot.
     * @throws BookingFailedException If the booking process fails.
     */
    public void bookGymSlot(int customerId) throws BookingFailedException;

    /**
     * Displays the bookings made by a customer.
     * @param customerId The ID of the customer.
     * @throws NoBookingsFoundException If no bookings are found for the customer.
     */
    public void viewMyBookings(int customerId) throws NoBookingsFoundException;
}
