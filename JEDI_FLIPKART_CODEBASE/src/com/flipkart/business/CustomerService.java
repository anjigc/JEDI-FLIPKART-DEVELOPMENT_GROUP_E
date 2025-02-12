package com.flipkart.business;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Booking;
import java.util.List;
import java.util.ArrayList;

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
        return customer;
    }


}
