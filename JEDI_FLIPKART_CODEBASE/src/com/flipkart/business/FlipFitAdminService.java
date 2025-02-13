package com.flipkart.business;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FlipFitAdminService implements FlipFitAdminInterface {

    private ArrayList<FlipFitAdmin> adminList;
    private ArrayList<FlipFitGymOwner> gymOwnerList;
    private HashMap<Integer, FlipFitGymCentre> gymCentres;

    public FlipFitAdminService(ArrayList<FlipFitAdmin> adminList, HashMap<Integer, FlipFitGymCentre> gymCentres, ArrayList<FlipFitGymOwner> gymOwnerList) {
        this.adminList = adminList;
        this.gymOwnerList = gymOwnerList;
        this.gymCentres = gymCentres;
    }

    public void loginAdmin(String email, String password) {
        System.out.println("User with email " + email + " logged in as Admin successfully!");
    }

    public void logoutAdmin(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }

	public FlipFitAdmin registerAdmin(String name, String email, String password, String contact) {
		FlipFitAdmin admin = new FlipFitAdmin();
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

    public void approveGymOwner() {
        System.out.print("Enter Gym Owner ID to approve: ");
        int gymOwnerId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Gym Owner ID " + gymOwnerId + " approved successfully!\n");
    }

    public void rejectGymOwner() {
        System.out.print("Enter Gym Owner ID to reject: ");
        int gymOwnerId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Gym Owner ID " + gymOwnerId + " rejected successfully!\n");
    }

    public void viewGymOwnerStatus() {
        System.out.println("\nGym Owner Status:");
        System.out.println("-------------------------------------");
        System.out.println("| Gym Owner ID | GymOwner  | Status      |");
        System.out.println("-------------------------------------");
        System.out.println("|      1       | Anjita    | Approved    |");
        System.out.println("|      2       | Sakshi    | Pending     |");
        System.out.println("|      3       | Sujan     | Rejected    |");
        System.out.println("-------------------------------------\n");
    }

}
