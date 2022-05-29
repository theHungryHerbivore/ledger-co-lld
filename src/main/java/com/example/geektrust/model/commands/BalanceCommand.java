package com.example.geektrust.model.commands;

import com.example.geektrust.exceptions.InvalidCommandDetailsException;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BalanceCommand implements Command{
    @Override
    public void execute(List<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details) {
        if (!isValid(details)) {
            throw new InvalidCommandDetailsException("Please Validate details for the BALANCE Command : " + Arrays.toString(details));
        }
        Bank bank = banks.get(details[1]);
        Customer customer = customers.get(details[2]);
        Integer installment = Integer.parseInt(details[3]);
        Loan loan = getLoan(bank, customer, loans);

        Integer amountPaid = loan.getAmountPaid(installment);
        Integer emisRemaining = loan.getRemainingEmis(amountPaid);

        printBalanceDetails(bank, customer, amountPaid, emisRemaining);
    }

    public void printBalanceDetails(Bank bank, Customer customer, Integer amountPaid, Integer emisRemaining){
        System.out.println(bank.getName() + " " + customer.getName() + " " + amountPaid + " " + emisRemaining);
    }

    @Override
    public boolean isValid(String[] details) {
        return details!= null && details.length == 4;
    }

    @Override
    public Loan getLoan(Bank bank, Customer customer, List<Loan> loans) {
        return null;
    }
}
