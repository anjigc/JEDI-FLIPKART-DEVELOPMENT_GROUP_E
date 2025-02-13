package com.flipkart.business;

public interface FlipFitUserServiceInterface {
    public String loginUser(String email, String password);
    public void logoutUser(String email);
}
