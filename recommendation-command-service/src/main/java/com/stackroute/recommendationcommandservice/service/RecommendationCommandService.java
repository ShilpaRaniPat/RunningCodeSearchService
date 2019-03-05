package com.stackroute.recommendationcommandservice.service;


import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;

import java.util.Collection;
import java.util.List;


public interface RecommendationCommandService {


    User saveUserToDb(User user);


    Collection<User> getUsers();


    Question saveQuestionToDb(Question question);


    Collection<Question> getQuestions();


    Answer saveAnswerToDb(Answer answer);


    Collection<Answer> getAnswers();


    User userFollowsTopic(String userName, String Name);


    User getByUser(int reputation);


    Question questionBelongsTopic(int questionId, List<String> Name);


    User userAnsweredAnswer(String userName, String answerString);


    User userViewedQuestion(String userName, int questionId);


    Answer answerIsAnswerOfQuestion(String answerString, int questionId);


    User userAskedQuestion(String userName, int questionId);


    User userAcceptedAnswer(String userName, String answerString);


    User userUpvotedAnswer(String userName, String answerString);


    User userDownvotedAnswer(String userName, String answerString);


    User userUpvoteQuestion(String userName, int questionId);

    User userDownvoteQuestion(String userName, int questionId);

}
