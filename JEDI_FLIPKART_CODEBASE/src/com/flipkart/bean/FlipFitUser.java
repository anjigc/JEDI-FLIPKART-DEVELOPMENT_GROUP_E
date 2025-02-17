package com.flipkart.bean;

/**
 * Represents a user in the FlipFit application.
 * This class contains the user's personal details such as ID, email, password, name, contact, and role ID.
 */
public class FlipFitUser {
    
    /** The unique identifier for the user */
    private int id;
    
    /** The email address of the user */
    private String email;
    
    /** The password of the user */
    private String password;
    
    /** The name of the user */
    private String name;
    
    /** The contact number of the user */
    private String contact;
    
    /** The role ID associated with the user */
    private int roleId;

    /**
     * Default constructor.
     * Initializes a new instance of the FlipFitUser class with default values.
     */
    public FlipFitUser() {
    }

    /**
     * Constructor with parameters to initialize the FlipFitUser object.
     * 
     * @param id The unique identifier for the user.
     * @param email The email address of the user.
     * @param password The password of the user.
     * @param name The name of the user.
     * @param contact The contact number of the user.
     * @param roleId The role ID associated with the user.
     */
    public FlipFitUser(int id, String email, String password, String name, String contact, int roleId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.roleId = roleId;
    }

    /**
     * Gets the unique identifier of the user.
     * 
     * @return The ID of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     * 
     * @param id The unique ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the email address of the user.
     * 
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the user.
     * 
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the name of the user.
     * 
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the user.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact number of the user.
     * 
     * @return The contact number of the user.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the contact number for the user.
     * 
     * @param contact The contact number to set.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets the role ID associated with the user.
     * 
     * @return The role ID of the user.
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID for the user.
     * 
     * @param roleId The role ID to set.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
