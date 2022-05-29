package com.example.geektrust.model.commands;

import com.example.geektrust.factory.CommandFactory;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanCommandTest {
    private Map<String, Bank> bankMap;
    private Map<String, Customer> customerMap;
    private String[] details;
    private List<Loan> loanList;
    private Command loanCommand;

    @BeforeEach
    public void init() {
        bankMap = new HashMap<>();
        customerMap = new HashMap<>();
        details = new String[6];
        loanList = new ArrayList<>();
        loanCommand = new CommandFactory().getLoanCommand();
    }

    @Test
    public void isValid_returnsFalseIfLengthMismatch() {
        details = new String[1];
        assertFalse(loanCommand.isValid(details));
    }

    @Test
    public void isValid_returnsTrueIfLengthEquals() {
        details = new String[6];
        assertTrue(loanCommand.isValid(details));
    }

    @Test
    public void execute_createsLoan_addsBankAndCustomer() {
        details = new String[]{"LOAN", "IDIDI", "Dale", "10000", "5", "4"};
        loanCommand.execute(loanList, customerMap, bankMap, details);
        assertTrue(loanList.get(0).getBank().getName().equals("IDIDI")  && loanList.get(0).getCustomer().getName().equals("Dale"));
    }

    @Test void execute_createsLoan_usesExistingBankAndCustomer(){
        details = new String[]{"LOAN", "IDIDI", "Dale", "10000", "5", "4"};
        loanCommand.execute(loanList, customerMap, bankMap, details);
        details = new String[]{"LOAN", "IDIDI", "Dale", "10050", "6", "7"};
        loanCommand.execute(loanList, customerMap, bankMap, details);
        Loan firstLoan = loanList.get(0);
        Loan secondLoan = loanList.get(1);
        assertTrue(firstLoan.getBank().equals(secondLoan.getBank()) && firstLoan.getCustomer().equals(secondLoan.getCustomer()));
    }

}