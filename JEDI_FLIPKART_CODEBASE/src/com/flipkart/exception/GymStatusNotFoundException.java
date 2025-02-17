package com.flipkart.exception;

/**
 * This exception is thrown when a gym status is not found.
 * It extends the {@link Exception} class to provide detailed information about the error.
 */
public class GymStatusNotFoundException extends Exception {

    /**
     * Constructs a new {@code GymStatusNotFoundException} with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public GymStatusNotFoundException(String message) {
        super(message);
    }
}
