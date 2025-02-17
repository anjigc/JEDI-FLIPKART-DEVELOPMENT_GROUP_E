package com.flipkart.bean;

/**
 * Represents a time slot available at a gym.
 * This class contains details about the slot such as the slot ID, start and end times, available seats, and the associated gym ID.
 */
public class FlipFitSlot {
    
    /** The unique identifier for the slot */
    private int slotId;
    
    /** The start time of the slot */
    private String startTime;
    
    /** The end time of the slot */
    private String endTime;
    
    /** The number of available seats in the slot */
    private int availableSeats;
    
    /** The ID of the gym associated with this slot */
    private int gymId;

    /**
     * Gets the unique identifier of the slot.
     * 
     * @return The ID of the slot.
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the unique identifier for the slot.
     * 
     * @param slotId The unique ID to set for the slot.
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Gets the start time of the slot.
     * 
     * @return The start time of the slot.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time for the slot.
     * 
     * @param startTime The start time to set.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time of the slot.
     * 
     * @return The end time of the slot.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time for the slot.
     * 
     * @param endTime The end time to set.
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the number of available seats for the slot.
     * 
     * @return The number of available seats in the slot.
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Sets the number of available seats for the slot.
     * 
     * @param availableSeats The number of available seats to set.
     */
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    /**
     * Gets the ID of the gym associated with the slot.
     * 
     * @return The gym ID for the slot.
     */
    public int getGymId() {
        return gymId;
    }

    /**
     * Sets the gym ID for the slot.
     * 
     * @param gymId The gym ID to set.
     */
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }
}
