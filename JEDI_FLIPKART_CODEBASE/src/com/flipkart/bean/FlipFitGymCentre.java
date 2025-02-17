package com.flipkart.bean;

/**
 * Represents a gym center in the FlipFit system.
 */
public class FlipFitGymCentre {
    /**
     * Unique identifier for the gym.
     */
    private int gymId;

    /**
     * Name of the gym.
     */
    private String gymName;

    /**
     * Address of the gym.
     */
    private String gymAddress;

    /**
     * Unique identifier of the gym owner.
     */
    private int ownerId;

    /**
     * Maximum capacity of the gym.
     */
    private int capacity;

    /**
     * Current status of the gym (e.g., Open, Closed).
     */
    private String status;


    /**
     * Slot price of the gym.
     */
    private int  slotPrice;

    /**
     * Retrieves the gym ID.
     *
     * @return the gym ID
     */
    public int getGymId() {
        return gymId;
    }

    /**
     * Sets the gym ID.
     *
     * @param gymId the gym ID to set
     */
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    /**
     * Retrieves the name of the gym.
     *
     * @return the gym name
     */
    public String getGymName() {
        return gymName;
    }

    /**
     * Sets the name of the gym.
     *
     * @param gymName the gym name to set
     */
    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    /**
     * Retrieves the address of the gym.
     *
     * @return the gym address
     */
    public String getGymAddress() {
        return gymAddress;
    }

    /**
     * Sets the address of the gym.
     *
     * @param gymAddress the gym address to set
     */
    public void setGymAddress(String gymAddress) {
        this.gymAddress = gymAddress;
    }

    /**
     * Retrieves the owner ID of the gym.
     *
     * @return the owner ID
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the owner ID of the gym.
     *
     * @param ownerId the owner ID to set
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Retrieves the maximum capacity of the gym.
     *
     * @return the gym capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the maximum capacity of the gym.
     *
     * @param capacity the gym capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Retrieves the current status of the gym.
     *
     * @return the gym status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the gym.
     *
     * @param status the gym status to set (e.g., Open, Closed)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the slot price of the gym.
     *
     * @return the slot price
     */
    public int getSlotPrice() {
        return slotPrice;
    }

    /**
     * Sets the slot price of the gym.
     *
     * @param slotPrice the slot price to set
     */
    public void setSlotPrice(int slotPrice) {
        this.slotPrice = slotPrice;
    }
}
