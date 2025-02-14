package com.flipkart.business;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.dao.FlipFitAdminDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FlipFitAdminService implements FlipFitAdminInterface {

    private FlipFitAdminDAO adminDAO;
    private Scanner scanner = new Scanner(System.in);

    public FlipFitAdminService() {
        this.adminDAO = new FlipFitAdminDAO();
    }

    public FlipFitAdmin registerAdmin(int id) {
        FlipFitAdmin admin = new FlipFitAdmin();
        admin.setId(id);
        try {
            adminDAO.registerAdmin(admin);
        } catch (SQLException e) {
            System.out.println("Error registering admin: " + e.getMessage());
        }
        return admin;
    }

    public void approveGym() {
        System.out.print("Enter Gym ID to approve: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        try {
            adminDAO.approveGym(gymId);
            System.out.println("Gym ID " + gymId + " approved successfully!\n");
        } catch (SQLException e) {
            System.out.println("Error approving gym: " + e.getMessage());
        }
    }

    public void rejectGym() {
        System.out.print("Enter Gym ID to reject: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        try {
            adminDAO.rejectGym(gymId);
            System.out.println("Gym ID " + gymId + " rejected successfully!\n");
        } catch (SQLException e) {
            System.out.println("Error rejecting gym: " + e.getMessage());
        }
    }

    public void viewGymStatus() {
        try {
            List<FlipFitGymCentre> gyms = adminDAO.viewGymStatus();
            System.out.println("\nGym Status:");
            System.out.println("-------------------------------------");
            System.out.println("| Gym ID | Gym Name  | Status      |");
            System.out.println("-------------------------------------");
            for (FlipFitGymCentre gym : gyms) {
                System.out.printf("|   %d    | %s   | %s    |%n", gym.getGymId(), gym.getGymName(), gym.getStatus());
            }
            System.out.println("-------------------------------------\n");
        } catch (SQLException e) {
            System.out.println("Error retrieving gym status: " + e.getMessage());
        }
    }

    public void approveGymOwner() {
        System.out.print("Enter Gym Owner ID to approve: ");
        int gymOwnerId = scanner.nextInt();
        scanner.nextLine();
        try {
            adminDAO.approveGymOwner(gymOwnerId);
            System.out.println("Gym Owner ID " + gymOwnerId + " approved successfully!\n");
        } catch (SQLException e) {
            System.out.println("Error approving gym owner: " + e.getMessage());
        }
    }

    public void rejectGymOwner() {
        System.out.print("Enter Gym Owner ID to reject: ");
        int gymOwnerId = scanner.nextInt();
        scanner.nextLine();
        try {
            adminDAO.rejectGymOwner(gymOwnerId);
            System.out.println("Gym Owner ID " + gymOwnerId + " rejected successfully!\n");
        } catch (SQLException e) {
            System.out.println("Error rejecting gym owner: " + e.getMessage());
        }
    }

    public void viewGymOwnerStatus() {
        try {
            List<FlipFitGymOwner> owners = adminDAO.viewGymOwnerStatus();
            System.out.println("\nGym Owner Status:");
            System.out.println("-------------------------------------");
            System.out.println("| Gym Owner ID | Status      |");
            System.out.println("-------------------------------------");
            for (FlipFitGymOwner owner : owners) {
                System.out.printf("|      %d       | %s    |%n", owner.getId(), owner.getStatus());
            }
            System.out.println("-------------------------------------\n");
        } catch (SQLException e) {
            System.out.println("Error retrieving gym owner status: " + e.getMessage());
        }
    }
}
