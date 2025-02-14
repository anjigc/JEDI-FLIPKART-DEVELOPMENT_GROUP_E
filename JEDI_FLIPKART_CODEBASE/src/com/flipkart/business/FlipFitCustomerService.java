package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitPayment;
import com.flipkart.bean.FlipFitSlot;


public class FlipFitCustomerService implements FlipFitCustomerInterface {

    private HashMap<Integer, FlipFitCustomer> customers;
    private HashMap<Integer, FlipFitGymCentre> gymCentres;
    private HashMap<Integer, FlipFitBooking> slotBookings;
    private HashMap<Integer, FlipFitSlot> gymSlots;
    private HashMap<String, FlipFitPayment> payments;

    public FlipFitCustomerService(HashMap<Integer, FlipFitCustomer> customers, HashMap<Integer, FlipFitGymCentre> gymCentres, HashMap<Integer, FlipFitBooking> slotBookings, HashMap<Integer, FlipFitSlot> gymSlots, HashMap<String, FlipFitPayment> payments) {
        this.customers = customers;
        this.gymCentres = gymCentres;
        this.slotBookings = slotBookings;
        this.gymSlots = gymSlots;
        this.payments = payments;
    }


    public FlipFitCustomer registerCustomer(int id, int age, String address) {
        FlipFitCustomer customer = new FlipFitCustomer();
        customer.setId(id);
        customer.setAge(age);
        customer.setAddress(address);

        customers.put(id, customer);

        System.out.println("User with email " + " registered as FLipfit Customer Successfully!");

        return customer;
    }
    
    private Scanner scanner = new Scanner(System.in);

    public void viewGymList() {
        System.out.println("\nAvailable Gyms:");
        System.out.println("-------------------------------------");
        System.out.println("| Gym ID | Gym Name  | Location    |");
        System.out.println("-------------------------------------");
        for (FlipFitGymCentre gym : gymCentres.values()) {
            if(gym.getStatus().equals("Approved")){
                System.out.printf("|   %d   | %s   | %s    |%n", gym.getGymId(), gym.getGymName(), gym.getGymAddress());
            }
        }
        System.out.println("-------------------------------------\n");
    }

    public void selectGym() {
        System.out.print("\nEnter Gym ID: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        if (gymCentres.containsKey(gymId)) {
            viewAvailableSlots(gymId);
        } else {
            System.out.println("Invalid Gym ID.\n");
        }
    }

    public void viewAvailableSlots(int gymId) {
        System.out.println("\nAvailable Gym Slots in " + gymCentres.get(gymId).getGymName() + " :");
        System.out.println("-------------------------------------");
        System.out.println("| Slot ID | Start Time  | End Time  | Available Seats |");
        System.out.println("-------------------------------------");
        for (FlipFitSlot slot : gymSlots.values()) {
            if (slot.getGymId() == gymId) {
                System.out.printf("|   %d     | %s  | %s  | %d               |%n", slot.getSlotId(), slot.getStartTime(), slot.getEndTime(), slot.getAvailableSeats());
            }
        }
        System.out.println("-------------------------------------\n");
    }

    public void bookGymSlot(int customerId) {
        System.out.print("Enter Slot ID to book: ");
        int slotId = scanner.nextInt();
        scanner.nextLine();
        if (gymSlots.containsKey(slotId) && gymSlots.get(slotId).getAvailableSeats() > 0) {
            String transactionId = processPayment(customerId, slotId);
            if (transactionId != null) {
                FlipFitBooking booking = new FlipFitBooking();
                booking.setCustomerId(customerId);
                booking.setSlotId(slotId);
                slotBookings.put(slotId, booking);
                int availableSeats = gymSlots.get(slotId).getAvailableSeats();
                gymSlots.get(slotId).setAvailableSeats(availableSeats - 1);
                System.out.println("Slot " + slotId + " booked successfully! Transaction ID: " + transactionId + "\n");
            } else {
                System.out.println("Payment failed. Booking not completed.\n");
            }
        } else {
            System.out.println("Invalid Slot ID or no available seats.\n");
        }
    }

    public String processPayment(int customerId, int slotId) {
        System.out.println("Processing payment...");
        String transactionId = UUID.randomUUID().toString();
        FlipFitPayment payment = new FlipFitPayment();
        payment.setTransactionId(transactionId);
        payment.setBookingId(slotId);
        payments.put(transactionId, payment);
        return transactionId;
    }

    

    public void viewMyBookings(int customerId) {
        System.out.println("\nYour Bookings:");
        System.out.println("-------------------------------------");
        System.out.println("| Slot ID | Gym Name  | Start Time  | End Time  |");
        System.out.println("-------------------------------------");
        for (FlipFitBooking booking : slotBookings.values()) {
            if (booking.getCustomerId() == customerId) {
                FlipFitSlot slot = gymSlots.get(booking.getSlotId());
                FlipFitGymCentre gym = gymCentres.get(slot.getGymId());
                System.out.printf("|   %d     | %s   | %s  | %s  |%n", slot.getSlotId(), gym.getGymName(), slot.getStartTime(), slot.getEndTime());
            }
        }
        System.out.println("-------------------------------------\n");
    }
}
