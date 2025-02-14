package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.FlipFitAdminInterface;
import com.flipkart.bean.FlipFitUser;

public class FlipFitAdminMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void Menu(FlipFitUser adminUser, FlipFitAdminInterface adminService) {
        int choice;

        System.out.println("Welcome, Admin " + adminUser.getName() + "!");
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Approve Gym");
            System.out.println("2. Reject Gym");
            System.out.println("3. View Gym List");
            System.out.println("4. Approve Gym Owner");
            System.out.println("5. Reject Gym Owner");
            System.out.println("6. View Gym Owner Status");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    adminService.approveGym();
                    break;
                case 2:
                    adminService.rejectGym();
                    break;
                case 3:
                    adminService.viewGymStatus();
                    break;
                case 4:
                    adminService.approveGymOwner();
                    break;
                case 5:
                    adminService.rejectGymOwner();
                    break;
                case 6:
                    adminService.viewGymOwnerStatus();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7);
    }
}
