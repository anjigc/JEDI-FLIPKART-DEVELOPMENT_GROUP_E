package com.flipkart.business;

public class FlipFitUserService {

    public String loginUser(String email, String password) {
        System.out.println("User with email " + email + " logged in successfully!");

        switch(email) {
            case "customer@gmail.com":
                return "Flipfit Customer";
            case "gymowner@gmail.com":
                return "Flipfit Gym Owner";
            case "admin@gmail.com":
                return "Flipfit Admin";
        }
        return null;
    }

    public void logoutUser(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }
}