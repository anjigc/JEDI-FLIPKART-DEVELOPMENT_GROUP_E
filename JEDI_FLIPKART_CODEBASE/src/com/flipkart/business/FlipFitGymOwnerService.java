package com.flipkart.business;

import java.util.Scanner;
import com.flipkart.bean.FlipFitGymOwner;

public class FlipFitGymOwnerService implements FlipFitGymOwnerInterface {
	
	public FlipFitGymOwner registerGymOwner(String name, String email, String password, String contact, String panNo, String address, String Aadhaar) {
		FlipFitGymOwner gymOwner = new FlipFitGymOwner();
		gymOwner.setName(name);
		gymOwner.setEmail(email);
		gymOwner.setPassword(password);
		gymOwner.setContact(contact);
		gymOwner.setPanNo(panNo);
		gymOwner.setAddress(address);
		gymOwner.setAadhaar(Aadhaar);
		gymOwner.setRole("FlipFit Gym Owner");

        System.out.println("User with email " + email + " registered as FLipfit Gym Owner Successfully!");

        return gymOwner;
    }
	
	public void loginGymOwner(String email, String password) {
        System.out.println("User with email " + email + " logged in as Gym Owner successfully!");

    }

    public void logoutGymOwner(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }
	
     private Scanner scanner = new Scanner(System.in);
    
    public void addGym() {
        System.out.print("Enter Gym Name: ");
        String gymName = scanner.nextLine();
        System.out.print("Enter Gym Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Gym Slots Count: ");
        int slots = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Gym Capacity of each Slot: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Gym '" + gymName + "' added successfully at " + location + " for " + slots + " slots with capacity of " + capacity + " for each slot." +"\n");
        for(int i = 0; i < slots; i++){
            System.out.print("Enter Gym Slot " + i+1 + " Details: " + "\n");
            System.out.print("Enter Start Time for Slot " + i+1 + ":");
            int startTime = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter End Time for Slot " + i+1 + ":");
            int endTime = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Slot details added from start time " + startTime + " to end time " + endTime + "!\n");
        }
    }
    
    public void removeGym() {
        System.out.print("Enter Gym ID to remove: ");
        int gymId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Gym ID " + gymId + " removed successfully!\n");
    }
    
    public void viewGymList() {
        System.out.println("\nGym List:");
        System.out.println("-------------------------------------");
        System.out.println("| Gym ID | Gym Name  | Location    |");
        System.out.println("-------------------------------------");
        System.out.println("|   1    | FitZone   | Downtown    |");
        System.out.println("|   2    | Iron Gym  | Uptown      |");
        System.out.println("|   3    | PowerFit  | Suburb      |");
        System.out.println("-------------------------------------\n");
    }
}