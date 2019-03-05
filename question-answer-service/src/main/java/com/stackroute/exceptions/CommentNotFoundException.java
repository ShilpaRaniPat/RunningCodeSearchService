package com.stackroute.exceptions;

public class CommentNotFoundException extends Exception{

    //Variable
    private String message;

    //Default Constructor
    public CommentNotFoundException() {
    }

    //Parameterized Constructor
    public CommentNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
