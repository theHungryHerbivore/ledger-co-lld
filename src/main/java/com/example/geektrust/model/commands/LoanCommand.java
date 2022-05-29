package com.example.geektrust.model.commands;

import com.example.geektrust.exceptions.InvalidCommandDetailsException;
import com.example.geektrust.factory.LoanFactory;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoanCommand implements Command {
    private final LoanFactory loanFactory;

    public LoanCommand(LoanFactory loanFactory) {
        this.loanFactory = loanFactory;
    }

    @Override
    public void execute(List<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details) {
        if (!isValid(details)) {
            throw new InvalidCommandDetailsException("Please Validate details for the LOAN Command : " + Arrays.toString(details));
        }
        Bank bank = getBank(banks, details[1]);
        Customer customer = getCustomer(customers, details[2]);
        Double principle = Double.parseDouble(details[3]);
        Double term = Double.parseDouble(details[4]);
        Double interest = Double.parseDouble(details[5]);
        Loan loan = loanFactory.getLoanOrCreate(bank, customer, principle, interest, term);
        loans.add(loan);
        customer.addLoan(loan);
    }

    @Override
    public boolean isValid(String[] details) {
        return details != null && details.length == 6;
    }

    public Customer getCustomer(Map<String, Customer> customers, String customerName) {
        return Optional.ofNullable(customers.get(customerName)).orElse(addNewCustomer(customerName, customers));
    }

    public Bank getBank(Map<String, Bank> banks, String bankName) {
        return Optional.ofNullable(banks.get(bankName)).orElse(addNewBank(bankName, banks));
    }

    private Customer addNewCustomer(String customerName, Map<String, Customer> customers) {
        Customer customer = new Customer(customerName);
        customers.put(customerName, customer);
        return customer;
    }

    private Bank addNewBank(String bankName, Map<String, Bank> banks) {
        Bank bank = new Bank(bankName);
        banks.put(bankName, bank);
        return bank;
    }
}
