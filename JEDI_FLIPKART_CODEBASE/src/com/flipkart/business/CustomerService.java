package com.flipkart.business;

import java.util.Scanner;

import com.flipkart.bean.Customer;

public class CustomerService extends UserService {


    public Customer registerCustomer(String name, String email, String password, String contact, int age, String address) {
        Customer customer = new Customer();
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

   public void viewAvailableSlots() {
        System.out.println("\nAvailable Gym Slots:");
        System.out.println("-------------------------------------");
        System.out.println("| Slot No | Gym Name  | Slot Time   |");
        System.out.println("-------------------------------------");
        System.out.println("|   1     | FitZone   | 6pm - 7pm   |");
        System.out.println("|   2     | Iron Gym  | 7pm - 8pm   |");
        System.out.println("|   3     | PowerFit  | 8pm - 9pm   |");
        System.out.println("-------------------------------------\n");
    }
    
    public void bookGymSlot() {
    	Scanner scanner = new Scanner(System.in);
    	
        System.out.print("Enter Slot No to book: ");
        int slotNo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Slot " + slotNo + " booked successfully!\n");
        scanner.close();
        
    }
    
    public void viewMyBookings() {
        System.out.println("\nYour Bookings:");
        System.out.println("-------------------------------------");
        System.out.println("| Slot No | Gym Name  | Slot Time   |");
        System.out.println("-------------------------------------");
        System.out.println("|   1     | FitZone   | 6pm - 7pm   |");
        System.out.println("-------------------------------------\n");
    }


}
