package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.*;


public class FlipFitApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        FlipFitUserInterface userService = new FlipFitUserService();
        FlipFitAdminInterface adminService = new FlipFitAdminService();
        FlipFitGymOwnerInterface gymOwnerService = new FlipFitGymOwnerService();
        FlipFitCustomerInterface customerService = new FlipFitCustomerService();

        do {
            System.out.println("Welcome to FlipFit Application");
            System.out.println("1. Login");
            System.out.println("2. Registration of the gym customer");
            System.out.println("3. Registration of the gym owner");
            System.out.println("4. Change password");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    
                    String currentUserRole = userService.loginUser(email, password);
                    if (currentUserRole != null) {
                        if (currentUserRole == "Flipfit Customer") {
                        	FlipFitCustomerMenu.Menu();
                        } else if (currentUserRole == "Flipfit Gym Owner") {
                            FlipFitGymOwnerMenu.Menu();
                        } else if (currentUserRole == "Flipfit Admin") {
                        	FlipFitAdminMenu.Menu();
                        }
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter email: ");
                    email = scanner.nextLine();

                    System.out.print("Enter password: ");
                    password = scanner.nextLine();

                    System.out.print("Enter contact: ");
                    String contact = scanner.nextLine();
                    
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    
                    customerService.registerCustomer(name, email, password, contact, age, address);
                    break;
                case 3:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();

                    System.out.print("Enter email: ");
                    email = scanner.nextLine();

                    System.out.print("Enter password: ");
                    password = scanner.nextLine();

                    System.out.print("Enter contact: ");
                    contact = scanner.nextLine();
                    
                    System.out.print("Enter pan number: ");
                    String panNo = scanner.nextLine();

                    System.out.print("Enter aadhaar number: ");
                    String aadhaarNo = scanner.nextLine();

                    System.out.print("Enter address: ");
                    address = scanner.nextLine();
                    
                    gymOwnerService.registerGymOwner(name, email, password, contact, panNo, address, aadhaarNo);
                    break;
                case 4:
                        System.out.print("Enter your email: ");
                        email = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        password = scanner.nextLine();
                        System.out.println("Password changed successfully!");
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

}