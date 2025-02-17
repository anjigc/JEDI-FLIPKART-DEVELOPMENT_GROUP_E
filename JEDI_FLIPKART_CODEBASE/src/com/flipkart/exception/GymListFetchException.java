package com.flipkart.exception;

/**
 * This exception is thrown when there is an error while fetching the list of gyms.
 * It extends the {@link Exception} class to provide detailed information about the failure.
 */
public class GymListFetchException extends Exception {

    /**
     * Constructs a new {@code GymListFetchException} with the specified detail message.
     * 
     * @param message the detail message describing the reason for the exception
     */
    public GymListFetchException(String message) {
        super(message);
    }
}
