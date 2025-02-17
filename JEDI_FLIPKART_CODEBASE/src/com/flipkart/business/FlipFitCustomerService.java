package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.dao.FlipFitCustomerDAO;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FlipFitCustomerService implements FlipFitCustomerInterface {
    private FlipFitCustomerDAO customerDAO;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor: Initializes the customerDAO for database operations.
     */
    public FlipFitCustomerService() {
        this.customerDAO = new FlipFitCustomerDAO();
    }

    /**
     * Registers a new customer with provided details.
     * 
     * @param id The unique ID of the customer
     * @param age The age of the customer
     * @param address The address of the customer
     * @throws InvalidCustomerDataException If invalid customer data is provided (age <= 0 or empty address)
     */
    public void registerCustomer(int id, int age, String address) throws InvalidCustomerDataException {
        FlipFitCustomer customer = new FlipFitCustomer();
        customer.setId(id);
        customer.setAge(age);
        customer.setAddress(address);
        
        try {
            // Validation checks for customer data
            if (age <= 0) {
                throw new InvalidCustomerDataException("Age must be greater than zero.");
            }

            if (address == null || address.trim().isEmpty()) {
                throw new InvalidCustomerDataException("Address cannot be empty.");
            }

            // Attempt to register customer in the database
            customerDAO.registerCustomer(customer);
            System.out.println("User registered as FlipFit Customer successfully!");
        } catch (SQLException e) {
            System.out.println("Error registering customer: " + e.getMessage());
        }
    }

    /**
     * Displays the list of available gyms for the customer to choose from.
     * 
     * @throws NoGymsFoundException If no gyms are found in the database
     */
    public void viewGymList() throws NoGymsFoundException {
        try {
            // Fetch the list of gym centres from the database
            List<FlipFitGymCentre> gymCentres = customerDAO.viewGymList();
            if (gymCentres.isEmpty()) {
                throw new NoGymsFoundException("No gyms are currently available.");
            }
            
            // Display the gym list to the user
            System.out.println("\nAvailable Gyms:");
            System.out.println("-------------------------------------");
            System.out.println("| Gym ID | Gym Name  | Location    |");
            System.out.println("-------------------------------------");
            gymCentres.forEach(gym -> System.out.printf("|   %d   | %s   | %s    |%n", gym.getGymId(), gym.getGymName(), gym.getGymAddress()));
            System.out.println("-------------------------------------\n");
        } catch (NoGymsFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error while fetching gym list: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Allows customer to select a gym for booking, handles cases for available slots and invalid gym IDs.
     * 
     * @throws NoSlotsAvailableException If there are no available slots for the selected gym
     * @throws GymNotFoundException If the selected gym ID does not exist
     */
    public void selectGym() throws NoSlotsAvailableException, GymNotFoundException {
        System.out.print("\nEnter Gym ID: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        
        try {
            // Fetch the available slots for the selected gym
            List<FlipFitSlot> slots = customerDAO.viewAvailableSlots(gymId);
            if (slots.isEmpty()) {
                throw new NoSlotsAvailableException("No available slots for this gym.");
            }
            if (slots == null) { // Check if slots is null, assuming DAO returns null for non-existing gym
                throw new GymNotFoundException("Gym ID " + gymId + " does not exist.");
            }
            // Display available slots for the user
            viewAvailableSlots(slots);
        } catch (NoSlotsAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (GymNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }  catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Displays the available slots for a selected gym.
     * 
     * @param slots The list of available gym slots
     * @throws NoSlotsAvailableException If no slots are available for the selected gym
     */
    public void viewAvailableSlots(List<FlipFitSlot> slots) throws NoSlotsAvailableException {
        try {
            // If no slots are available or slots list is empty
            if (slots == null || slots.isEmpty()) {
                throw new NoSlotsAvailableException("No slots available for the selected gym.");
            }

            // Display the list of available gym slots
            System.out.println("\nAvailable Gym Slots:");
            System.out.println("-------------------------------------");
            System.out.println("| Slot ID | Start Time  | End Time  | Available Seats |");
            System.out.println("-------------------------------------");
            slots.forEach(slot ->
                    System.out.printf("|   %d     | %s  | %s  | %d               |%n", slot.getSlotId(), slot.getStartTime(), slot.getEndTime(), slot.getAvailableSeats())
            );
            System.out.println("-------------------------------------\n");
        } catch (NoSlotsAvailableException e) {
            System.out.println("Error: " + e.getMessage());  // In case no slots are available
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());  // Catch any unforeseen errors
        }
    }

    /**
     * Books a gym slot for the customer, handles booking failure.
     * 
     * @param customerId The ID of the customer making the booking
     * @throws BookingFailedException If the booking fails
     */
    public void bookGymSlot(int customerId) throws BookingFailedException {
        System.out.print("Enter Slot ID to book: ");
        int slotId = scanner.nextInt();
        scanner.nextLine();
        
        try {
            // Attempt to book the slot and check if booking fails
            int transactionId = customerDAO.bookGymSlot(customerId, slotId);
            if (transactionId == -1) {
                throw new BookingFailedException("Failed to book slot due to a system error.");
            }
            System.out.println("Slot " + slotId + " booked successfully! Transaction ID: " + transactionId + "\n");
        } catch (BookingFailedException e) {
            System.out.println("Booking error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * View all the bookings made by the customer.
     * 
     * @param customerId The ID of the customer whose bookings need to be fetched
     * @throws NoBookingsFoundException If no bookings are found for the customer
     */
    public void viewMyBookings(int customerId) throws NoBookingsFoundException {
        try {
            // Fetch the list of bookings for the customer from the database
            List<FlipFitBooking> bookings = customerDAO.viewMyBookings(customerId);
            if (bookings == null || bookings.isEmpty()) {
                throw new NoBookingsFoundException("No bookings found for the customer.");
            }
            
            // Display the booking details to the customer
            System.out.println("\nYour Bookings:");
            System.out.println("-------------------------------------");
            System.out.println("| Slot ID | Booking Date | Confirmed |");
            System.out.println("-------------------------------------");
            bookings.forEach(booking ->
                    System.out.printf("|   %d     | %s  | %s  |%n", booking.getSlotId(), booking.getBookingDate(), booking.isConfirmed())
            );
            System.out.println("-------------------------------------\n");
        } catch (NoBookingsFoundException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            // Handle unexpected errors
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
