package com.example.geektrust.exceptions;

public class InvalidCommandDetailsException extends RuntimeException{
    public InvalidCommandDetailsException(String message) {
        super(message);
    }
}
