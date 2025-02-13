package com.flipkart.business;

import java.util.HashMap;

import com.flipkart.bean.FlipFitUser;

public class FlipFitUserService implements FlipFitUserInterface {

    private HashMap<Integer, FlipFitUser> UserList;

    public FlipFitUserService(HashMap<Integer, FlipFitUser> UserList) {
        this.UserList = UserList;
    }

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