package com.flipkart.dao;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.constant.FlipFitConstants;
import com.flipkart.utils.FlipFitDBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitUserDAO implements FlipFitUserDAOInterface {
    private Connection conn;

    public FlipFitUserDAO() {
        this.conn = FlipFitDBConnection.getConnection();
    }

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

    public void deleteUser(int id) {
        try (PreparedStatement stmt = conn.prepareStatement(FlipFitConstants.FLIPFIT_SQL_USER_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
