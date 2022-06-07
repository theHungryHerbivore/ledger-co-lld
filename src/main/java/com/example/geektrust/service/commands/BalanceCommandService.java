package com.example.geektrust.service.commands;

import com.example.geektrust.constants.CommandLength;
import com.example.geektrust.exceptions.IllegalCommandException;
import com.example.geektrust.exceptions.IllegalOperationException;
import com.example.geektrust.model.Bank;
import com.example.geektrust.model.Customer;
import com.example.geektrust.model.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BalanceCommandService implements CommandService {
    @Override
    public void execute(List<Loan> loans, Map<String, Customer> customers, Map<String, Bank> banks, String[] details) {
        if (!isValid(details)) {
            throw new IllegalCommandException("Please Validate details for the BALANCE Command : " + Arrays.toString(details));
        }
        Bank bank = Optional.ofNullable(banks.get(details[1])).orElseThrow(()-> new IllegalOperationException("bank "+ details[1] + " does not exist"));
        Customer customer = Optional.ofNullable(customers.get(details[2])).orElseThrow(()-> new IllegalOperationException("customer "+ details[2] + " does not exist")) ;
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
        return details!= null && details.length == CommandLength.BALANCE_COMMAND_LENGTH;
    }
}
