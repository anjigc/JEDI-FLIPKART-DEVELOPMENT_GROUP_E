package com.flipkart.business;

import com.flipkart.bean.User;

public class UserService {
    public User loginUser(String email, String password) {
        System.out.println("User with email " + email + " logged in successfully!");
        return null;

    }

    public void logoutUser(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }
}