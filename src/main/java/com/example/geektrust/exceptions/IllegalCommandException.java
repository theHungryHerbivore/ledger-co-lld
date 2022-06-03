package com.example.geektrust.exceptions;

public class IllegalCommandException extends RuntimeException{
    public IllegalCommandException(String message) {
        super(message);
    }
}
