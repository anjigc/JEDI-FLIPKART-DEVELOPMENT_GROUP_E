package com.flipkart.dao;

import com.flipkart.bean.FlipFitCustomer;
import java.util.List;

public interface FlipFitCustomerDAOInterface {
    void addCustomer(FlipFitCustomer customer);
    FlipFitCustomer getCustomerById(int id);
    List<FlipFitCustomer> getAllCustomers();
    void updateCustomer(FlipFitCustomer customer);
    void deleteCustomer(int id);
}


