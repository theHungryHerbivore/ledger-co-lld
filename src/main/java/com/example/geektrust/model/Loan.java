package com.example.geektrust.model;

import java.util.Map;

public class Loan {
    private Bank bank;
    private Customer customer;
    private Double principle;
    private Double interest;
    private Double term;
    private Map<Integer, Double> payments;
    private Double totalPayableAmount;

    public Loan(Bank bank, Customer customer, Double principle, Double interest, Double term) {
        this.bank = bank;
        this.customer = customer;
        this.principle = principle;
        this.interest = interest;
        this.term = term;
        this.totalPayableAmount = principle + (principle * interest * term)/100;
    }
}
