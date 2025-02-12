package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.bean.*;
import java.util.List;
import java.util.ArrayList;

public class UserBusiness {

    public User registerUser(String name, String mobileNo, String email, String type) {
        User user = new User();
        user.setName(name);
        user.setContact(mobileNo);
        user.setEmail(email);
        user.setRole(type);
        return user;
    }

    public User registerUser(User user) {

        System.out.println("User " + user.getName() + " registered successfully!");
        return null;

    }

    public User loginUser(String email, String password) {
        System.out.println("User with email " + email + " logged in successfully!");
        return null;

    }

    public void logoutUser(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }
}



