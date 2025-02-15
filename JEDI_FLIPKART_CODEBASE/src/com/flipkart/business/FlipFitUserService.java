package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.dao.FlipFitUserDAO;

public class FlipFitUserService implements FlipFitUserInterface {

    private FlipFitUserDAO userDAO;

    public FlipFitUserService() {
        this.userDAO = new FlipFitUserDAO();
    }

    public FlipFitUser registerUser(int id, String email, String password, String name, String contact, int roleId) {
        FlipFitUser user = new FlipFitUser();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setContact(contact);
        user.setRoleId(roleId);

        // Store in the database
        userDAO.addUser(user);
        System.out.println("User with email " + email + " registered successfully!");

        return user;
    }

    public FlipFitUser loginUser(String email, String password) {
        for (FlipFitUser user : userDAO.getAllUsers()) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    System.out.println("User with email " + email + " logged in successfully!");
                    return user;
                } else {
                    System.out.println("Incorrect password for email " + email + "!");
                    return null;
                }
            }
        }
        System.out.println("No user found with email " + email + "!");
        return null;
    }

    public void logoutUser(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }
    
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        for (FlipFitUser user : userDAO.getAllUsers()) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(oldPassword)) {
                    user.setPassword(newPassword);
                    userDAO.updateUser(user);
                    System.out.println("Password changed successfully for email " + email + "!");
                    return true;
                } else {
                    System.out.println("Incorrect old password for email " + email + "!");
                    return false;
                }
            }
        }
        System.out.println("No user found with email " + email + "!");
        return false;
    }
}
