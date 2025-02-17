package com.flipkart.bean;

import java.util.Date;

/**
 * Represents a booking for a FlipFit session.
 */
public class FlipFitBooking {
    /**
     * Unique identifier for the booking.
     */
    private int bookingId;

    /**
     * Identifier for the booked slot.
     */
    private int slotId;

    /**
     * Identifier for the customer who made the booking.
     */
    private int customerId;

    /**
     * Indicates whether the booking is confirmed.
     */
    private boolean isConfirmed;

    /**
     * Date of the booking.
     */
    private Date bookingDate;

    /**
     * Retrieves the booking ID.
     *
     * @return the booking ID
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID.
     *
     * @param bookingId the booking ID to set
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Retrieves the slot ID for the booking.
     *
     * @return the slot ID
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the slot ID for the booking.
     *
     * @param slotId the slot ID to set
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Retrieves the customer ID associated with the booking.
     *
     * @return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID associated with the booking.
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Checks if the booking is confirmed.
     *
     * @return {@code true} if the booking is confirmed, otherwise {@code false}
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Sets the booking confirmation status.
     *
     * @param isConfirmed {@code true} if the booking is confirmed, otherwise {@code false}
     */
    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    /**
     * Retrieves the booking date.
     *
     * @return the booking date
     */
    public Date getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets the booking date.
     *
     * @param bookingDate the booking date to set
     */
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
