package com.example.geektrust.service.commands;

import com.example.geektrust.constants.CommandLength;
import com.example.geektrust.exceptions.IllegalInvalidCommandDetailsException;
import com.example.geektrust.factory.BankFactory;
import com.example.geektrust.factory.CustomerFactory;
import com.example.geektrust.factory.LoanFactory;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoanCommandService implements CommandService {
    private final LoanFactory loanFactory;
    private final CustomerFactory customerFactory;
    private final BankFactory bankFactory;

    public LoanCommandService(LoanFactory loanFactory, CustomerFactory customerFactory, BankFactory bankFactory) {
        this.loanFactory = loanFactory;
        this.customerFactory = customerFactory;
        this.bankFactory = bankFactory;
    }

    @Override
    public void execute(List<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details) {
        if (!isValid(details)) {
            throw new IllegalInvalidCommandDetailsException("Please Validate details for the LOAN Command : " + Arrays.toString(details));
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
        return details != null && details.length == CommandLength.LOAN_COMMAND_LENGTH;
    }

    public Customer getCustomer(Map<String, Customer> customers, String customerName) {
        return Optional.ofNullable(customers.get(customerName)).orElse(addNewCustomer(customerName, customers));
    }

    public Bank getBank(Map<String, Bank> banks, String bankName) {
        return Optional.ofNullable(banks.get(bankName)).orElse(addNewBank(bankName, banks));
    }

    private Customer addNewCustomer(String customerName, Map<String, Customer> customers) {
        Customer customer = customerFactory.getCustomer(customerName);
        customers.put(customerName, customer);
        return customer;
    }

    private Bank addNewBank(String bankName, Map<String, Bank> banks) {
        Bank bank = bankFactory.getBank(bankName);
        banks.put(bankName, bank);
        return bank;
    }
}
