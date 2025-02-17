package com.flipkart.business;

import com.flipkart.dao.FlipFitGymOwnerDAO;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Service class responsible for handling gym owner operations, including registering gym owners, adding gyms,
 * removing gyms, and viewing gym lists. Interacts with the {@link FlipFitGymOwnerDAO} for database operations.
 */
public class FlipFitGymOwnerService implements FlipFitGymOwnerInterface {

    private FlipFitGymOwnerDAO gymOwnerDAO;
    private Scanner scanner;

    /**
     * Constructor to initialize the FlipFitGymOwnerService with DAO and Scanner.
     * Initializes the gymOwnerDAO object for handling database operations and the scanner for user input.
     */
    public FlipFitGymOwnerService() {
        this.gymOwnerDAO = new FlipFitGymOwnerDAO();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Registers a new gym owner in the system.
     * 
     * @param id The unique identifier of the gym owner
     * @param panNo The PAN number of the gym owner
     * @param address The address of the gym owner
     * @param aadhaar The Aadhaar number of the gym owner
     * @return The registered {@link FlipFitGymOwner} object
     */
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

    /**
     * Adds a new gym to the system, including gym slots for the gym.
     * 
     * @param ownerId The ID of the gym owner adding the gym
     */
    public void addGym(int ownerId) {

        // Check if the gym owner is approved
        String status = isOwnerApproved(ownerId);

        if (status.equals("Pending")) {
            System.out.println("Gym Owner is not approved yet. Please wait for approval.");
            return;
        }
        else if (status.equals("Rejected")) {
            System.out.println("Gym Owner is rejected. Cannot add gym.");
            return;
        }

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
        int slotPrice = scanner.nextInt();
        scanner.nextLine();


        FlipFitGymCentre gymCentre = new FlipFitGymCentre();
        int gymId = Math.abs(UUID.randomUUID().hashCode());
        gymCentre.setGymId(gymId);
        gymCentre.setGymName(gymName);
        gymCentre.setGymAddress(location);
        gymCentre.setOwnerId(ownerId);
        gymCentre.setCapacity(capacity);
        gymCentre.setStatus("Pending");
        gymCentre.setSlotPrice(slotPrice);
        try {
            gymOwnerDAO.addGym(gymCentre);
            System.out.println("Gym '" + gymName + "' added successfully!");

            // Adding gym slots
            IntStream.range(0, slots).forEach(i -> {
                System.out.print("Enter Start Time for Slot " + (i + 1) + ": ");
                String startTime = scanner.nextLine();
                System.out.print("Enter End Time for Slot " + (i + 1) + ": ");
                String endTime = scanner.nextLine();

                FlipFitSlot slot = new FlipFitSlot();
                slot.setGymId(gymCentre.getGymId());
                slot.setStartTime(startTime);
                slot.setEndTime(endTime);
                slot.setAvailableSeats(capacity);

                // Add the slot to the database
                System.out.println("Adding slot -> " + slot);
                try {
                    gymOwnerDAO.addGymSlot(slot);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Slot " + (i + 1) + " added successfully!");
            });
        } catch (SQLException e) {
            System.err.println("Error adding gym: " + e.getMessage());
        }
    }

    /**
     * Removes a gym from the system.
     * 
     * @param ownerId The ID of the gym owner removing the gym
     */
    public void removeGym(int ownerId) {
        // Check if the gym owner is approved
        String status = isOwnerApproved(ownerId);

        if (status.equals("Pending")) {
            System.out.println("Gym Owner is not approved yet. Please wait for approval.");
            return;
        }
        else if (status.equals("Rejected")) {
            System.out.println("Gym Owner is rejected. Cannot remove gym.");
            return;
        }

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

    /**
     * Views the list of gyms owned by a specific gym owner.
     * 
     * @param ownerId The ID of the gym owner whose gyms need to be displayed
     */
    public void viewGymList(int ownerId) {

        // Check if the gym owner is approved
        String status = isOwnerApproved(ownerId);

        if (status.equals("Pending")) {
            System.out.println("Gym Owner is not approved yet. Please wait for approval.");
            return;
        }
        else if (status.equals("Rejected")) {
            System.out.println("Gym Owner is rejected. Cannot view gyms.");
            return;
        }

        try {
            List<FlipFitGymCentre> gymList = gymOwnerDAO.viewGymList(ownerId);
            if (gymList.isEmpty()) {
                System.out.println("No gyms found for the owner.");
            } else {
                System.out.println("\nGym List:");
                System.out.println("-------------------------------------");
                System.out.println("| Gym ID | Gym Name  | Location    | Status      |");
                System.out.println("-------------------------------------");
                gymList.stream().forEach(gym -> System.out.println("|   " + gym.getGymId() + "    | " + gym.getGymName() + "   | " + gym.getGymAddress() + "   | " + gym.getStatus() + "    |"));

                System.out.println("-------------------------------------\n");
            }
        } catch (SQLException e) {
            System.err.println("Error viewing gym list: " + e.getMessage());
        }
    }

    /**
     * Checks if the gym owner is approved or rejected.
     * 
     * @param ownerId The ID of the gym owner
     * @return The status of the gym owner
     */
    public String isOwnerApproved(int ownerId) {
        try {
            String status = gymOwnerDAO.getGymOwnerStatus(ownerId);
            return status;
        } catch (SQLException e) {
            System.err.println("Error checking gym owner status: " + e.getMessage());
            return "Error";
        }
    }
}
