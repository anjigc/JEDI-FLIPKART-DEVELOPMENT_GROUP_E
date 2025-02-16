package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.FlipFitAdminInterface;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.exception.GymOwnerNotFoundException;
import com.flipkart.exception.GymStatusNotFoundException;

public class FlipFitAdminMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void Menu(FlipFitUser adminUser, FlipFitAdminInterface adminService) {
        int choice;

        System.out.println("Welcome, Admin " + adminUser.getName() + "!");
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Approve Gym");
            System.out.println("2. Reject Gym");
            System.out.println("3. View Gym List");
            System.out.println("4. Approve Gym Owner");
            System.out.println("5. Reject Gym Owner");
            System.out.println("6. View Gym Owner Status");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    try {
                        adminService.approveGym();
                    } catch (GymNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (DatabaseException e) {
                        System.out.println("Database error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        adminService.rejectGym();
                    } catch (GymNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (DatabaseException e) {
                        System.out.println("Database error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        adminService.viewGymStatus();
                    } catch (GymStatusNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (DatabaseException e) {
                        System.out.println("Database error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        adminService.approveGymOwner();
                    } catch (GymOwnerNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());  // Custom message if gym owner not found
                    } catch (DatabaseException e) {
                        System.out.println("Database error: " + e.getMessage());  // Custom message for DB error
                    }
                    break;
                case 5:
                    try {
                        adminService.rejectGymOwner();
                    } catch (GymOwnerNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());  // Custom message if gym owner not found
                    } catch (DatabaseException e) {
                        System.out.println("Database error: " + e.getMessage());  // Custom message for DB error
                    }
                    break;
                case 6:
                    adminService.viewGymOwnerStatus();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7);
    }
}
