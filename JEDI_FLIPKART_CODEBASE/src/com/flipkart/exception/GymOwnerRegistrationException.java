package com.flipkart.exception;

/**
 * This exception is thrown when there is an error during the registration of a gym owner.
 * It extends the {@link Exception} class to provide detailed information about the registration failure.
 */
public class GymOwnerRegistrationException extends Exception {

    /**
     * Constructs a new {@code GymOwnerRegistrationException} with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public GymOwnerRegistrationException(String message) {
        super(message);
    }
}
