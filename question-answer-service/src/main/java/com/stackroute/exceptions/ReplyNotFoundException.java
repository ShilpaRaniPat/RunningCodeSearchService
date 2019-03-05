package com.stackroute.exceptions;

public class ReplyNotFoundException extends Exception{

    //Variable
    private String message;

    //Default Constructor
    public ReplyNotFoundException() {
    }

    //Parametrized Constructor

    public ReplyNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
