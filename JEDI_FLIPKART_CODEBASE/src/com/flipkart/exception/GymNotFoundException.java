package com.flipkart.exception;

/**
 * This exception is thrown when the specified gym is not found in the system.
 * It extends the {@link Exception} class to provide detailed information about the failure.
 */
public class GymNotFoundException extends Exception {

    /**
     * Constructs a new {@code GymNotFoundException} with the specified detail message.
     * 
     * @param message the detail message describing the reason for the exception
     */
    public GymNotFoundException(String message) {
        super(message);
    }
}
