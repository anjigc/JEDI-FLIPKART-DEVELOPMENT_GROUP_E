package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;

/**
 * Interface for FlipFit User Business Logic.
 * This interface defines various methods related to user operations
 * such as registering, logging in, logging out, and changing passwords.
 */
public interface FlipFitUserInterface {

    /**
     * Registers a new user.
     * @param id The unique identifier of the user.
     * @param email The email address of the user.
     * @param password The password for the user.
     * @param name The name of the user.
     * @param contact The contact number of the user.
     * @param roleId The role ID associated with the user (e.g., customer, gym owner, admin).
     * @return The registered FlipFitUser object.
     */
    public FlipFitUser registerUser(int id, String email, String password, String name, String contact, int roleId);

    /**
     * Logs a user into the system.
     * @param email The email address of the user.
     * @param password The password for the user.
     * @return The logged-in FlipFitUser object.
     */
    public FlipFitUser loginUser(String email, String password);

    /**
     * Logs the user out of the system.
     * @param email The email address of the user logging out.
     */
    public void logoutUser(String email);

    /**
     * Changes the user's password.
     * @param email The email address of the user.
     * @param password The current password of the user.
     * @param newPassword The new password for the user.
     * @return true if the password change was successful, false otherwise.
     */
    public boolean changePassword(String email, String password, String newPassword);
}
