package com.flipkart.service;

import com.flipkart.bean.Payment;

public class PaymentsBusiness {

    public boolean processPayment(Payment payment) {
        System.out.println("Processing payment for Booking ID: " + payment.getBookingId());
        return true;
    }

    public boolean checkPaymentStatus(String transactionId) {
        System.out.println("Checking payment status for Transaction ID: " + transactionId);
        return true;
    }

    public boolean refundPayment(String transactionId) {
        System.out.println("Refunding payment for Transaction ID: " + transactionId);
        return true;
    }
}
