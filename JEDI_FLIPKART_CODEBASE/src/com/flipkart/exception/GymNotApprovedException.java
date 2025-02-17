package com.flipkart.exception;

/**
 * This exception is thrown when an attempt is made to access or perform operations on a gym
 * that has not been approved yet.
 * It extends the {@link Exception} class to provide detailed information about the failure.
 */
public class GymNotApprovedException extends Exception {

    /**
     * Constructs a new {@code GymNotApprovedException} with the specified detail message.
     * 
     * @param message the detail message describing the reason for the exception
     */
    public GymNotApprovedException(String message) {
        super(message);
    }
}
