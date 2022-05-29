package com.example.geektrust;

import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.factory.CommandFactory;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ledger {
    List<Loan> loans;
    Map<String, Customer> customers;
    Map<String, Bank> banks;
    CommandFactory commandFactory;

    public Ledger(CommandFactory commandFactory) {
        this.loans = new ArrayList<>();
        this.customers = new HashMap<>();
        this.banks = new HashMap<>();
        this.commandFactory = commandFactory;
    }

    public void process(List<String> commands) {
        for (String command : commands) {
            String[] details = command.split(" ");
            String commandType = details[0];
            switch (commandType) {
                case "LOAN":
                    commandFactory.getLoanCommand().execute(loans, customers, banks, details);
                    break;
                case "PAYMENT":
                    commandFactory.getPaymentCommand().execute(loans, customers, banks, details);
                    break;
                case "BALANCE":
                    commandFactory.getBalanceCommand().execute(loans, customers, banks, details);
                default:
                    throw new InvalidInputException("We do not serve this command : " + commandType);
            }
        }
    }
}
