package com.flipkart.bean;


public class Notification {
    private String bookingId;
    private String message;

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}