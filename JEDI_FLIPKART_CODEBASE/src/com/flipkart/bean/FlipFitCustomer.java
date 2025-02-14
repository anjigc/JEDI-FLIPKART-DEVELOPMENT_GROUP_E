package com.flipkart.bean;


 public class FlipFitCustomer {
	private int id;
    private int age;
    private String address;

	public FlipFitCustomer(){

	}

	public FlipFitCustomer(int id, int age, String address){
		this.id = id;
		this.age = age;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
    
	

}