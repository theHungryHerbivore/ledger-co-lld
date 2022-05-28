package com.example.geektrust.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {
    public static List<String> readFile(String path) throws IOException {
        List<String> commands = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                commands.add(line);
            }

        } catch (IOException e) {
            throw new IOException("Error in Reading File" + e.getMessage());
        }
        return commands;
    }
}
