package com.example.geektrust.exceptions;

public class InvalidInputCommandException extends RuntimeException{
    public InvalidInputCommandException(String message){
        super(message);
    }
}
