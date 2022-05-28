package com.example.geektrust.model;

import java.util.List;

public class Customer extends Person {
    private List<Loan> loans;

    public Customer(String name) {
        super(name);
    }
}
