package com.flipkart.business;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FlipFitAdminService implements FlipFitAdminInterface {

    private ArrayList<FlipFitAdmin> adminList;
    private HashMap<Integer, FlipFitGymOwner> gymOwners;
    private HashMap<Integer, FlipFitGymCentre> gymCentres;
    private HashMap<Integer, FlipFitUser> Users;
    private Scanner scanner = new Scanner(System.in);

    public FlipFitAdminService(ArrayList<FlipFitAdmin> adminList, HashMap<Integer, FlipFitGymCentre> gymCentres, HashMap<Integer, FlipFitGymOwner> gymOwners, HashMap<Integer, FlipFitUser> Users) {
        this.adminList = adminList;
        this.gymOwners = gymOwners;
        this.gymCentres = gymCentres;
        this.Users = Users;
    }

	public FlipFitAdmin registerAdmin(int id) {
        FlipFitAdmin admin = new FlipFitAdmin();
        admin.setId(id);
        adminList.add(admin);
        return admin;
    }

    public void approveGym() {
        System.out.print("Enter Gym ID to approve: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        if (gymCentres.containsKey(gymId)) {
            gymCentres.get(gymId).setStatus("Approved");
            System.out.println("Gym ID " + gymId + " approved successfully!\n");
        } else {
            System.out.println("Gym ID not found!\n");
        }
    }

    public void rejectGym() {
        System.out.print("Enter Gym ID to reject: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        if (gymCentres.containsKey(gymId)) {
            gymCentres.get(gymId).setStatus("Rejected");
            System.out.println("Gym ID " + gymId + " rejected successfully!\n");
        } else {
            System.out.println("Gym ID not found!\n");
        }
    }

    public void viewGymStatus() {
        System.out.println("\nGym Status:");
        System.out.println("-------------------------------------");
        System.out.println("| Gym ID | Gym Name  | Status      |");
        System.out.println("-------------------------------------");
        for (FlipFitGymCentre gym : gymCentres.values()) {
            System.out.printf("|   %d    | %s   | %s    |%n", gym.getGymId(), gym.getGymName(), gym.getStatus());
        }
        System.out.println("-------------------------------------\n");
    }

    public void approveGymOwner() {
        System.out.print("Enter Gym Owner ID to approve: ");
        int gymOwnerId = scanner.nextInt();
        scanner.nextLine();
        if (gymOwners.containsKey(gymOwnerId)) {
            gymOwners.get(gymOwnerId).setStatus("Approved");
            System.out.println("Gym Owner ID " + gymOwnerId + " approved successfully!\n");
        } else {
            System.out.println("Gym Owner ID not found!\n");
        }
    }

    public void rejectGymOwner() {
        System.out.print("Enter Gym Owner ID to reject: ");
        int gymOwnerId = scanner.nextInt();
        scanner.nextLine();
        if (gymOwners.containsKey(gymOwnerId)) {
            gymOwners.get(gymOwnerId).setStatus("Rejected");
            System.out.println("Gym Owner ID " + gymOwnerId + " rejected successfully!\n");
        } else {
            System.out.println("Gym Owner ID not found!\n");
        }
    }

    public void viewGymOwnerStatus() {
        System.out.println("\nGym Owner Status:");
        System.out.println("-------------------------------------");
        System.out.println("| Gym Owner ID | Gym Owner  | Status      |");
        System.out.println("-------------------------------------");
        for (FlipFitGymOwner owner : gymOwners.values()) {
            String ownerName = Users.get(owner.getId()).getName();
            System.out.printf("|      %d       | %s    | %s    |%n", owner.getId(), ownerName, owner.getStatus());
        }
        System.out.println("-------------------------------------\n");
    }
}

