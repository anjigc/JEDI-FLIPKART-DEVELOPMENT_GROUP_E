package com.flipkart.client;

import java.sql.SQLException;
import java.util.Scanner;
import com.flipkart.business.FlipFitCustomerInterface;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.*;

public class FlipFitCustomerMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void Menu(FlipFitUser customerUser, FlipFitCustomerInterface customerService) {
        int choice;

        System.out.println("Welcome, Customer " + customerUser.getName() + "!");
        do {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Gyms");
            System.out.println("2. Select Gym to view slots");
            System.out.println("3. Book a Gym Slot");
            System.out.println("4. View My Bookings");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        customerService.viewGymList();
                    } catch (NoGymsFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Unexpected error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        customerService.selectGym();
                    } catch (NoSlotsAvailableException e) {
                        System.out.println("Error: " + e.getMessage());  // No available slots for the selected gym
                    } catch (GymNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());  // Gym with the provided ID does not exist
                    } catch (Exception e) {
                        System.out.println("Unexpected error: " + e.getMessage());  // Catch any other unforeseen errors
                    }
                    break;
                case 3:
                    try {
                        customerService.bookGymSlot(customerUser.getId());
                    } catch (BookingFailedException e) {
                        System.out.println("Booking error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Unexpected error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        customerService.viewMyBookings(customerUser.getId());
                    } catch (NoBookingsFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Unexpected error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }
}
