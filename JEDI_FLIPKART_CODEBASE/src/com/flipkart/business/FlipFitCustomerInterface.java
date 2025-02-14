package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitSlot;

public interface FlipFitCustomerInterface {
    public void registerCustomer(int id, int age, String address);
    public void viewGymList();
    public void selectGym();
    public void viewAvailableSlots(List<FlipFitSlot> slots);
    public void bookGymSlot(int customerId);
    public void viewMyBookings(int customerId);
}
