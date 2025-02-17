package com.flipkart.dao;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.constant.FlipFitConstants;
import com.flipkart.utils.FlipFitDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code FlipFitUserDAO} class is responsible for handling database operations related to users.
 * This includes adding, retrieving, updating, and deleting users from the database. It interacts with
 * the database using SQL queries defined in the {@link FlipFitConstants} class.
 * <p>
 * This class supports CRUD operations for managing user data within the FlipFit system.
 * </p>
 */
public class FlipFitUserDAO implements FlipFitUserDAOInterface {
    private Connection conn;

    /**
     * Constructor that initializes the database connection.
     * Uses {@link FlipFitDBConnection#getConnection()} to establish the connection to the database.
     */
    public FlipFitUserDAO() {
        this.conn = FlipFitDBConnection.getConnection();
    }

    /**
     * Adds a new user to the database.
     *
     * @param user The {@link FlipFitUser} object containing the user's details to be added.
     */
    public void addUser(FlipFitUser user) {
        try (PreparedStatement stmt = conn.prepareStatement(FlipFitConstants.FLIPFIT_SQL_USER_ADD)) {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getContact());
            stmt.setInt(6, user.getRoleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a user by their ID from the database.
     *
     * @param id The ID of the user to be retrieved.
     * @return The {@link FlipFitUser} object representing the user, or {@code null} if no user is found.
     */
    public FlipFitUser getUserById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement(FlipFitConstants.FLIPFIT_SQL_USER_GET_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new FlipFitUser(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getInt("roleId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of {@link FlipFitUser} objects representing all users in the database.
     */
    public List<FlipFitUser> getAllUsers() {
        List<FlipFitUser> users = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(FlipFitConstants.FLIPFIT_SQL_USER_GET_ALL)) {
            while (rs.next()) {
                users.add(new FlipFitUser(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getInt("roleId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Updates an existing user's information in the database.
     *
     * @param user The {@link FlipFitUser} object containing the updated user's details.
     */
    public void updateUser(FlipFitUser user) {
        try (PreparedStatement stmt = conn.prepareStatement(FlipFitConstants.FLIPFIT_SQL_USER_UPDATE)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getContact());
            stmt.setInt(5, user.getRoleId());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteUser(int id) {
        try (PreparedStatement stmt = conn.prepareStatement(FlipFitConstants.FLIPFIT_SQL_USER_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the database connection.
     * This method should be called after performing database operations to ensure that resources are released.
     */
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
