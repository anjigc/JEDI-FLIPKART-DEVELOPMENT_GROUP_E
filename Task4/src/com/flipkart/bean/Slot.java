package main.java.com.flipkart.bean;

import java.util.Date;
import java.util.List;

public class Slot {
    private int id;
    private int gymId;
    private Date date;
    private String time;
    private int capacity;
    private boolean booked;
    private List<Integer> waitList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public List<Integer> getWaitList() {
        return waitList;
    }

    public void setWaitList(List<Integer> waitList) {
        this.waitList = waitList;
    }
}