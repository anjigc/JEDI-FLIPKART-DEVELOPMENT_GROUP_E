package com.flipkart.client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.business.*;
import com.flipkart.dao.*;
import com.flipkart.utils.FlipFitDBConnection;

/**
 * This is the main class for the FlipFit application that serves as the entry point
 * for the user interface. It allows users to log in, register, change passwords,
 * and exit the application. It connects to the database and invokes appropriate
 * services for different user roles.
 */
public class FlipFitApplication {

    /**
     * The main method is the entry point of the FlipFit application. It presents a
     * menu for the user to choose options like login, registration, password change,
     * and exit. It also handles user input and delegates tasks to corresponding services.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Scanner instance for reading user input
        Scanner scanner = new Scanner(System.in);
        int choice; // Variable to store user's menu choice
        Connection connection = null; // Connection object to interact with the database

        try {
            // Establishing database connection
            connection = FlipFitDBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("Error connecting to the database");
        }

        try {
            // Creating DAO objects for different user roles
            FlipFitUserDAO userDAO = new FlipFitUserDAO();
            FlipFitAdminDAO adminDAO = new FlipFitAdminDAO();
            FlipFitGymOwnerDAO gymOwnerDAO = new FlipFitGymOwnerDAO();
            FlipFitCustomerDAO customerDAO = new FlipFitCustomerDAO();

            // Creating service objects for handling business logic
            FlipFitUserInterface userService = new FlipFitUserService();
            FlipFitAdminInterface adminService = new FlipFitAdminService();
            FlipFitGymOwnerInterface gymOwnerService = new FlipFitGymOwnerService();
            FlipFitCustomerInterface customerService = new FlipFitCustomerService();

            System.out.println("Welcome to FlipFit Application");

            do {
                // Displaying menu options
                System.out.println("1. Login");
                System.out.println("2. Registration as GymCustomer/GymOwner/Admin");
                System.out.println("3. Change password");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt(); // Reading user's choice
                scanner.nextLine(); // Clearing the input buffer

                switch (choice) {
                    case 1:
                        // Handling login
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();

                        // Authenticating user and navigating to respective menu
                        FlipFitUser flipFitUser = userService.loginUser(email, password);
                        if (flipFitUser == null) {
                            break;
                        }

                        switch (flipFitUser.getRoleId()) {
                            case 1 -> FlipFitAdminMenu.Menu(flipFitUser, adminService);
                            case 2 -> FlipFitGymOwnerMenu.Menu(flipFitUser, gymOwnerService);
                            case 3 -> FlipFitCustomerMenu.Menu(flipFitUser, customerService);
                            default -> System.out.println("Invalid credentials. Please try again.");
                        }
                        break;

                    case 2:
                        // Handling registration
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();
                        System.out.print("Enter contact: ");
                        String contact = scanner.nextLine();

                        System.out.println("Enter your role: 1) Admin 2) GymOwner 3) Customer");
                        int roleId = scanner.nextInt();
                        scanner.nextLine();

                        // Generating unique user ID
                        int userId = Math.abs(UUID.randomUUID().hashCode());
                        FlipFitUser user = userService.registerUser(userId, email, password, name, contact, roleId);

                        // Registering the user based on the selected role
                        switch (roleId) {
                            case 1 -> adminService.registerAdmin(userId);
                            case 2 -> {
                                System.out.print("Enter pan number: ");
                                String panNo = scanner.nextLine();
                                System.out.print("Enter aadhaar number: ");
                                String aadhaarNo = scanner.nextLine();
                                System.out.print("Enter address: ");
                                String address = scanner.nextLine();
                                gymOwnerService.registerGymOwner(userId, panNo, address, aadhaarNo);
                            }
                            case 3 -> {
                                System.out.print("Enter age: ");
                                int age = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter address: ");
                                String address = scanner.nextLine();
                                customerService.registerCustomer(userId, age, address);
                            }
                            default -> System.out.println("Invalid role. Please try again.");
                        }
                        break;

                    case 3:
                        // Handling password change
                        System.out.print("Enter your email: ");
                        email = scanner.nextLine();
                        System.out.print("Enter your old password: ");
                        String oldPass = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        password = scanner.nextLine();
                        userService.changePassword(email, oldPass, password);
                        System.out.println("Password changed successfully!");
                        break;

                    case 4:
                        // Exiting the application
                        System.out.println("Exiting FlipFit application...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 4); // Repeating until the user chooses to exit

        } catch (Exception e) {
            // Catching any exceptions and printing the stack trace
            e.printStackTrace();
        } finally {
            // Closing the database connection if it's open
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Closing the scanner object
        scanner.close();
    }
}
