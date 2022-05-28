package com.example.geektrust.factory.commandfactory;

public class CommandFactory {
    public Command getLoanCommand(){
        return new LoanCommand();
    }

    public Command getBalanceCommand(){
        return new BalanceCommand();
    }

    public Command getPaymentCommand(){
        return new PaymentCommand();
    }
}
