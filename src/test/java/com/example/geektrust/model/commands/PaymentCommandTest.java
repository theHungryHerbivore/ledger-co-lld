package com.example.geektrust.model.commands;

import com.example.geektrust.factory.CommandFactory;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;
import com.example.geektrust.service.commands.CommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCommandTest {
    private Map<String, Bank> bankMap;
    private Map<String, Customer> customerMap;
    private String[] details;
    private List<Loan> loanList;
    private CommandService paymentCommand;

    @BeforeEach
    public void init() {
        bankMap = new HashMap<>();
        customerMap = new HashMap<>();
        details = new String[6];
        loanList = new ArrayList<>();
        paymentCommand = new CommandFactory().getPaymentCommand();
    }

    @Test
    public void isValid_returnsFalseIfLengthMismatch() {
        details = new String[1];
        assertFalse(paymentCommand.isValid(details));
    }

    @Test
    public void isValid_returnsTrueIfLengthEquals() {
        details = new String[5];
        assertTrue(paymentCommand.isValid(details));
    }

    @Test
    public void execute_addsPaymentInThePaymentMap() {
        details = new String[]{"PAYMENT", "IDIDI", "Dale", "1000", "5"};
        bankMap.put("IDIDI", new Bank("IDIDI"));
        customerMap.put("Dale", new Customer("Dale"));
        loanList.add(new Loan(bankMap.get("IDIDI"), customerMap.get("Dale"), 10000, 4, 5));
        Loan loan = loanList.get(0);
        assertTrue(loan.getPayments().isEmpty());
        paymentCommand.execute(loanList, customerMap, bankMap, details);
        assertEquals(loan.getPayments().get(5), 1000);
    }

}