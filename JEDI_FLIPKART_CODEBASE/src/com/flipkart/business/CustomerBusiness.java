package com.flipkart.business;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Booking;
import java.util.List;
import java.util.ArrayList;

public class CustomerBusiness {

    private List<Customer> customers = new ArrayList<>();

    public Customer registerCustomer(String name, String mobileNo, String email, int age, String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setContact(mobileNo);
        customer.setEmail(email);
        customer.setAge(age);
        customer.setAddress(address);
        customer.setBookings(new ArrayList<>());
        customers.add(customer);
        return customer;
    }

    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public boolean updateCustomerDetails(String email, String newName, String newMobileNo, int newAge, String newAddress) {
        Customer customer = getCustomerByEmail(email);
        if (customer != null) {
            customer.setName(newName);
            customer.setContact(newMobileNo);
            customer.setAge(newAge);
            customer.setAddress(newAddress);
            return true;
        }
        return false;
    }

    public boolean deleteCustomer(String email) {
        Customer customer = getCustomerByEmail(email);
        if (customer != null) {
            customers.remove(customer);
            return true;
        }
        return false;
    }

    public boolean addBookingToCustomer(String email, Booking booking) {
        Customer customer = getCustomerByEmail(email);
        if (customer != null) {
            customer.getBookings().add(booking);
            return true;
        }
        return false;
    }

    public List<Booking> getCustomerBookings(String email) {
        Customer customer = getCustomerByEmail(email);
        return customer != null ? customer.getBookings() : null;
    }
}
