package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.bean.*;
import java.util.List;
import java.util.ArrayList;

public class UserBusiness {

    public User registerUser(String name, String mobileNo, String email, String type) {
        User user = new User();
        user.setName(name);
        user.setMobileNo(mobileNo);
        user.setEmail(email);
        user.setType(type);
        return user;
    }

    public User loginUser(String email, String password) {
        return null;
    }

    public void logoutUser(User user) {
    }
}



