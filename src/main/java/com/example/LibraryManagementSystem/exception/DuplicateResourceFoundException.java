package com.example.LibraryManagementSystem.exception;

public class DuplicateResourceFoundException extends RuntimeException{


    public DuplicateResourceFoundException(String massage)
    {
        super(massage);
    }

}
