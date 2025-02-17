package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to manage the database connection for the FlipFit application.
 * Provides a method to establish a connection to the MySQL database.
 */
public class FlipFitDBConnection {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/FlipFitDB";
    private static final String USER = "root";
    private static final String PASSWORD = " ";

    /**
     * Establishes a connection to the FlipFit database.
     *
     * @return a {@link Connection} object representing the connection to the database
     * @throws RuntimeException if the database connection fails
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed!");
        }
    }
}
