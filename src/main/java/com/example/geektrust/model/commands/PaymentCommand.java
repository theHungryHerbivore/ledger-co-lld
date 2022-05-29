package com.example.geektrust.model.commands;

import com.example.geektrust.exceptions.IllegalInvalidCommandDetailsException;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PaymentCommand implements Command {
    @Override
    public void execute(List<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details) {
        if (!isValid(details)) {
            throw new IllegalInvalidCommandDetailsException("Please Validate details for the PAYMENT Command : " + Arrays.toString(details));
        }

        Bank bank = banks.get(details[1]);
        Customer customer = customers.get(details[2]);
        Integer lumpsum = Integer.parseInt(details[3]);
        Integer emi = Integer.parseInt(details[4]);
        Loan loan = getLoan(bank, customer, loans);
        loan.addPayment(emi, lumpsum);
    }

    @Override
    public boolean isValid(String[] details) {
        return details != null && details.length == 5;
    }


}
