package com.flipkart.business;

import com.flipkart.bean.Gym;
import java.util.Scanner;
import com.flipkart.bean.GymOwner;

public class GymOwnerService {
	
	public GymOwner registerGymOwner(String name, String email, String password, String contact, String panNo, String address, String Aadhaar) {
		GymOwner gymOwner = new GymOwner();
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
	
     private Scanner scanner = new Scanner(System.in);
    
    public void addGym() {
        System.out.print("Enter Gym Name: ");
        String gymName = scanner.nextLine();
        System.out.print("Enter Gym Location: ");
        String location = scanner.nextLine();
        System.out.println("Gym '" + gymName + "' added successfully at " + location + "!\n");
    }
    
    public void updateGym() {
        System.out.print("Enter Gym ID to update: ");
        int gymId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new Gym Name: ");
        String newGymName = scanner.nextLine();
        System.out.println("Gym ID " + gymId + " updated successfully to '" + newGymName + "'!\n");
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