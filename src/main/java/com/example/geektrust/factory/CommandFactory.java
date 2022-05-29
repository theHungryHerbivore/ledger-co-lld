package com.example.geektrust.factory;

import com.example.geektrust.service.commands.BalanceCommandService;
import com.example.geektrust.service.commands.CommandService;
import com.example.geektrust.service.commands.LoanCommandService;
import com.example.geektrust.service.commands.PaymentCommandService;

public class CommandFactory {
    public CommandService getLoanCommand(){
        return new LoanCommandService(new LoanFactory(), new CustomerFactory(), new BankFactory());
    }

    public CommandService getBalanceCommand(){
        return new BalanceCommandService();
    }

    public CommandService getPaymentCommand(){
        return new PaymentCommandService();
    }
}
