package com.flipkart.client;

import java.util.Scanner;
import java.util.List;
import com.flipkart.business.FlipFitGymOwnerInterface;
import com.flipkart.bean.FlipFitUser;

public class FlipFitGymOwnerMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void Menu(FlipFitUser gymOwnerUser, FlipFitGymOwnerInterface gymOwnerService) {
        System.out.println("Welcome, Gym Owner " + gymOwnerUser.getName() + "!");

        List<String> menuOptions = List.of(
                "1. Add Gym",
                "2. Remove Gym",
                "3. View Gym List",
                "4. Logout"
        );

        int choice;
        do {
            System.out.println("\n--- Gym Owner Menu ---");
            menuOptions.forEach(System.out::println);
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> gymOwnerService.addGym(gymOwnerUser.getId());
                    case 2 -> gymOwnerService.removeGym();
                    case 3 -> gymOwnerService.viewGymList(gymOwnerUser.getId());
                    case 4 -> System.out.println("Logging out...");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 4);
    }
}
