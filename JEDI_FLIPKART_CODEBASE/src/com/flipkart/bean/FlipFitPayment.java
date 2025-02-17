package com.flipkart.bean;

/**
 * Represents a payment transaction in the FlipFit application.
 * This class contains the transaction ID, associated booking ID, and the payment status.
 */
public class FlipFitPayment {
    
    /** The unique identifier for the payment transaction */
    private String transactionId;
    
    /** The ID of the booking associated with the payment */
    private int bookingId;
    
    /** The current status of the payment */
    private String status;

    /**
     * Gets the unique identifier of the payment transaction.
     * 
     * @return The transaction ID.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the unique identifier for the payment transaction.
     * 
     * @param transactionId The transaction ID to set.
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the booking ID associated with the payment.
     * 
     * @return The booking ID.
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID for the payment.
     * 
     * @param bookingId The booking ID to set.
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Gets the status of the payment.
     * 
     * @return The status of the payment.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status for the payment.
     * 
     * @param status The status to set for the payment.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
