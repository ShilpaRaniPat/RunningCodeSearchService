package com.stackroute.exceptions;

public class QuestionAlreadyExistsException extends Exception {

    //Variable
    private String message;

    //Default Constructor
    public QuestionAlreadyExistsException() {
    }

    //Parameterized Constructor

    public QuestionAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
