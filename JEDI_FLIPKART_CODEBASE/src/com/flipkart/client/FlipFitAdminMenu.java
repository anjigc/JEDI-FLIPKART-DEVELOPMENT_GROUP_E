package com.flipkart.client;

import java.util.Scanner;
import java.util.List;
import com.flipkart.business.FlipFitAdminInterface;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.exception.GymOwnerNotFoundException;
import com.flipkart.exception.GymStatusNotFoundException;

/**
 * This class represents the admin menu for FlipFit application, providing options
 * to approve, reject, and view the status of gyms and gym owners.
 * The user is able to select actions from a list of menu options.
 */
public class FlipFitAdminMenu {

    // Scanner instance to read user input
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the admin menu and allows the admin to choose different options
     * for managing gyms and gym owners. It processes user input and calls
     * respective service methods to perform actions.
     *
     * @param adminUser The FlipFitAdminUser object representing the logged-in admin user.
     * @param adminService The service interface that contains methods for gym and gym owner management.
     */
    public static void Menu(FlipFitUser adminUser, FlipFitAdminInterface adminService) {
        // Welcome message displaying the admin's name
        System.out.println("Welcome, Admin " + adminUser.getName() + "!");

        // List of available menu options for the admin
        List<String> menuOptions = List.of(
            "1. View Gym Owner List",
            "2. Approve Gym Owner",
            "3. Reject Gym Owner",
            "4. View Gym List",
            "5. Approve Gym",
            "6. Reject Gym",
            "7. Logout"
        );

        int choice; // Variable to store user choice

        do {
            // Displaying the menu options
            System.out.println("\n--- Admin Menu ---");
            menuOptions.forEach(System.out::println); // Displaying each option using forEach
            System.out.println("");

            // Prompting the user for a menu choice
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); // Reading user input
            scanner.nextLine(); // Clearing the newline character from input buffer
            System.out.println("");


            try {
                // Handling the user's choice
                switch (choice) {
                    case 1 -> adminService.viewGymOwnerStatus(); // View gym owner status
                    case 2 -> adminService.approveGymOwner(); // Approve gym owner action
                    case 3 -> adminService.rejectGymOwner(); // Reject gym owner action
                    case 4 -> adminService.viewGymStatus(); // View gym list/status
                    case 5 -> adminService.approveGym();  // Approve gym action
                    case 6 -> adminService.rejectGym();   // Reject gym action
                    case 7 -> System.out.println("Logging out..."); // Logging out action
                    default -> System.out.println("Invalid choice. Please try again."); // Invalid option message
                }
            } catch (GymNotFoundException | GymOwnerNotFoundException | GymStatusNotFoundException | DatabaseException e) {
                // Handling any exceptions that might occur during service calls
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 7); // Looping until the user chooses to logout
    }
}
