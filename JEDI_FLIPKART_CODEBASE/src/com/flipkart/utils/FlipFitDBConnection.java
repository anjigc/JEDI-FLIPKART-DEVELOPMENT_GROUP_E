package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FlipFitDBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/FlipFitDB";
    private static final String USER = "root";
    private static final String PASSWORD = "FK_232532_gulu";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed!");
        }
    }
}


