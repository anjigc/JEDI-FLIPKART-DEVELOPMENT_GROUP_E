package com.flipkart.bean;

import java.util.List;

 public class Customer extends User {
    private int age;
    private String address;
    private List<Booking> bookings;

    // Getters and Setters
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}