package com.flipkart.exception;

/**
 * This exception is thrown when a gym owner already exists in the system.
 * It extends the {@link Exception} class to provide detailed information about the failure.
 */
public class GymOwnerAlreadyExistsException extends Exception {

    /**
     * Constructs a new {@code GymOwnerAlreadyExistsException} with the specified detail message.
     * 
     * @param message the detail message describing the reason for the exception
     */
    public GymOwnerAlreadyExistsException(String message) {
        super(message);
    }
}
