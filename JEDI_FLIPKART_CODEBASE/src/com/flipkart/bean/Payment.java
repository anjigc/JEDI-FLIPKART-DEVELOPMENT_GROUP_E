package com.flipkart.bean;

import com.flipkart.bean.Slot;

public class Payment {
    private String transactionId;
    private int bookingId;
    private boolean isSuccessful;

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}