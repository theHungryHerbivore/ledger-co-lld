package com.example.geektrust.factory;

import com.example.geektrust.model.commands.BalanceCommand;
import com.example.geektrust.model.commands.Command;
import com.example.geektrust.model.commands.LoanCommand;
import com.example.geektrust.model.commands.PaymentCommand;

public class CommandFactory {
    public Command getLoanCommand(){
        return new LoanCommand(new LoanFactory(), new CustomerFactory(), new BankFactory());
    }

    public Command getBalanceCommand(){
        return new BalanceCommand();
    }

    public Command getPaymentCommand(){
        return new PaymentCommand();
    }
}
