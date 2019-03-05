package com.stackroute.exceptions;

public class AnswerNotFoundException extends Exception{

    //Variable
    private String message;

    //Default constructor
    public AnswerNotFoundException() {
    }

    //Parameterized Constructor
    public AnswerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
