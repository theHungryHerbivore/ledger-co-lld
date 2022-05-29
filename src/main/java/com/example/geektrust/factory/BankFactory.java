package com.example.geektrust.factory;

import com.example.geektrust.model.Bank;

public class BankFactory {
    public Bank getBank(String bankName){
        return new Bank(bankName);
    }
}
