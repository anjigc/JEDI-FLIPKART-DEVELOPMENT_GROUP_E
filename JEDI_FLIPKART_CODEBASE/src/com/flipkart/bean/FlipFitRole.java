package com.flipkart.bean;

/**
 * Represents a role within the FlipFit application.
 * This class contains details about the role such as its ID, name, and description.
 */
public class FlipFitRole {
    
    /** The unique identifier for the role */
    private int id;
    
    /** The name of the role */
    private String role;
    
    /** The description of the role */
    private String description;

    /**
     * Gets the unique identifier of the role.
     * 
     * @return The ID of the role.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the role.
     * 
     * @param id The unique ID to set for the role.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     * 
     * @return The name of the role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the name for the role.
     * 
     * @param role The name to set for the role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the description of the role.
     * 
     * @return The description of the role.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the role.
     * 
     * @param description The description to set for the role.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
