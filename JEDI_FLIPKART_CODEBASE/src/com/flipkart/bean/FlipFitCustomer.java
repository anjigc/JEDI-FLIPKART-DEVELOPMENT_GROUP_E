package com.flipkart.bean;

/**
 * Represents a customer in the FlipFit system.
 */
public class FlipFitCustomer {
    /**
     * Unique identifier for the customer.
     */
    private int id;

    /**
     * Age of the customer.
     */
    private int age;

    /**
     * Address of the customer.
     */
    private String address;

    /**
     * Default constructor.
     */
    public FlipFitCustomer() {
    }

    /**
     * Parameterized constructor to initialize customer details.
     *
     * @param id      the unique identifier of the customer
     * @param age     the age of the customer
     * @param address the address of the customer
     */
    public FlipFitCustomer(int id, int age, String address) {
        this.id = id;
        this.age = age;
        this.address = address;
    }

    /**
     * Retrieves the customer ID.
     *
     * @return the customer ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the customer ID.
     *
     * @param id the customer ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the age of the customer.
     *
     * @return the customer's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the customer.
     *
     * @param age the customer's age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieves the address of the customer.
     *
     * @return the customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address the customer's address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
