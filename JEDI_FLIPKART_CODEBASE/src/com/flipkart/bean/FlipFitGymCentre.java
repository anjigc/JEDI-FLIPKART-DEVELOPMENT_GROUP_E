package com.flipkart.bean;

public class FlipFitGymCentre {
    private int gymId;
    private String gymName;
    private String gymAddress;
    private int ownerId;
    private int capacity;
    private String status;
    
	public int getGymId() {
		return gymId;
	}
	public void setGymId(int gymId) {
		this.gymId = gymId;
	}
	public String getGymName() {
		return gymName;
	}
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	public String getGymAddress() {
		return gymAddress;
	}
	public void setGymAddress(String gymAddress) {
		this.gymAddress = gymAddress;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}