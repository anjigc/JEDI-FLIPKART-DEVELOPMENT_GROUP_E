package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitPayment;
import com.flipkart.bean.FlipFitSlot;


public class FlipFitCustomerService implements FlipFitCustomerInterface {

    private ArrayList<FlipFitCustomer> customerList;
    private HashMap<Integer, FlipFitGymCentre> gymCentres;
    private HashMap<Integer, FlipFitBooking> slotBookings;
    private HashMap<Integer, FlipFitSlot> gymSlots;
    private HashMap<Integer, FlipFitPayment> payments;

    public FlipFitCustomerService(ArrayList<FlipFitCustomer> customerList, HashMap<Integer, FlipFitGymCentre> gymCentres, HashMap<Integer, FlipFitBooking> slotBookings, HashMap<Integer, FlipFitSlot> gymSlots, HashMap<Integer, FlipFitPayment> payments) {
        this.customerList = customerList;
        this.gymCentres = gymCentres;
        this.slotBookings = slotBookings;
        this.gymSlots = gymSlots;
        this.payments = payments;
    }

    public void loginCustomer(String email, String password) {
        System.out.println("User with email " + email + " logged in as Customer successfully!");

    }

    public void logoutCustomer(String email) {
        System.out.println("User with email " + email + " logged out successfully!");
    }

    public FlipFitCustomer registerCustomer(String name, String email, String password, String contact, int age, String address) {
        FlipFitCustomer customer = new FlipFitCustomer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setContact(contact);
        customer.setAge(age);
        customer.setAddress(address);
        customer.setRole("FlipFit Customer");

        System.out.println("User with email " + email + " registered as FLipfit Customer Successfully!");

        return customer;
    }
    
    private Scanner scanner = new Scanner(System.in);

    public void viewGymList() {
        System.out.println("\nAvailable Gyms:");
        System.out.println("-------------------------------------");
        System.out.println("| Gym Name  | Location    |");
        System.out.println("-------------------------------------");
        System.out.println("| FitZone   | Downtown    |");
        System.out.println("| Iron Gym  | Uptown      |");
        System.out.println("| PowerFit  | Suburb      |");
        System.out.println("-------------------------------------\n");
    }
    
    public void selectGym() {
        System.out.println("\n Enter Gym Name:");
        String gymName = scanner.nextLine();
        viewAvailableSlots(gymName);
    }
    
    public void viewAvailableSlots(String gymName) {
        System.out.println("\nAvailable Gym Slots in " + gymName + " :");
        System.out.println("-------------------------------------");
        System.out.println("| Slot No | Slot Time  | Available Seats |");
        System.out.println("-------------------------------------");
        System.out.println("|   1     | 6pm - 7pm  | 5               |");
        System.out.println("|   2     | 7pm - 8pm  | 3               |");
        System.out.println("|   3     | 8pm - 9pm  | 2               |");
        System.out.println("-------------------------------------\n");
    }
    
    public void bookGymSlot() {
        System.out.print("Enter Slot No to book: ");
        int slotNo = scanner.nextInt();
        scanner.nextLine();
        
        if (payAmount()) {
            System.out.println("Slot " + slotNo + " booked successfully!\n");
        } else {
            System.out.println("Payment failed. Booking not completed.\n");
        }
    }
    
    public boolean payAmount() {
        System.out.println("Processing payment...");
        return true; // Hardcoded to always succeed
    }
    
    public void viewMyBookings() {
        System.out.println("\nYour Bookings:");
        System.out.println("-------------------------------------");
        System.out.println("| Slot No | Gym Name  | Slot Time   |");
        System.out.println("-------------------------------------");
        System.out.println("|   1     | FitZone   | 6pm - 7pm   |");
        System.out.println("|   2     | Iron Gym  | 7pm - 8pm   |");
        System.out.println("-------------------------------------\n");
    }
}
