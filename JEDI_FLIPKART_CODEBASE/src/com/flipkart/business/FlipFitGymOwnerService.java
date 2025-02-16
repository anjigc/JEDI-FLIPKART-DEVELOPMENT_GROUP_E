package com.flipkart.business;

import com.flipkart.dao.FlipFitGymOwnerDAO;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import java.util.UUID;

public class FlipFitGymOwnerService implements FlipFitGymOwnerInterface {

    private FlipFitGymOwnerDAO gymOwnerDAO;
    private Scanner scanner;

    public FlipFitGymOwnerService() {
        this.gymOwnerDAO = new FlipFitGymOwnerDAO();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public FlipFitGymOwner registerGymOwner(int id, String panNo, String address, String aadhaar) {
        FlipFitGymOwner gymOwner = new FlipFitGymOwner();
        gymOwner.setId(id);
        gymOwner.setPanNo(panNo);
        gymOwner.setAddress(address);
        gymOwner.setAadhaar(aadhaar);
        gymOwner.setStatus("Pending");

        try {
            gymOwnerDAO.registerGymOwner(gymOwner);
            System.out.println("Gym Owner registered successfully with ID: " + id);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

        return gymOwner;
    }

    public void addGym(int ownerId) {
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

        FlipFitGymCentre gymCentre = new FlipFitGymCentre();
        int gymId = Math.abs(UUID.randomUUID().hashCode());
        gymCentre.setGymId(gymId);
        gymCentre.setGymName(gymName);  
        gymCentre.setGymAddress(location);
        gymCentre.setOwnerId(ownerId);
        gymCentre.setCapacity(capacity);
        gymCentre.setStatus("Pending");

        try {
            gymOwnerDAO.addGym(gymCentre);
            System.out.println("Gym '" + gymName + "' added successfully!");

            // Adding gym slots
            for (int i = 0; i < slots; i++) {
                System.out.print("Enter Start Time for Slot " + (i + 1) + ": ");
                String startTime = scanner.nextLine();
                System.out.print("Enter End Time for Slot " + (i + 1) + ": ");
                String endTime = scanner.nextLine();

                FlipFitSlot slot = new FlipFitSlot();
                slot.setGymId(gymCentre.getGymId());
                slot.setStartTime(startTime);
                slot.setEndTime(endTime);
                slot.setAvailableSeats(capacity);
                
                System.out.println("Adding slot -> " + slot);
                gymOwnerDAO.addGymSlot(slot);
                System.out.println("Slot " + (i + 1) + " added successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error adding gym: " + e.getMessage());
        }
    }

    public void removeGym() {
        System.out.print("Enter Gym ID to remove: ");
        int gymId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            gymOwnerDAO.removeGym(gymId);
            System.out.println("Gym with ID " + gymId + " removed successfully!");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    public void viewGymList(int ownerId) {
        try {
            List<FlipFitGymCentre> gymList = gymOwnerDAO.viewGymList(ownerId);
            if (gymList.isEmpty()) {
                System.out.println("No gyms found for the owner.");
            } else {
                System.out.println("\nGym List:");
                System.out.println("-------------------------------------");
                System.out.println("| Gym ID | Gym Name  | Location    | Status      |");
                System.out.println("-------------------------------------");
                for (FlipFitGymCentre gym : gymList) {
                    System.out.println("|   " + gym.getGymId() + "    | " + gym.getGymName() + "   | " + gym.getGymAddress() + "   | " + gym.getStatus() + "    |");
                }
                System.out.println("-------------------------------------\n");
            }
        } catch (SQLException e) {
            System.err.println("Error viewing gym list: " + e.getMessage());
        }
    }
}