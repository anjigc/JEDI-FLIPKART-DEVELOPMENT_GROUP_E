package com.flipkart.business;

import com.flipkart.bean.Customer;

public interface FlipFitCustomerInterface {
    public Customer registerCustomer(String name, String email, String password, String contact, int age, String address);
    public void viewGymList();
    public void selectGym();
    public void viewAvailableSlots(String gymName);
    public void bookGymSlot();
    public boolean payAmount();
    public void viewMyBookings();
}
