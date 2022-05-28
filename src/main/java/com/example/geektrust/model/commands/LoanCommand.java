package com.example.geektrust.model.commands;

import com.example.geektrust.model.Loan;

import java.util.List;

public class LoanCommand implements Command {

    @Override
    public void execute(List<Loan> loans, String[] details) {

    }

    @Override
    public boolean isValid(String[] details) {
        return details != null && details.length == 6;
    }
}
