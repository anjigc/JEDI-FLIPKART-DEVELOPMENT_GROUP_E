package com.flipkart.dao;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.utils.FlipFitDBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitUserDAO implements FlipFitUserDAOInterface {
    private Connection conn;

    // Constructor to initialize connection
    public FlipFitUserDAO() {
        this.conn = FlipFitDBConnection.getConnection();
    }

    public void addUser(FlipFitUser user) {
        String sql = "INSERT INTO FlipFitUser (id, email, password, name, contact, roleId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public FlipFitUser getUserById(int id) {
        String sql = "SELECT * FROM FlipFitUser WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public List<FlipFitUser> getAllUsers() {
        List<FlipFitUser> users = new ArrayList<>();
        String sql = "SELECT * FROM FlipFitUser";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
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

    public void updateUser(FlipFitUser user) {
        String sql = "UPDATE FlipFitUser SET email = ?, password = ?, name = ?, contact = ?, roleId = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public void deleteUser(int id) {
        String sql = "DELETE FROM FlipFitUser WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close connection when done (optional if connection pooling is used)
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
