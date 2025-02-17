package com.flipkart.dao;

import com.flipkart.bean.FlipFitUser;
import java.util.List;

/**
 * Interface for FlipFit User DAO (Data Access Object).
 * This interface defines methods for user-related operations, 
 * including adding, retrieving, updating, and deleting users.
 */
public interface FlipFitUserDAOInterface {

    /**
     * Adds a new user to the system.
     * @param user The FlipFitUser object containing user details.
     */
    public void addUser(FlipFitUser user);

    /**
     * Retrieves a user based on their unique ID.
     * @param id The unique identifier of the user.
     * @return A FlipFitUser object representing the user, or null if not found.
     */
    public FlipFitUser getUserById(int id);

    /**
     * Retrieves a list of all users in the system.
     * @return A list of FlipFitUser objects representing all users.
     */
    public List<FlipFitUser> getAllUsers();

    /**
     * Updates the details of an existing user.
     * @param user The FlipFitUser object containing updated user details.
     */
    public void updateUser(FlipFitUser user);

    /**
     * Deletes a user from the system based on their unique ID.
     * @param id The unique identifier of the user to be deleted.
     */
    public void deleteUser(int id);
}
