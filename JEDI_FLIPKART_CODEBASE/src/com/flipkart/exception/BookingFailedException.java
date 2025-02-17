package com.flipkart.exception;

/**
 * This exception is thrown when a booking attempt fails.
 * It extends the {@link Exception} class to provide detailed information about the failure.
 */
public class BookingFailedException extends Exception {

    /**
     * Constructs a new {@code BookingFailedException} with the specified detail message.
     * 
     * @param message the detail message which explains the reason for the failure
     */
    public BookingFailedException(String message) {
        super(message);
    }
}
