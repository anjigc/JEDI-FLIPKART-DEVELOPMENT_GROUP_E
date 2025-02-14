package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.FlipFitCustomerInterface;
import com.flipkart.bean.FlipFitUser;

public class FlipFitCustomerMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void Menu(FlipFitUser customerUser, FlipFitCustomerInterface customerService) {
        int choice;

        System.out.println("Welcome, Customer " + customerUser.getName() + "!");
        do {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Gyms");
            System.out.println("2. Select Gym to view slots");
            System.out.println("3. Book a Gym Slot");
            System.out.println("4. View My Bookings");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerService.viewGymList();
                    break;
                case 2:
                    customerService.selectGym();
                    break;
                case 3:
                    customerService.bookGymSlot(customerUser.getId());
                    break;
                case 4:
                    customerService.viewMyBookings(customerUser.getId());
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }
}
