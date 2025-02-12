package com.flipkart.bean;

import java.util.Date;
import java.util.List;

public class Slot {
    private String slotId;
    private String startTime;
    private String endTime;
    private boolean isFull;
    private int slotCapacity;
    private int availableSeats;
    private List<Customer> waitlist;
    private int gymId;

    // Getters and Setters
    public String getSlotId() {
        return slotId;
    }
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isFull() {
        return isFull;
    }
    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    public int getSlotCapacity() {
        return slotCapacity;
    }
    public void setSlotCapacity(int slotCapacity) {
        this.slotCapacity = slotCapacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Customer> getWaitlist() {
        return waitlist;
    }
    public void setWaitlist(List<Customer> waitlist) {
        this.waitlist = waitlist;
    }

    public int getGymId() {
        return gymId;
    }
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }
}