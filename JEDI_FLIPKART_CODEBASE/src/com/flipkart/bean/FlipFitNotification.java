package com.flipkart.bean;

/**
 * Represents a notification associated with a booking in the FlipFit application.
 * This class contains the booking ID and the message for the notification.
 */
public class FlipFitNotification {
    
    /** The ID of the booking associated with the notification */
    private String bookingId;
    
    /** The message content of the notification */
    private String message;

    /**
     * Gets the booking ID associated with the notification.
     * 
     * @return The booking ID.
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID for the notification.
     * 
     * @param bookingId The booking ID to set.
     */
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Gets the message content of the notification.
     * 
     * @return The message of the notification.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message for the notification.
     * 
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
