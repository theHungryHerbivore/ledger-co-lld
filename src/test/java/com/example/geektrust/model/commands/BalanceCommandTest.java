package com.example.geektrust.model.commands;

import com.example.geektrust.factory.CommandFactory;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;
import com.example.geektrust.service.commands.CommandService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BalanceCommandTest {
    private Map<String, Bank> bankMap;
    private Map<String, Customer> customerMap;
    private String[] details;
    private List<Loan> loanList;
    private CommandService balanceCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @BeforeEach
    public void init(){
        bankMap = new HashMap<>();
        customerMap = new HashMap<>();
        details = new String[6];
        loanList = new ArrayList<>();
        balanceCommand = new CommandFactory().getBalanceCommand();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams(){
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void isValid_returnsFalseIfLengthMismatch(){
        details = new String[1];
        assertFalse(balanceCommand.isValid(details));
    }

    @Test
    public void isValid_returnsTrueIfLengthEquals(){
        details = new String[4];
        assertTrue(balanceCommand.isValid(details));
    }

    @Test
    public void execute_calculatesTheBalance(){
        details = new String[]{"BALANCE", "IDIDI", "Dale" ,"5"};
        bankMap.put("IDIDI", new Bank("IDIDI"));
        customerMap.put("Dale", new Customer("Dale"));
        loanList.add(new Loan(bankMap.get("IDIDI"), customerMap.get("Dale"), 10000,4,5));
        balanceCommand.execute(loanList,customerMap,bankMap, details);
        assertEquals("IDIDI Dale 1000 55", outContent.toString().trim());

    }

}