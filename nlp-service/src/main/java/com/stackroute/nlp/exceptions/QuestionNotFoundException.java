package com.stackroute.nlp.exceptions;

public class QuestionNotFoundException extends Exception {
    private String message;

    public QuestionNotFoundException(String message){
        super(message);
    }
}