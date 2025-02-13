package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.AdminService;

public class AdminMenu {
	private static Scanner scanner = new Scanner(System.in);
    public static void Menu() {
        
        AdminService adminService = new AdminService();
        int choice;
        
        do {
            System.out.println("Welcome Gym Admin");
            System.out.println("1. Approve Gym");
            System.out.println("2. Reject Gym");
            System.out.println("3. View Gym Status");
            System.out.println("4. Exit to main menu");
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
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
        
    }
}