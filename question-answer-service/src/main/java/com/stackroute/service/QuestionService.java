package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.domain.Replies;
import com.stackroute.exceptions.*;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question questionObject) throws QuestionAlreadyExistsException;

    Question addAnswer(int questionId, Answer answer) throws QuestionNotFoundException;

    Question getQuestion(int questionId) throws QuestionNotFoundException;

    Question addQuestionComment(int questionId, Comment comment) throws QuestionNotFoundException;

    Question addQuestionCommentReply(int questionId, String comment, List<Replies> replies) throws QuestionNotFoundException, CommentNotFoundException;

    Question addAnswerComment(int questionId, String answer, List<Comment> comment) throws QuestionNotFoundException, AnswerNotFoundException;

    Question addAnswerCommentReply(int questionId, String answer, List<Comment> comment) throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException;

    Question addQuestionUpvote(int questionId) throws QuestionNotFoundException;

    Question addQuestionDownvote(int questionId) throws QuestionNotFoundException;

    Question addAnswerUpvote(int questionId, String answer) throws QuestionNotFoundException, AnswerNotFoundException;

    Question addAnswerDownvote(int questionId, String answer) throws QuestionNotFoundException,AnswerNotFoundException;

    Question addQuestionCommentLikes(int questionId, String comment) throws QuestionNotFoundException, CommentNotFoundException;

    Question addQuestionCommentReplyLikes(int questionId, Comment comment) throws QuestionNotFoundException, CommentNotFoundException, ReplyNotFoundException;

    Question addAnswerCommentLikes(int questionId, Answer answer) throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException;

    Question addAnswerCommentReplyLikes(int questionId, Answer answer) throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException, ReplyNotFoundException;

    Question addQuestionAnswerAccepted(int questionId, String answer) throws QuestionNotFoundException, AnswerNotFoundException;

    List<Question> getAllQuestions();
}
