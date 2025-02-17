package com.flipkart.client;

import java.util.Scanner;
import java.util.List;
import com.flipkart.business.FlipFitCustomerInterface;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.*;

public class FlipFitCustomerMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void Menu(FlipFitUser customerUser, FlipFitCustomerInterface customerService) {
        System.out.println("Welcome, Customer " + customerUser.getName() + "!");
        List<String> menuOptions = List.of(
                "1. View Gyms",
                "2. Select Gym to view slots",
                "3. Book a Gym Slot",
                "4. View My Bookings",
                "5. Logout"
        );

        int choice;
        do {
            System.out.println("\n--- Customer Menu ---");
            menuOptions.forEach(System.out::println);
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> customerService.viewGymList();
                    case 2 -> customerService.selectGym();
                    case 3 -> customerService.bookGymSlot(customerUser.getId());
                    case 4 -> customerService.viewMyBookings(customerUser.getId());
                    case 5 -> System.out.println("Logging out...");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (NoGymsFoundException | NoSlotsAvailableException | GymNotFoundException
                     | BookingFailedException | NoBookingsFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        } while (choice != 5);
    }
}
