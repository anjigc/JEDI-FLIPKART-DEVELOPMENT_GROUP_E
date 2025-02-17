package com.flipkart.exception;

/**
 * This exception is thrown when invalid data is provided for a customer.
 * It extends the {@link Exception} class to provide detailed information about the error.
 */
public class InvalidCustomerDataException extends Exception {

    /**
     * Constructs a new {@code InvalidCustomerDataException} with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidCustomerDataException(String message) {
        super(message);
    }
}
