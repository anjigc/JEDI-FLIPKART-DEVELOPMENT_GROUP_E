package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.GymOwnerService;

public class GymOwnerMenu {
	private static Scanner scanner = new Scanner(System.in);
    public static void Menu() {
        
        GymOwnerService gymOwnerService = new GymOwnerService();
        int choice;
        
        do {
            System.out.println("Welcome Gym Owner");
            System.out.println("1. Add Gym");
            System.out.println("2. Remove Gym");
            System.out.println("3. View Gym List");
            System.out.println("4. Exit to main menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    gymOwnerService.addGym();
                    break;
                case 2:
                    gymOwnerService.removeGym();
                    break;
                case 3:
                    gymOwnerService.viewGymList();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
        
    }
}