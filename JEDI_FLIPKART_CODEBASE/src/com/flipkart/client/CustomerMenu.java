/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;
import com.flipkart.business.CustomerService;
/**
 * 
 */
public class CustomerMenu {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void Menu() {
		
		CustomerService customerService = new CustomerService();
	    int choice;
    	
        do {
            System.out.println("Welcome Gym Customer");
            System.out.println("1. View Available Slots");
            System.out.println("2. Book Gym Slot");
            System.out.println("3. View My Bookings");
            System.out.println("4. Logout (Exit to main menu)");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                	customerService.viewAvailableSlots();
                    break;
                case 2:
                	customerService.bookGymSlot();
                    break;
                case 3:
                	customerService.viewMyBookings();
                    break;
                case 4:
                    System.out.println("Logged Out, Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
	}
}
