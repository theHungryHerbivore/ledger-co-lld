package com.example.geektrust.model.commands;

import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.List;
import java.util.Map;

public class BalanceCommand implements Command{
    @Override
    public void execute(List<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details) {

    }

    @Override
    public boolean isValid(String[] details) {
        return details!= null && details.length == 4;
    }
}
