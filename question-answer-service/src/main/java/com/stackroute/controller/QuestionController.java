package com.stackroute.controller;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.exceptions.*;
import com.stackroute.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/v1")
public class QuestionController extends ResponseEntityExceptionHandler {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //Controller method to post a question
    @PostMapping("question")
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) throws QuestionAlreadyExistsException {
        System.out.println("backend");
        return new ResponseEntity<Question>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable int questionId) throws QuestionNotFoundException{
        return new ResponseEntity<Question>(questionService.getQuestion(questionId),HttpStatus.FOUND);
    }

    //Controller method to put answer to a question
    @PutMapping("question/answer/{questionId}")
    public ResponseEntity<Question> addAnswer(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException {
        return new ResponseEntity<Question>(questionService.addAnswer(questionId, answer), HttpStatus.OK);
    }

    //Controller method to put comments to question
    @PutMapping("question/comment/{questionId}")
    public ResponseEntity<Question> addComment(@PathVariable int questionId, @RequestBody Comment comment) throws QuestionNotFoundException {
        return new ResponseEntity<Question>(questionService.addQuestionComment(questionId, comment), HttpStatus.OK);
    }

    //Controller method to put reply to question comment
    @PutMapping("question/comment/reply/{questionId}")
    public ResponseEntity<Question> addCommentReply(@PathVariable int questionId, @RequestBody Comment comment) throws QuestionNotFoundException, CommentNotFoundException {
        return new ResponseEntity<Question>(questionService.addQuestionCommentReply(questionId,comment.getComment(),comment.getReplies()),HttpStatus.OK);
    }

    //Controller method to put comment to answer
    @PutMapping("question/answer/comment/{questionId}")
    public ResponseEntity<Question> addAnswerComment(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException {
        return new ResponseEntity<Question>(questionService.addAnswerComment(questionId,answer.getAnswer(),answer.getComments()),HttpStatus.OK);
    }

    //Controller method to put reply to the answer comment
    @PutMapping("question/answer/comment/reply/{questionId}")
    public ResponseEntity<Question> addAnswerCommentReply(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException{
        return new ResponseEntity<Question>(questionService.addAnswerCommentReply(questionId,answer.getAnswer(),answer.getComments()),HttpStatus.OK);
    }

    //Controller method to put question upvote
    @PutMapping("question/upvote/{questionId}")
    public ResponseEntity<Question> addQuestionUpvote(@PathVariable int questionId) throws QuestionNotFoundException {
        return new ResponseEntity<Question>(questionService.addQuestionUpvote(questionId),HttpStatus.OK);
    }

    //Controller method to put question downvote
    @PutMapping("question/downvote/{questionId}")
    public ResponseEntity<Question> addQuestionDownvote(@PathVariable int questionId) throws QuestionNotFoundException {
        return new ResponseEntity<Question>(questionService.addQuestionDownvote(questionId),HttpStatus.OK);
    }

    //Controller method to put upvote for answer
    @PutMapping("question/answer/upvote/{questionId}")
    public ResponseEntity<Question> addAnswerUpvote(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException {
        return new ResponseEntity<Question>(questionService.addAnswerUpvote(questionId,answer.getAnswer()),HttpStatus.OK);
    }

    //Controller method to put downvote for answer
    @PutMapping("question/answer/downvote/{questionId}")
    public ResponseEntity<Question> addAnswerDownvote(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException {
        return new ResponseEntity<Question>(questionService.addAnswerDownvote(questionId,answer.getAnswer()),HttpStatus.OK);
    }

    //Controller method to put answer accept
    @PutMapping("question/answer/accept/{questionId}")
    public ResponseEntity<Question> addAnswerAccept(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException {
        return new ResponseEntity<Question>(questionService.addQuestionAnswerAccepted(questionId,answer.getAnswer()),HttpStatus.OK);
    }

    //Controller method to put likes for question comment
    @PutMapping("question/comment/likes/{questionId}")
    public ResponseEntity<Question> addQuestionCommentLikes(@PathVariable int questionId, @RequestBody Comment comment) throws QuestionNotFoundException, CommentNotFoundException {
        return new ResponseEntity<Question>(questionService.addQuestionCommentLikes(questionId,comment.getComment()),HttpStatus.OK);
    }

    //Controller method to put likes for answer comment
    @PutMapping("question/answer/comment/likes/{questionId}")
    public ResponseEntity<Question> addQuestionAnswerCommentLikes(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, CommentNotFoundException, AnswerNotFoundException {
        return new ResponseEntity<Question>(questionService.addAnswerCommentLikes(questionId,answer),HttpStatus.OK);
    }

    //Controller method to put likes for question comment reply
    @PutMapping("question/comment/reply/likes/{questionId}")
    public ResponseEntity<Question> addQuestionCommentReplyLikes(@PathVariable int questionId, @RequestBody Comment comment) throws QuestionNotFoundException,CommentNotFoundException, ReplyNotFoundException {
        return new ResponseEntity<Question>(questionService.addQuestionCommentReplyLikes(questionId,comment),HttpStatus.OK);
    }

    //Controller method to put likes for answer comment reply
    @PutMapping("question/answer/comment/reply/likes/{questionId}")
    public ResponseEntity<Question> addAnswerCommentReplyLikes(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException,AnswerNotFoundException,CommentNotFoundException,ReplyNotFoundException {
        return new ResponseEntity<Question>(questionService.addAnswerCommentReplyLikes(questionId,answer), HttpStatus.OK);
    }

    //Controller method to get all the questions in database
    @GetMapping("questions")
    public ResponseEntity<?> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }
}