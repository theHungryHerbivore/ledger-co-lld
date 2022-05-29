package com.example.geektrust;

import com.example.geektrust.factory.CommandFactory;
import com.example.geektrust.service.FileReaderService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List <String> commands = FileReaderService.readFile(args[0]);
        Ledger ledger = new Ledger(new CommandFactory());
        ledger.process(commands);
    }
}
