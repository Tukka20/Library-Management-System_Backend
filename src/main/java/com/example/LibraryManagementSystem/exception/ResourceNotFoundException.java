package com.example.LibraryManagementSystem.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String massage)
    {
        super(massage);
    }

}
