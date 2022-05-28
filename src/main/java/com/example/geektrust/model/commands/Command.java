package com.example.geektrust.model.commands;

import com.example.geektrust.model.Loan;

import java.util.List;

public interface Command {
    public void execute(List<Loan> loans, String[] details);
    public boolean isValid(String[] details);
}
