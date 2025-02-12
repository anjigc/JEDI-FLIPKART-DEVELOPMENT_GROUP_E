package com.flipkart.bean;

import com.flipkart.bean.Slot;

public class Payment {
    private String transactionId;
    private int bookingId;
    private String status;

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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}