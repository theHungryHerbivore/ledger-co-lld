package com.example.geektrust.factory.commandfactory;

import com.example.geektrust.model.Loan;

import java.util.List;

public interface Command {
    public void execute(List<Loan> loans, String[] details);
}
