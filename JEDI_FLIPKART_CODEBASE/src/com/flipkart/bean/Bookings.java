package com.flipkart.bean;

import java.util.Date;

public class Bookings {
    private int bookingId;
    private int gymId;
    private int slotId;
    private int customerId;
    private boolean isConfirmed;
    private java.util.Date bookingDate;

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getGymId() {
        return gymId;
    }
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public int getSlotId() {
        return slotId;
    }
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }
    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public java.util.Date getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(java.util.Date bookingDate) {
        this.bookingDate = bookingDate;
    }