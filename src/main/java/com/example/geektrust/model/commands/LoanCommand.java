package com.example.geektrust.model.commands;

import com.example.geektrust.exceptions.InvalidCommandDetailsException;
import com.example.geektrust.factory.commandfactory.LoanFactory;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class LoanCommand implements Command {
    private final LoanFactory loanFactory;

    public LoanCommand(LoanFactory loanFactory) {
        this.loanFactory = loanFactory;
    }

    @Override
    public void execute(Set<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details) {
        if (!isValid(details)) {
            throw new InvalidCommandDetailsException("Please Validate details for the LOAN Command : " + Arrays.toString(details));
        }
        Bank bank = getBank(banks, details[1]);
        Customer customer = getCustomer(customers, details[2]);
        Double principle = Double.parseDouble(details[3]);
        Double term = Double.parseDouble(details[4]);
        Double interest = Double.parseDouble(details[5]);
        Loan loan = loanFactory.getLoan(bank, customer, principle, interest, term);
        loans.add(loan);
        customer.addLoan(loan);
    }

    @Override
    public boolean isValid(String[] details) {
        return details != null && details.length == 6;
    }

    public Customer getCustomer(Map<String, Customer> customers, String customerName){
        return customers.getOrDefault(customerName, new Customer(customerName));
    }
    public Bank getBank(Map<String, Bank> banks, String bankName){
        return banks.getOrDefault(bankName, new Bank(bankName));
    }
}
