package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.dao.FlipFitCustomerDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FlipFitCustomerService implements FlipFitCustomerInterface {
    private FlipFitCustomerDAO customerDAO;
    private Scanner scanner = new Scanner(System.in);

    public FlipFitCustomerService() {
        this.customerDAO = new FlipFitCustomerDAO();
    }

    public void registerCustomer(int id, int age, String address) {
        FlipFitCustomer customer = new FlipFitCustomer();
        customer.setId(id);
        customer.setAge(age);
        customer.setAddress(address);
        
        try {
            customerDAO.registerCustomer(customer);
            System.out.println("User registered as FlipFit Customer successfully!");
        } catch (SQLException e) {
            System.out.println("Error registering customer: " + e.getMessage());
        }
    }

    public void viewGymList() {
        try {
            List<FlipFitGymCentre> gymCentres = customerDAO.viewGymList();
            System.out.println("\nAvailable Gyms:");
            System.out.println("-------------------------------------");
            System.out.println("| Gym ID | Gym Name  | Location    |");
            System.out.println("-------------------------------------");
            for (FlipFitGymCentre gym : gymCentres) {
                System.out.printf("|   %d   | %s   | %s    |%n", gym.getGymId(), gym.getGymName(), gym.getGymAddress());
            }
            System.out.println("-------------------------------------\n");
        } catch (SQLException e) {
            System.out.println("Error fetching gym list: " + e.getMessage());
        }
    }

    public void selectGym() {
        System.out.print("\nEnter Gym ID: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        
        try {
            List<FlipFitSlot> slots = customerDAO.viewAvailableSlots(gymId);
            if (slots.isEmpty()) {
                System.out.println("No available slots for this gym.");
                return;
            }
            viewAvailableSlots(slots);
        } catch (SQLException e) {
            System.out.println("Error fetching gym slots: " + e.getMessage());
        }
    }

    public void viewAvailableSlots(List<FlipFitSlot> slots) {
        System.out.println("\nAvailable Gym Slots:");
        System.out.println("-------------------------------------");
        System.out.println("| Slot ID | Start Time  | End Time  | Available Seats |");
        System.out.println("-------------------------------------");
        for (FlipFitSlot slot : slots) {
            System.out.printf("|   %d     | %s  | %s  | %d               |%n", slot.getSlotId(), slot.getStartTime(), slot.getEndTime(), slot.getAvailableSeats());
        }
        System.out.println("-------------------------------------\n");
    }

    public void bookGymSlot(int customerId) {
        System.out.print("Enter Slot ID to book: ");
        int slotId = scanner.nextInt();
        scanner.nextLine();
        
        try {
            int transactionId = customerDAO.bookGymSlot(customerId, slotId);
            System.out.println("Slot " + slotId + " booked successfully! Transaction ID: " + transactionId + "\n");
        } catch (SQLException e) {
            System.out.println("Error booking slot: " + e.getMessage());
        }
    }

    public void viewMyBookings(int customerId) {
        try {
            List<FlipFitBooking> bookings = customerDAO.viewMyBookings(customerId);
            System.out.println("\nYour Bookings:");
            System.out.println("-------------------------------------");
            System.out.println("| Slot ID | Booking Date | Confirmed |");
            System.out.println("-------------------------------------");
            for (FlipFitBooking booking : bookings) {
                System.out.printf("|   %d     | %s  | %s  |%n", booking.getSlotId(), booking.getBookingDate(), booking.isConfirmed());
            }
            System.out.println("-------------------------------------\n");
        } catch (SQLException e) {
            System.out.println("Error fetching bookings: " + e.getMessage());
        }
    }
}
