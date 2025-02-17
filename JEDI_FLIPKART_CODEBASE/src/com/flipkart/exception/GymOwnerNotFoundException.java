package com.flipkart.exception;

/**
 * This exception is thrown when a gym owner is not found in the system.
 * It extends the {@link Exception} class to provide detailed information about the failure.
 */
public class GymOwnerNotFoundException extends Exception {

    /**
     * Constructs a new {@code GymOwnerNotFoundException} with the specified detail message.
     * 
     * @param message the detail message describing the reason for the exception
     */
    public GymOwnerNotFoundException(String message) {
        super(message);
    }
}
