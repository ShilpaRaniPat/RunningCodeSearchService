package com.stackroute.exceptions;

public class CommentAlreadyExistsException extends Exception{

    //Variable
    private String message;

    //Default constructor
    public CommentAlreadyExistsException() {
    }

    //Parametrized Constructor
    public CommentAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
