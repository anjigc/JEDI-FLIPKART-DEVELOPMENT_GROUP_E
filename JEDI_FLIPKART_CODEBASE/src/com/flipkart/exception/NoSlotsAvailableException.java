package com.flipkart.exception;

/**
 * This exception is thrown when there are no available slots for a specific gym or activity.
 * It extends the {@link Exception} class to provide a detailed error message regarding the unavailability of slots.
 */
public class NoSlotsAvailableException extends Exception {

    /**
     * Constructs a new {@code NoSlotsAvailableException} with the specified detail message.
     *
     * @param message the detail message which explains the reason for the exception
     */
    public NoSlotsAvailableException(String message) {
        super(message);
    }
}
