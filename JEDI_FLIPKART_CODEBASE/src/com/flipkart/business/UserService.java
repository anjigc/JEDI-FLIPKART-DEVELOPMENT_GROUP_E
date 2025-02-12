package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.bean.*;
import java.util.List;
import java.util.ArrayList;

public class UserService {

    public User registerUser(String name, String contact, String email, String password, String role) {
        User user = new User();
        user.setName(name);
        user.setContact(contact);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    public User loginUser(String email, String password) {
        System.out.println("User with email " + email + " logged in successfully!");
        return null;

    }

    public void logoutUser(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }
}