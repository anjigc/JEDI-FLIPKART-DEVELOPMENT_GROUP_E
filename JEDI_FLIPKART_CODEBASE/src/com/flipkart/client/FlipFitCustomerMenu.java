package com.flipkart.client;

import java.util.Scanner;
import java.util.List;
import com.flipkart.business.FlipFitCustomerInterface;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.*;

/**
 * This class represents the customer menu for the FlipFit application, providing options
 * for customers to view gyms, select gyms, book slots, view their bookings, and log out.
 * The user is able to choose actions from a list of menu options.
 */
public class FlipFitCustomerMenu {

    // Scanner instance to read user input
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the customer menu and allows the customer to choose different options
     * such as viewing gyms, selecting a gym to view available slots, booking a gym slot,
     * and viewing their bookings. It processes user input and calls corresponding service methods.
     *
     * @param customerUser The FlipFitUser object representing the logged-in customer.
     * @param customerService The service interface that contains methods for customer actions.
     */
    public static void Menu(FlipFitUser customerUser, FlipFitCustomerInterface customerService) {
        // Welcome message displaying the customer's name
        System.out.println("Welcome, Customer " + customerUser.getName() + "!");

        // List of available menu options for the customer
        List<String> menuOptions = List.of(
                "1. View Gyms",
                "2. Select Gym to view slots",
                "3. Book a Gym Slot",
                "4. View My Bookings",
                "5. Logout"
        );

        int choice; // Variable to store user's choice

        do {
            // Displaying the menu options
            System.out.println("\n--- Customer Menu ---");
            menuOptions.forEach(System.out::println); // Displaying each option using forEach

            // Prompting the user for a menu choice
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); // Reading user input
            scanner.nextLine(); // Clearing the newline character from input buffer

            try {
                // Handling the user's choice
                switch (choice) {
                    case 1 -> customerService.viewGymList(); // Viewing the list of gyms
                    case 2 -> customerService.selectGym(); // Selecting a gym to view available slots
                    case 3 -> customerService.bookGymSlot(customerUser.getId()); // Booking a gym slot
                    case 4 -> customerService.viewMyBookings(customerUser.getId()); // Viewing the customer's bookings
                    case 5 -> System.out.println("Logging out..."); // Logging out action
                    default -> System.out.println("Invalid choice. Please try again."); // Invalid option message
                }
            } catch (NoGymsFoundException | NoSlotsAvailableException | GymNotFoundException
                     | BookingFailedException | NoBookingsFoundException e) {
                // Handling specific exceptions related to gyms, slots, and bookings
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                // Handling any unexpected exceptions
                System.out.println("Unexpected error: " + e.getMessage());
            }
        } while (choice != 5); // Looping until the user chooses to logout
    }
}
