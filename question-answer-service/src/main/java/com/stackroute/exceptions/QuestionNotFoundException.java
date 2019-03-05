package com.stackroute.exceptions;

public class QuestionNotFoundException extends Exception {

    //Variable
    private String message;

    //Default Constructor
    public QuestionNotFoundException() {
    }

    //Parameterized Constructor
    public QuestionNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
