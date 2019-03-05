package com.stackroute.recommendationcommandservice.service;


import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;
import com.stackroute.recommendationcommandservice.repository.AnswerRepository;
import com.stackroute.recommendationcommandservice.repository.QuestionRepository;
import com.stackroute.recommendationcommandservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RecommendationCommandServiceImpl implements RecommendationCommandService {
    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public RecommendationCommandServiceImpl(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    /*method to save userDTO*/
    @Override
    public User saveUserToDb(User user) {
        userRepository.save(user);
        return user;
    }

    //method to get USERS//
    @Override
    public Collection<User> getUsers() {
        return userRepository.getAllUsers();
    }


    //method to add QUESTION//
    @Override
    public Question saveQuestionToDb(Question question) {
        questionRepository.save(question);
        return question;
    }


    //method to get QUESTIONS//
    @Override
    public Collection<Question> getQuestions() {

        return questionRepository.getAllQuestions();
    }


    //method to add ANSWER//
    @Override
    public Answer saveAnswerToDb(Answer answer) {

        answerRepository.save(answer);
        return answer;
    }

    //method to get ANSWERS//
    @Override
    public Collection<Answer> getAnswers() {

        return answerRepository.getAllAnswers();
    }


    //method to create relationship FOLLOWS between userDTO and topic//
    @Override
    public User userFollowsTopic(String userName, String Name) {

        return userRepository.userFollowsTopicRelationship(userName, Name);

    }


    //method to get USERS by reputation//
    @Override
    public User getByUser(int reputation) {

        return userRepository.getByUser(reputation);
    }


    //method to create relationship QUESTION_OF between question and topic//
    @Override
    public Question questionBelongsTopic(int questionId, List<String> Name) {

        return questionRepository.questionBelongsTopicRelationship(questionId, Name);
    }


    //method to create relationship ANSWERED between userDTO and answerDTO//
    @Override
    public User userAnsweredAnswer(String userName, String answerString) {

        return answerRepository.userAnsweredAnswerRelationship(userName, answerString);
    }


    //method to create relationship VIEWED between userDTO and question//
    @Override
    public User userViewedQuestion(String userName, int questionId) {

        return questionRepository.userViewedQuestionRelationship(userName, questionId);

    }


    //method to create relationship ANSWER_OF between answerDTO and question//
    @Override
    public Answer answerIsAnswerOfQuestion(String answerString, int questionId) {

        return questionRepository.answerIsAnswerOfQuestionRelationship(answerString, questionId);

    }


    //method to create relationship ASKED between userDTO and question//
    @Override
    public User userAskedQuestion(String userName, int questionId) {

        return questionRepository.userAskedQuestionRelationship(userName, questionId);

    }

    //method to create relationship ACCEPTED between userDTO and answerDTO//
    @Override
    public User userAcceptedAnswer(String userName, String answerString) {

        return answerRepository.userAcceptedAnswerRelationship(userName, answerString);

    }


    //method to create relationship UPVOTED between userDTO and answerDTO//
    @Override
    public User userUpvotedAnswer(String userName, String answerString) {

        return answerRepository.userUpvotedAnswerRelationship(userName, answerString);

    }


    //method to create relationship DOWNVOTED between userDTO and answerDTO//
    @Override
    public User userDownvotedAnswer(String userName, String answerString) {

        return answerRepository.userDownvotedAnswerRelationship(userName, answerString);

    }


    //method to create relationship UPVOTE between userDTO and question//
    @Override
    public User userUpvoteQuestion(String userName, int questionId) {

        return questionRepository.userUpvoteQuestionRelationship(userName, questionId);

    }


    //method to create relationship DOWNVOTE between userDTO and question//
    @Override
    public User userDownvoteQuestion(String userName, int questionId) {

        return questionRepository.userDownvoteQuestionRelationship(userName, questionId);

    }


}
