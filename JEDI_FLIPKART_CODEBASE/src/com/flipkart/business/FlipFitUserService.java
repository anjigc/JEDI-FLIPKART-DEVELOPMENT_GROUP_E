package com.flipkart.business;

import java.util.HashMap;

import com.flipkart.bean.FlipFitUser;

public class FlipFitUserService implements FlipFitUserInterface {

    private HashMap<Integer, FlipFitUser> Users;

    public FlipFitUserService(HashMap<Integer, FlipFitUser> Users) {
        this.Users = Users;
    }
    
    public FlipFitUser registerUser(int id, String email, String password, String name, String contact, int roleId) {
        FlipFitUser user = new FlipFitUser();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setContact(contact);
        user.setRoleId(roleId);
        Users.put(id, user);

        Users.put(id, user);
        System.out.println("User with email " + email + " registered successfully!");

        return user;
    }

    public FlipFitUser loginUser(String email, String password) {
        // Iterate through Users map to find the user with the given email
        for (FlipFitUser user : Users.values()) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    System.out.println("User with email " + email + " logged in successfully!");
                    return user;  // Return the user object upon successful login
                } else {
                    System.out.println("Incorrect password for email " + email + "!");
                    return null;  // Incorrect password, return null
                }
            }
        }
        System.out.println("No user found with email " + email + "!");
        return null;  // User not found, return null
    }

    public void logoutUser(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }
}