package com.example.geektrust.factory.commandfactory;

import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

public class LoanFactory {
    public Loan getLoan(Bank bank, Customer customer, Double principle, Double interest, Double term){
        return new Loan(bank, customer, principle, interest, term);
    }
}
