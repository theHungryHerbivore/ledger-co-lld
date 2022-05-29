package com.example.geektrust.factory;

import com.example.geektrust.model.Customer;

public class CustomerFactory {
    public Customer getCustomer(String customerName){
        return new Customer(customerName);
    }
}
