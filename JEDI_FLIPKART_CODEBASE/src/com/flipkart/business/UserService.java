package com.flipkart.business;

import com.flipkart.bean.User;

public class UserService {
    private String email1 = "customer@gmail.com"
    private String email2 = "gymowner@gmail.com"
    private String email3 = "admin@gmail.com"

    public User loginUser(String email, String password) {
        System.out.println("User with email " + email + " logged in successfully!");

        switch(email) {
            case email1:
                return "Flipfit Customer"
            case email2:
                return "Flipfit Gym Owner"
            case email3:
                return "Flipfit Admin"
        }
        return null;
    }

    public void logoutUser(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }
}