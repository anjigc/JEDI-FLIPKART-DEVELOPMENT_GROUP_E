package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.FlipFitGymOwnerInterface;
import com.flipkart.bean.FlipFitUser;

public class FlipFitGymOwnerMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void Menu(FlipFitUser gymOwnerUser, FlipFitGymOwnerInterface gymOwnerService) {
        int choice;

        System.out.println("Welcome, Gym Owner " + gymOwnerUser.getName() + "!");
        do {
            System.out.println("\n--- Gym Owner Menu ---");
            System.out.println("1. Add Gym");
            System.out.println("2. Remove Gym");
            System.out.println("3. View Gym List");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    gymOwnerService.addGym(gymOwnerUser.getId());
                    break;
                case 2:
                    gymOwnerService.removeGym();
                    break;
                case 3:
                    gymOwnerService.viewGymList(gymOwnerUser.getId());
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }
}
