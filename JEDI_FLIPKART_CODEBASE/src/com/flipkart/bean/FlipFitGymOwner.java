package com.flipkart.bean;

/**
 * Represents a gym owner in the FlipFit system.
 */
public class FlipFitGymOwner {
    /**
     * Unique identifier for the gym owner.
     */
    private int id;

    /**
     * Address of the gym owner.
     */
    private String address;

    /**
     * PAN (Permanent Account Number) of the gym owner.
     */
    private String panNo;

    /**
     * Aadhaar number of the gym owner.
     */
    private String Aadhaar;

    /**
     * Current status of the gym owner (e.g., Active, Inactive).
     */
    private String status;

    /**
     * Retrieves the gym owner ID.
     *
     * @return the gym owner ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the gym owner ID.
     *
     * @param id the gym owner ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the address of the gym owner.
     *
     * @return the gym owner's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the gym owner.
     *
     * @param address the gym owner's address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the PAN number of the gym owner.
     *
     * @return the PAN number
     */
    public String getPanNo() {
        return panNo;
    }

    /**
     * Sets the PAN number of the gym owner.
     *
     * @param panNo the PAN number to set
     */
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    /**
     * Retrieves the Aadhaar number of the gym owner.
     *
     * @return the Aadhaar number
     */
    public String getAadhaar() {
        return Aadhaar;
    }

    /**
     * Sets the Aadhaar number of the gym owner.
     *
     * @param aadhaar the Aadhaar number to set
     */
    public void setAadhaar(String aadhaar) {
        Aadhaar = aadhaar;
    }

    /**
     * Retrieves the current status of the gym owner.
     *
     * @return the status (e.g., Active, Inactive)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the gym owner.
     *
     * @param status the status to set (e.g., Active, Inactive)
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
