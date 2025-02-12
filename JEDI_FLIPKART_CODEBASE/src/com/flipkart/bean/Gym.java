package com.flipkart.bean;

import com.flipkart.bean.Slot;

import java.util.List;

public class Gym {
    private String gymId;
    private String gymName;
    private String gymAddress;
    private int ownerId;
    private List<Slot> slots;

    // Getters and Setters
    public String getGymId() {
        return gymId;
    }
    public void setGymId(String gymId) {
        this.gymId = gymId;
    }

    public String getGymName() {
        return gymName;
    }
    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymAddress() {
        return gymAddress;
    }
    public void setGymAddress(String gymAddress) {
        this.gymAddress = gymAddress;
    }

    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Slot> getSlots() {
        return slots;
    }
    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}