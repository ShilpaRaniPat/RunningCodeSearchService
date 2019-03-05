package com.stackroute.exceptions;

import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    //Exception handler to handle Qusetion not found exceptions
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity handleQuestionNotFoundException(final QuestionNotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception handler to handle Qusetion already exists exceptions
    @ExceptionHandler(QuestionAlreadyExistsException.class)
    public ResponseEntity handleQuestionAlreadyExistsException(final QuestionAlreadyExistsException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

    //Exception handler to handle Comment already exists exceptions
    @ExceptionHandler(CommentAlreadyExistsException.class)
    public ResponseEntity handleCommentAlreadyExistsException(final CommentAlreadyExistsException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

    //Exception handler to handle Qusetion not found exceptions
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity handleCommentNotFoundException(final CommentNotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception handler to handle Answer not found exceptions
    @ExceptionHandler(AnswerNotFoundException.class)
    public ResponseEntity handleAnswerNotFoundException(final AnswerNotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception handler to handle Reply not found exceptions
    @ExceptionHandler(ReplyNotFoundException.class)
    public ResponseEntity handlereplyNotFoundException(final ReplyNotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
