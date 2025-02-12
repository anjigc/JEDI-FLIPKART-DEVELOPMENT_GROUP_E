package com.flipkart.client;

import com.flipkart.bean.User;
import com.flipkart.service.UserBusiness;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserBusiness userBusiness = new UserBusiness();
        User currentUser = null;
        int choice;

        do {
            System.out.println("Welcome to FlipFit application");
            System.out.println("Enter the choice: ");
            System.out.println("1. Login");
            System.out.println("2. Registration of the gym customer");
            System.out.println("3. Registration of the gym owner");
            System.out.println("4. Change password");
            System.out.println("5. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    // Login
                    currentUser = userBusiness.login(scanner);
                    if (currentUser != null) {
                        // Check role and display respective menus
                        if (currentUser.getType().equalsIgnoreCase("gym owner")) {
                            gymOwnerMenu(scanner);
                        } else if (currentUser.getType().equalsIgnoreCase("gym customer")) {
                            gymCustomerMenu(scanner);
                        } else if (currentUser.getType().equalsIgnoreCase("gym admin")) {
                            gymAdminMenu(scanner);
                        }
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 2:
                    // Registration of gym customer
                    userBusiness.registerCustomer(scanner);
                    break;

                case 3:
                    // Registration of gym owner
                    userBusiness.registerOwner(scanner);
                    break;

                case 4:
                    // Change password
                    if (currentUser != null) {
                        userBusiness.changePassword(scanner, currentUser);
                    } else {
                        System.out.println("You need to be logged in to change password.");
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting FlipFit application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    // Menu for gym owner
    public static void gymOwnerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Welcome Gym Owner");
            System.out.println("1. Add Gym");
            System.out.println("2. Update Gym");
            System.out.println("3. Remove Gym");
            System.out.println("4. View Gym Details");
            System.out.println("5. Exit to main menu");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Add Gym Logic
                    System.out.println("Adding gym...");
                    break;
                case 2:
                    // Update Gym Logic
                    System.out.println("Updating gym...");
                    break;
                case 3:
                    // Remove Gym Logic
                    System.out.println("Removing gym...");
                    break;
                case 4:
                    // View Gym Details Logic
                    System.out.println("Viewing gym details...");
                    break;
                case 5:
                    // Exit to main menu
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    // Menu for gym customer
    public static void gymCustomerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Welcome Gym Customer");
            System.out.println("1. View Available Slots");
            System.out.println("2. Book Gym Slot");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit to main menu");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // View Available Slots Logic
                    System.out.println("Viewing available slots...");
                    break;
                case 2:
                    // Book Gym Slot Logic
                    System.out.println("Booking gym slot...");
                    break;
                case 3:
                    // View My Bookings Logic
                    System.out.println("Viewing your bookings...");
                    break;
                case 4:
                    // Exit to main menu
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }

    // Menu for gym admin
    public static void gymAdminMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Welcome Gym Admin");
            System.out.println("1. Approve Gym");
            System.out.println("2. Reject Gym");
            System.out.println("3. View Gym Status");
            System.out.println("4. Exit to main menu");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Approve Gym Logic
                    System.out.println("Approving gym...");
                    break;
                case 2:
                    // Reject Gym Logic
                    System.out.println("Rejecting gym...");
                    break;
                case 3:
                    // View Gym Status Logic
                    System.out.println("Viewing gym status...");
                    break;
                case 4:
                    // Exit to main menu
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }
}
