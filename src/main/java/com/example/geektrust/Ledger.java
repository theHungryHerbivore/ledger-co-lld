package com.example.geektrust;

import com.example.geektrust.exceptions.InvalidInputCommandException;
import com.example.geektrust.factory.commandfactory.*;
import com.example.geektrust.model.Loan;

import java.util.ArrayList;
import java.util.List;

public class Ledger {
    List<Loan> loans;
    CommandFactory commandFactory;

    public Ledger() {
        this.loans = new ArrayList<>();
        commandFactory = new CommandFactory();
    }

    public void process(List<String> commands) {
        for (String command : commands) {
            String[] details = command.split(" ");
            String commandType = details[0];
            switch (commandType) {
                case "LOAN":
                    commandFactory.getLoanCommand().execute(loans, details);
                    break;
                case "PAYMENT":
                    commandFactory.getPaymentCommand().execute(loans, details);
                    break;
                case "BALANCE":
                    commandFactory.getBalanceCommand().execute(loans, details);
                default:
                    throw new InvalidInputCommandException("We do not serve this command : " + commandType);
            }
        }
    }
}
