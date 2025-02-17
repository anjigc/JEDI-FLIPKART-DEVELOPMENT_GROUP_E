package com.flipkart.exception;

/**
 * This exception is thrown when no bookings are found for a customer.
 * It extends the {@link Exception} class to provide detailed information about the error.
 */
public class NoBookingsFoundException extends Exception {

    /**
     * Constructs a new {@code NoBookingsFoundException} with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public NoBookingsFoundException(String message) {
        super(message);
    }
}
