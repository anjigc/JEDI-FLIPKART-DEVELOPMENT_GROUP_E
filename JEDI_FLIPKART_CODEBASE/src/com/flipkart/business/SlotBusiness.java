package com.flipkart.business

import com.flipkart.bean.Slot

public class SlotBusiness {

    public boolean updateSlot(Slot slot) {
        // Logic to update slot details (e.g., availability)
    }

    public boolean addCustomerToWaitlist(Slot slot, Customer customer) {
        // Logic to add a customer to a slot's waitlist
    }

    public boolean removeCustomerFromWaitlist(Slot slot, Customer customer) {
        // Logic to remove a customer from the waitlist
    }

    public Slot getSlotById(int slotId) {
        // Logic to retrieve a slot by ID
    }
}