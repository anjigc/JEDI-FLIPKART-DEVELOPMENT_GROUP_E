package com.flipkart.client;

import java.util.Scanner;
import java.util.List;
import com.flipkart.business.FlipFitAdminInterface;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.exception.GymOwnerNotFoundException;
import com.flipkart.exception.GymStatusNotFoundException;

public class FlipFitAdminMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void Menu(FlipFitUser adminUser, FlipFitAdminInterface adminService) {
        System.out.println("Welcome, Admin " + adminUser.getName() + "!");
        List<String> menuOptions = List.of(
                "1. Approve Gym",
                "2. Reject Gym",
                "3. View Gym List",
                "4. Approve Gym Owner",
                "5. Reject Gym Owner",
                "6. View Gym Owner Status",
                "7. Logout"
        );

        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            menuOptions.forEach(System.out::println);
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> adminService.approveGym();
                    case 2 -> adminService.rejectGym();
                    case 3 -> adminService.viewGymStatus();
                    case 4 -> adminService.approveGymOwner();
                    case 5 -> adminService.rejectGymOwner();
                    case 6 -> adminService.viewGymOwnerStatus();
                    case 7 -> System.out.println("Logging out...");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (GymNotFoundException | GymOwnerNotFoundException | GymStatusNotFoundException | DatabaseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 7);
    }
}
