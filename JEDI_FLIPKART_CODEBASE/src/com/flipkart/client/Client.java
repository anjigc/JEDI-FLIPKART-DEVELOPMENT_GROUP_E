package com.flipkart.client;

import com.flipkart.bean.User;
import com.flipkart.business.UserBusiness;
import java.util.Scanner;
import com.flipkart.business.CustomerBusiness;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserBusiness userBusiness = new UserBusiness();
        CustomerBusiness customerBusiness = new CustomerBusiness();
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
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    currentUser = userBusiness.loginUser(email, password);
                    if (currentUser != null) {
                        if (currentUser.getRole().equalsIgnoreCase("gym owner")) {
                            gymOwnerMenu(scanner);
                        } else if (currentUser.getRole().equalsIgnoreCase("gym customer")) {
                            gymCustomerMenu(scanner);
                        } else if (currentUser.getRole().equalsIgnoreCase("gym admin")) {
                            gymAdminMenu(scanner);
                        }
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter mobile number: ");
                    String mobileNo = scanner.nextLine();
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    customerBusiness.registerCustomer(name, mobileNo, email, age, address);
                    System.out.println("Gym customer registered successfully!");
                    break;
                case 3:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter mobile number: ");
                    mobileNo = scanner.nextLine();
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    userBusiness.registerUser(name, mobileNo, email, "gym owner");
                    System.out.println("Gym owner registered successfully!");
                    break;
                case 4:
                    if (currentUser != null) {
                        System.out.print("Enter new password: ");
                        password = scanner.nextLine();
                        System.out.println("Password changed successfully!");
                    } else {
                        System.out.println("You need to be logged in to change password.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting FlipFit application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Adding gym...");
                    break;
                case 2:
                    System.out.println("Updating gym...");
                    break;
                case 3:
                    System.out.println("Removing gym...");
                    break;
                case 4:
                    System.out.println("Viewing gym details...");
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    public static void gymCustomerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Welcome Gym Customer");
            System.out.println("1. View Available Slots");
            System.out.println("2. Book Gym Slot");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit to main menu");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Viewing available slots...");
                    break;
                case 2:
                    System.out.println("Booking gym slot...");
                    break;
                case 3:
                    System.out.println("Viewing your bookings...");
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }

    public static void gymAdminMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Welcome Gym Admin");
            System.out.println("1. Approve Gym");
            System.out.println("2. Reject Gym");
            System.out.println("3. View Gym Status");
            System.out.println("4. Exit to main menu");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Approving gym...");
                    break;
                case 2:
                    System.out.println("Rejecting gym...");
                    break;
                case 3:
                    System.out.println("Viewing gym status...");
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }
}