package com.flipkart.exception;

public class NoBookingsFoundException extends Exception {
    public NoBookingsFoundException(String message) {
        super(message);
    }
}