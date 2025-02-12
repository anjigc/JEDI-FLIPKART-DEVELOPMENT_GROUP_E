package com.flipkart.business;

import com.flipkart.bean.Admin;
import java.util.Scanner;

public class AdminService extends UserService {
	
	public Admin registerAdmin(String name, String email, String password, String contact) {
		Admin admin = new Admin();
		admin.setName(name);
		admin.setEmail(email);
		admin.setPassword(password);
		admin.setContact(contact);
		admin.setRole("FlipFit Admin");
        return admin;
    }

   private Scanner scanner = new Scanner(System.in);
    
    public void approveGym() {
        System.out.print("Enter Gym ID to approve: ");
        int gymId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Gym ID " + gymId + " approved successfully!\n");
    }
    
    public void rejectGym() {
        System.out.print("Enter Gym ID to reject: ");
        int gymId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Gym ID " + gymId + " rejected successfully!\n");
    }
    
    public void viewGymStatus() {
        System.out.println("\nGym Status:");
        System.out.println("-------------------------------------");
        System.out.println("| Gym ID | Gym Name  | Status      |");
        System.out.println("-------------------------------------");
        System.out.println("|   1    | FitZone   | Approved    |");
        System.out.println("|   2    | Iron Gym  | Pending     |");
        System.out.println("|   3    | PowerFit  | Rejected    |");
        System.out.println("-------------------------------------\n");
    }
}
