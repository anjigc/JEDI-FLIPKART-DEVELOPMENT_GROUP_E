package com.flipkart.business;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.dao.FlipFitAdminDAO;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.exception.GymOwnerNotFoundException;
import com.flipkart.exception.GymStatusNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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

    public void approveGym() throws GymNotFoundException, DatabaseException {
        System.out.print("Enter Gym ID to approve: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        try {
            if(gymId == 0){
                throw new GymNotFoundException("Gym ID " + gymId + " not found.");
            }

            List<FlipFitGymCentre> gyms = adminDAO.viewGymStatus();
            boolean gymFound = false;
            for (FlipFitGymCentre gym : gyms) {
                if (gym.getGymId() == gymId) {
                    gymFound = true;
                    break;
                }
            }
            if(!gymFound){
                throw new GymNotFoundException("Gym with ID " + gymId + " not found.");
            }
            adminDAO.approveGym(gymId);

            System.out.println("Gym ID " + gymId + " approved successfully!\n");
        } catch (SQLException e) {
            throw new DatabaseException("Database error while retrieving gym status: " + e.getMessage());
        } catch (GymNotFoundException e) {
            throw e;
        }
    }

    public void rejectGym() throws GymNotFoundException, DatabaseException {
        System.out.print("Enter Gym ID to reject: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        try {
            if (gymId == 0) {
                throw new GymNotFoundException("Gym ID " + gymId + " not found.");
            }

            List<FlipFitGymCentre> gyms = adminDAO.viewGymStatus();
            boolean gymFound = false;
            for (FlipFitGymCentre gym : gyms) {
                if (gym.getGymId() == gymId) {
                    gymFound = true;
                    break;
                }
            }

            if (!gymFound) {
                throw new GymNotFoundException("Gym with ID " + gymId + " not found.");
            }
            adminDAO.rejectGym(gymId);
            System.out.println("Gym ID " + gymId + " rejected successfully!\n");
        } catch (SQLException e) {
            throw new DatabaseException("Database error while rejecting gym: " + e.getMessage());
        } catch (GymNotFoundException e) {
            throw e;
        }
    }

    public void viewGymStatus() throws GymStatusNotFoundException, DatabaseException {
        try {
            List<FlipFitGymCentre> gyms = adminDAO.viewGymStatus();

            if (gyms.isEmpty()) {
                throw new GymStatusNotFoundException("No gyms found or gym statuses are not available.");
            }

            System.out.println("\nGym Status:");
            System.out.println("-------------------------------------");
            System.out.println("| Gym ID | Gym Name  | Status      |");
            System.out.println("-------------------------------------");
            for (FlipFitGymCentre gym : gyms) {
                System.out.printf("|   %d    | %s   | %s    |%n", gym.getGymId(), gym.getGymName(), gym.getStatus());
            }
            System.out.println("-------------------------------------\n");
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving gym status from the database: " + e.getMessage());
        } catch (GymStatusNotFoundException e) {
            throw e;
        }
    }

    public void approveGymOwner() throws GymOwnerNotFoundException, DatabaseException {
        System.out.print("Enter Gym Owner ID to approve: ");
        int gymOwnerId = scanner.nextInt();
        scanner.nextLine();
        try {
            Map<FlipFitGymOwner, String> ownerMap = adminDAO.viewGymOwnerStatus();
            boolean gymOwnerFound = false;
    
            for (FlipFitGymOwner owner : ownerMap.keySet()) {
                if (owner.getId() == gymOwnerId) {
                    gymOwnerFound = true;
                    break;
                }
            }
    
            if (!gymOwnerFound) {
                throw new GymOwnerNotFoundException("Gym Owner ID " + gymOwnerId + " not found.");
            }
    
            adminDAO.approveGymOwner(gymOwnerId);
            System.out.println("Gym Owner ID " + gymOwnerId + " approved successfully!\n");
        } catch (SQLException e) {
            throw new DatabaseException("Error approving gym owner: " + e.getMessage());
        } catch (GymOwnerNotFoundException e) {
            throw e;
        }
    }
    

    public void rejectGymOwner() throws GymOwnerNotFoundException, DatabaseException {
        System.out.print("Enter Gym Owner ID to reject: ");
        int gymOwnerId = scanner.nextInt();
        scanner.nextLine();
        try {
            Map<FlipFitGymOwner, String> ownerMap = adminDAO.viewGymOwnerStatus();
            boolean ownerFound = false;
    
            for (FlipFitGymOwner owner : ownerMap.keySet()) {
                if (owner.getId() == gymOwnerId) {
                    ownerFound = true;
                    break;
                }
            }
    
            if (!ownerFound) {
                throw new GymOwnerNotFoundException("Gym Owner with ID " + gymOwnerId + " not found.");
            }
    
            adminDAO.rejectGymOwner(gymOwnerId);
            System.out.println("Gym Owner ID " + gymOwnerId + " rejected successfully!\n");
        } catch (SQLException e) {
            throw new DatabaseException("Error rejecting gym owner: " + e.getMessage());
        } catch (GymOwnerNotFoundException e) {
            throw e;
        }
    }
    

    public void viewGymOwnerStatus() throws GymOwnerNotFoundException, DatabaseException {
        try {
            Map<FlipFitGymOwner, String> ownerMap = adminDAO.viewGymOwnerStatus();

            if (ownerMap.isEmpty()) {
                throw new GymOwnerNotFoundException("No gym owners found or statuses are not available.");
            }

            System.out.println("\nGym Owner Status:");
            System.out.println("---------------------------------------------");
            System.out.println("| Owner ID | Owner Name      | Status       |");
            System.out.println("---------------------------------------------");
            
            for (Map.Entry<FlipFitGymOwner, String> entry : ownerMap.entrySet()) {
                FlipFitGymOwner owner = entry.getKey();
                String ownerName = entry.getValue();
                System.out.printf("|   %d    | %-15s | %-12s |%n", owner.getId(), ownerName, owner.getStatus());
            }
            
            System.out.println("---------------------------------------------\n");
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving gym owner status from the database: " + e.getMessage());
        } catch (GymOwnerNotFoundException e) {
            throw e;
        }
    }

}
