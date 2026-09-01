package com.example.LibraryManagementSystem.exception;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String massage)
    {
        super(massage);
    }

}
