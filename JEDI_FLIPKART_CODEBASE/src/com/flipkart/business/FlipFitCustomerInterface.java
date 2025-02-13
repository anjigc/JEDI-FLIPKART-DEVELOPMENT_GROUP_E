package com.flipkart.business;

import com.flipkart.bean.FlipFitCustomer;

public interface FlipFitCustomerInterface {
    public void loginCustomer(String email, String password);
    public void logoutCustomer(String email);
    public FlipFitCustomer registerCustomer(String name, String email, String password, String contact, int age, String address);
    public void viewGymList();
    public void selectGym();
    public void viewAvailableSlots(String gymName);
    public void bookGymSlot();
    public boolean payAmount();
    public void viewMyBookings();
}
