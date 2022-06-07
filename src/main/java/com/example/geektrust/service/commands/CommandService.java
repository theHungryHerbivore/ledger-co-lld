package com.example.geektrust.service.commands;

import com.example.geektrust.exceptions.IllegalInputException;
import com.example.geektrust.exceptions.IllegalOperationException;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.List;
import java.util.Map;

public interface CommandService {
    void execute(List<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details);
    boolean isValid(String[] details);

    default Loan getLoan(Bank bank, Customer customer, List<Loan> loans) {
        return loans.stream().filter( loan -> customer.getName().equals(loan.getCustomer().getName()) && bank.getName().equals(loan.getBank().getName())).findAny().orElseThrow(()-> new IllegalOperationException("No loan exists for customer: " + customer.getName() + " in bank : "+ bank.getName()));
    }
}
