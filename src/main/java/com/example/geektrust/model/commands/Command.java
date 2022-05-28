package com.example.geektrust.model.commands;

import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.Map;
import java.util.Set;

public interface Command {
    public void execute(Set<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details);
    public boolean isValid(String[] details);

}
