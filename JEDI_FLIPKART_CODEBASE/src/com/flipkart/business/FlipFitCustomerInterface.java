package com.flipkart.business;

import com.flipkart.bean.FlipFitCustomer;

public interface FlipFitCustomerInterface {
    public FlipFitCustomer registerCustomer(int id, int age, String address);
    public void viewGymList();
    public void selectGym();
    public void viewAvailableSlots(int gymId);
    public void bookGymSlot(int customerId);
    public String processPayment(int customerId, int slotId);
    public void viewMyBookings(int customerId);
}
