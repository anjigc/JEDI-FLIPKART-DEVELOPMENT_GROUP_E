package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.dao.FlipFitUserDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service class responsible for handling user-related operations like registration,
 * login, logout, and password change. Interacts with the {@link FlipFitUserDAO} 
 * for database operations.
 */

public class FlipFitUserService implements FlipFitUserInterface {

    private FlipFitUserDAO userDAO;
    /**
     * Constructor to initialize the FlipFitUserService with the associated DAO.
     * Initializes the userDAO object for handling database interactions.
     */
    public FlipFitUserService() {
        this.userDAO = new FlipFitUserDAO();
    }
    /**
     * Registers a new user in the system.
     * 
     * @param id The unique identifier of the user
     * @param email The email address of the user
     * @param password The password for the user
     * @param name The full name of the user
     * @param contact The contact number of the user
     * @param roleId The role ID of the user (e.g., customer, gym owner, etc.)
     * @return The registered {@link FlipFitUser} object
     */
    public FlipFitUser registerUser(int id, String email, String password, String name, String contact, int roleId) {
        FlipFitUser user = new FlipFitUser();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setContact(contact);
        user.setRoleId(roleId);

        try {
            userDAO.addUser(user);
            System.out.println("User with email " + email + " registered successfully!");
        } catch (Exception e) {
            System.out.println("Error while registering user: " + e.getMessage());
        }
        return user;
    }
    /**
     * Logs in an existing user using email and password.
     * 
     * @param email The email address of the user attempting to log in
     * @param password The password of the user
     * @return The logged-in {@link FlipFitUser} object if successful, null otherwise
     */
    public FlipFitUser loginUser(String email, String password) {
        try {
            return userDAO.getAllUsers().stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst()
                    .map(user -> {
                        if (user.getPassword().equals(password)) {
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String formattedDateTime = now.format(formatter);
                            System.out.println("\nLogin:");
                            System.out.println("User " + user.getName() + " with email " + email + " logged in successfully at " + formattedDateTime + "!");
                            return user;
                        } else {
                            System.out.println("Incorrect password for email " + email + "!");
                            return null;
                        }
                    })
                    .orElseGet(() -> {
                        System.out.println("No user found with email " + email + "!");
                        return null;
                    });
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            return null;
        }
    }
    /**
     * Logs out a user from the system.
     * 
     * @param email The email address of the user attempting to log out
     */
    public void logoutUser(String email) {
        try {
            System.out.println("User with email " + email + " logged out successfully!");
        } catch (Exception e) {
            System.out.println("Error while logging out: " + e.getMessage());
        }
    }
    /**
     * Changes the password for a user.
     * 
     * @param email The email address of the user requesting the password change
     * @param oldPassword The current password of the user
     * @param newPassword The new password for the user
     * @return true if the password change was successful, false otherwise
     */
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        try {
            return userDAO.getAllUsers().stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst()
                    .map(user -> {
                        if (user.getPassword().equals(oldPassword)) {
                            user.setPassword(newPassword);
                            try {
                                userDAO.updateUser(user);
                                System.out.println("Password changed successfully for email " + email + "!");
                                return true;
                            } catch (Exception e) {
                                System.out.println("Error while updating password: " + e.getMessage());
                                return false;
                            }
                        } else {
                            System.out.println("Incorrect old password for email " + email + "!");
                            return false;
                        }
                    })
                    .orElseGet(() -> {
                        System.out.println("No user found with email " + email + "!");
                        return false;
                    });
        } catch (Exception e) {
            System.out.println("Error during password change: " + e.getMessage());
            return false;
        }
    }
}
