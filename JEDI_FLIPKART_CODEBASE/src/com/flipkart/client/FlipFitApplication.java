package com.flipkart.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitPayment;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.business.*;


public class FlipFitApplication {

    private static HashMap<Integer, FlipFitUser> users = new HashMap<Integer, FlipFitUser>();
    private static HashMap<Integer, FlipFitGymOwner> gymOwners = new HashMap<Integer, FlipFitGymOwner>();
    private static HashMap<Integer, FlipFitCustomer> customers = new HashMap<Integer, FlipFitCustomer>();
    private static ArrayList<FlipFitAdmin> adminList = new ArrayList<FlipFitAdmin>();
    private static HashMap<Integer, FlipFitBooking> slotBookings = new HashMap<Integer, FlipFitBooking>();
    private static HashMap<Integer, FlipFitGymCentre> gymCentres = new HashMap<Integer, FlipFitGymCentre>();
    private static HashMap<Integer, FlipFitSlot> gymSlots = new HashMap<Integer, FlipFitSlot>();
    private static HashMap<String, FlipFitPayment> payments = new HashMap<String, FlipFitPayment>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int idGenerator = 1;

        FlipFitUserInterface userService = new FlipFitUserService(users);
        FlipFitAdminInterface adminService = new FlipFitAdminService(adminList, gymCentres, gymOwners, users);
        FlipFitGymOwnerInterface gymOwnerService = new FlipFitGymOwnerService(gymOwners, gymCentres, gymSlots);
        FlipFitCustomerInterface customerService = new FlipFitCustomerService(customers, gymCentres, slotBookings, gymSlots, payments);

        System.out.println("Welcome to FlipFit Application");
        do {
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
                    System.out.println("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.println("Enter password: ");
                    String password = scanner.nextLine();

                    FlipFitUser flipFitUser = userService.loginUser(email, password);

                    if (flipFitUser == null) {
                        break;
                    }

                    if(flipFitUser.getRoleId() == 1) {
                        FlipFitAdminMenu.Menu(flipFitUser, adminService);
                    } else if(flipFitUser.getRoleId() == 2) {
                        FlipFitGymOwnerMenu.Menu(flipFitUser, gymOwnerService);
                    } else if(flipFitUser.getRoleId() == 3) {
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
                    
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    
                    userService.registerUser(idGenerator, email, password, name, contact, 3);
                    customerService.registerCustomer(idGenerator, age, address);
                    idGenerator++;
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
                    
                    userService.registerUser(idGenerator, email, password, name, contact, 2);
                    gymOwnerService.registerGymOwner(idGenerator, panNo, address, aadhaarNo);
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