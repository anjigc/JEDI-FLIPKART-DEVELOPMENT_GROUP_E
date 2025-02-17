package com.flipkart.client;

import java.util.Scanner;
import java.util.List;
import com.flipkart.business.FlipFitGymOwnerInterface;
import com.flipkart.bean.FlipFitUser;

/**
 * This class represents the Gym Owner menu for the FlipFit application. It allows gym owners
 * to manage their gyms by adding or removing gyms, viewing their gym list, and logging out.
 * The user can choose actions from a list of menu options.
 */
public class FlipFitGymOwnerMenu {

    // Scanner instance for reading user input
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the Gym Owner menu and allows the gym owner to choose different options
     * such as adding a gym, removing a gym, viewing gym list, and logging out. It processes
     * user input and calls corresponding service methods.
     *
     * @param gymOwnerUser The FlipFitUser object representing the logged-in gym owner.
     * @param gymOwnerService The service interface that contains methods for gym owner actions.
     */
    public static void Menu(FlipFitUser gymOwnerUser, FlipFitGymOwnerInterface gymOwnerService) {
        // Welcome message displaying the gym owner's name
        System.out.println("Welcome, Gym Owner " + gymOwnerUser.getName() + "!");

        // List of available menu options for the gym owner
        List<String> menuOptions = List.of(
            "1. View Gym List",
            "2. Add Gym",
            "3. Remove Gym",
            "4. Logout"
        );

        int choice; // Variable to store user's choice

        do {
            // Displaying the menu options
            System.out.println("\n--- Gym Owner Menu ---");
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
                    case 1 -> gymOwnerService.viewGymList(gymOwnerUser.getId()); // Viewing the gym list
                    case 2 -> gymOwnerService.addGym(gymOwnerUser.getId()); // Adding a gym
                    case 3 -> gymOwnerService.removeGym(gymOwnerUser.getId()); // Removing a gym
                    case 4 -> System.out.println("Logging out..."); // Logging out action
                    default -> System.out.println("Invalid choice. Please try again."); // Invalid option message
                }
            } catch (Exception e) {
                // Handling any exceptions that occur
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 4); // Looping until the user chooses to logout
    }
}
