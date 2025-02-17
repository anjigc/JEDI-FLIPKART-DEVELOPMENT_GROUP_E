package com.flipkart.exception;

/**
 * This exception is thrown when no gyms are found in the system.
 * It is a custom exception that extends {@link Exception} to provide details on the specific error encountered.
 */
public class NoGymsFoundException extends Exception {

    /**
     * Constructs a new {@code NoGymsFoundException} with the specified detail message.
     *
     * @param message the detail message which explains the reason for the exception
     */
    public NoGymsFoundException(String message) {
        super(message);
    }
}
