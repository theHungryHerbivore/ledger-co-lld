package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Loan> loans;

    public Customer(String name) {
        super(name);
        this.loans = new ArrayList<>();
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan){
        loans.add(loan);
    }
}
