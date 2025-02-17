package com.flipkart.exception;

/**
 * This exception is thrown when there is a database-related error.
 * It extends the {@link Exception} class to provide specific details about the database issue.
 */
public class DatabaseException extends Exception {

    /**
     * Constructs a new {@code DatabaseException} with the specified detail message.
     * 
     * @param message the detail message explaining the cause of the database error
     */
    public DatabaseException(String message) {
        super(message);
    }
}
