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

    public FlipFitCustomerService() {
        this.customerDAO = new FlipFitCustomerDAO();
    }

    public void registerCustomer(int id, int age, String address) throws InvalidCustomerDataException {
        FlipFitCustomer customer = new FlipFitCustomer();
        customer.setId(id);
        customer.setAge(age);
        customer.setAddress(address);
        
        try {

            if (age <= 0) {
                throw new InvalidCustomerDataException("Age must be greater than zero.");
            }

            if (address == null || address.trim().isEmpty()) {
                throw new InvalidCustomerDataException("Address cannot be empty.");
            }

            customerDAO.registerCustomer(customer);
            System.out.println("User registered as FlipFit Customer successfully!");
        } catch (SQLException e) {
            System.out.println("Error registering customer: " + e.getMessage());
        }
    }

    public void viewGymList() throws NoGymsFoundException {
        try {
            List<FlipFitGymCentre> gymCentres = customerDAO.viewGymList();
            if (gymCentres.isEmpty()) {
                throw new NoGymsFoundException("No gyms are currently available.");
            }
                System.out.println("\nAvailable Gyms:");
            System.out.println("-------------------------------------");
            System.out.println("| Gym ID | Gym Name  | Location    |");
            System.out.println("-------------------------------------");
            for (FlipFitGymCentre gym : gymCentres) {
                System.out.printf("|   %d   | %s   | %s    |%n", gym.getGymId(), gym.getGymName(), gym.getGymAddress());
            }
            System.out.println("-------------------------------------\n");
        } catch (NoGymsFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error while fetching gym list: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void selectGym() throws NoSlotsAvailableException, GymNotFoundException {
        System.out.print("\nEnter Gym ID: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        
        try {
            List<FlipFitSlot> slots = customerDAO.viewAvailableSlots(gymId);
            if (slots.isEmpty()) {
                throw new NoSlotsAvailableException("No available slots for this gym.");
            }
            if (slots == null) { // Check if slots is null, assuming DAO returns null for non-existing gym
                throw new GymNotFoundException("Gym ID " + gymId + " does not exist.");
            }
            viewAvailableSlots(slots);
        } catch (NoSlotsAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (GymNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }  catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void viewAvailableSlots(List<FlipFitSlot> slots) throws NoSlotsAvailableException {
        try {
        if (slots == null || slots.isEmpty()) {
            throw new NoSlotsAvailableException("No slots available for the selected gym.");
        }

        System.out.println("\nAvailable Gym Slots:");
        System.out.println("-------------------------------------");
        System.out.println("| Slot ID | Start Time  | End Time  | Available Seats |");
        System.out.println("-------------------------------------");


        for (FlipFitSlot slot : slots) {
            System.out.printf("|   %d     | %s  | %s  | %d               |%n", slot.getSlotId(), slot.getStartTime(), slot.getEndTime(), slot.getAvailableSeats());
        }
        System.out.println("-------------------------------------\n");
    } catch (NoSlotsAvailableException e) {
            System.out.println("Error: " + e.getMessage());  // In case no slots are available
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());  // Catch any unforeseen errors
        }
    }

    public void bookGymSlot(int customerId) throws BookingFailedException {
        System.out.print("Enter Slot ID to book: ");
        int slotId = scanner.nextInt();
        scanner.nextLine();
        
        try {
            int transactionId = customerDAO.bookGymSlot(customerId, slotId);
            // If transaction fails
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

    public void viewMyBookings(int customerId) throws NoBookingsFoundException {
        try {
            List<FlipFitBooking> bookings = customerDAO.viewMyBookings(customerId);
            if (bookings == null || bookings.isEmpty()) {
                throw new NoBookingsFoundException("No bookings found for the customer.");
            }
            System.out.println("\nYour Bookings:");
            System.out.println("-------------------------------------");
            System.out.println("| Slot ID | Booking Date | Confirmed |");
            System.out.println("-------------------------------------");
            for (FlipFitBooking booking : bookings) {
                System.out.printf("|   %d     | %s  | %s  |%n", booking.getSlotId(), booking.getBookingDate(), booking.isConfirmed());
            }
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
