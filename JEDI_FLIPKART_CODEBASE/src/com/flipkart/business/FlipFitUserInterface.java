package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;

public interface FlipFitUserInterface {
    public FlipFitUser registerUser(int id, String email, String password, String name, String contact, int roleId);
    public int loginUser(String email, String password);
    public void logoutUser(String email);
}
