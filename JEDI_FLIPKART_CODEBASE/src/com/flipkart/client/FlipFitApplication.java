package com.flipkart.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.business.*;
import com.flipkart.dao.*;
import com.flipkart.utils.FlipFitDBConnection;

import java.util.UUID;

public class FlipFitApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        Connection connection = null;


        try{
            connection = FlipFitDBConnection.getConnection();
        }catch(Exception e){
                System.out.println("Error connecting to the database");
        }
        
        try {
            FlipFitUserDAO userDAO = new FlipFitUserDAO();
            FlipFitAdminDAO adminDAO = new FlipFitAdminDAO();
            FlipFitGymOwnerDAO gymOwnerDAO = new FlipFitGymOwnerDAO();
            FlipFitCustomerDAO customerDAO = new FlipFitCustomerDAO();

            FlipFitUserInterface userService = new FlipFitUserService();
            FlipFitAdminInterface adminService = new FlipFitAdminService();
            FlipFitGymOwnerInterface gymOwnerService = new FlipFitGymOwnerService();
            FlipFitCustomerInterface customerService = new FlipFitCustomerService();

             

            System.out.println("Welcome to FlipFit Application");
            do {
                System.out.println("1. Login");
                System.out.println("2. Registration as GymCustomer/GymOwner/Admin");
                System.out.println("3. Change password");
                System.out.println("4. Exit");
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.println("Enter password: ");
                        String password = scanner.nextLine();

                        FlipFitUser flipFitUser = userService.loginUser(email, password);
                        if (flipFitUser == null) {
                            break;
                        }

                        if (flipFitUser.getRoleId() == 1) {
                            FlipFitAdminMenu.Menu(flipFitUser, adminService);
                        } else if (flipFitUser.getRoleId() == 2) {
                            FlipFitGymOwnerMenu.Menu(flipFitUser, gymOwnerService);
                        } else if (flipFitUser.getRoleId() == 3) {
                            FlipFitCustomerMenu.Menu(flipFitUser, customerService);
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
                        
                        System.out.println("Enter your role: 1) Admin 2) GymOwner 3) Customer");
                        int roleId = scanner.nextInt();
                        scanner.nextLine();

                        int userId =Math.abs(UUID.randomUUID().hashCode());
                        FlipFitUser user = userService.registerUser(userId, email, password, name, contact, roleId);

                        if (roleId == 1) {
                            adminService.registerAdmin(userId);
                        } else if (roleId == 2) {
                            System.out.print("Enter pan number: ");
                            String panNo = scanner.nextLine();
                            System.out.print("Enter aadhaar number: ");
                            String aadhaarNo = scanner.nextLine();
                            System.out.print("Enter address: ");
                            String address = scanner.nextLine();

                            gymOwnerService.registerGymOwner(userId, panNo, address, aadhaarNo);
                        } else if (roleId == 3) {
                            System.out.print("Enter age: ");
                            int age = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter address: ");
                            String address = scanner.nextLine();

                            customerService.registerCustomer(userId, age, address);
                        } else {
                            System.out.println("Invalid role. Please try again.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter your email: ");
                        email = scanner.nextLine();
                        System.out.println("Enter your old password: ");
                        String oldPass = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        password = scanner.nextLine();
                        userService.changePassword(email,oldPass, password);
                        System.out.println("Password changed successfully!");
                        break;
                    case 4:
                        System.out.println("Exiting FlipFit application...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 4);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}
