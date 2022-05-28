package com.example.geektrust.model.commands;

import com.example.geektrust.exceptions.InvalidCommandDetailsException;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.List;

public class LoanCommand implements Command {

    @Override
    public void execute(List<Loan> loans, String[] details) {
       if(!isValid(details)){
            throw new InvalidCommandDetailsException("Please Validate details for the LOAN Command : " + details);
        }
        Bank bank = new Bank(details[1]);
        Customer customer = new Customer(details[2]);
        Double principle = Double.parseDouble(details[3]);
        Double term = Double.parseDouble(details[4]);
        Double interest = Double.parseDouble(details[5]);
        loans.add(new Loan(bank, customer, principle, interest, term));
    }

    @Override
    public boolean isValid(String[] details) {
        return details != null && details.length == 6;
    }
}
