package com.stackroute.recommendationcommandservice.service;

import com.stackroute.recommendationcommandservice.UserDomain.UserDTO;
import com.stackroute.recommendationcommandservice.domain.AnswerDTO;
import com.stackroute.recommendationcommandservice.domain.QuestionDTO;
import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static com.stackroute.recommendationcommandservice.service.Actions.*;

@Component
@Slf4j
public class RabbitService {
    @Autowired
    private RecommendationCommandServiceImpl recommendationCommandServiceImpl;

    @RabbitListener(queues = "${jse.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO questionDTO) {
        log.info("Received Message: " + questionDTO);
        System.out.println(questionDTO.getAction());


//        user.setEmail(user.getEmail());
//        user.setInterests(user.getInterests());
//        List<String> name = user.getInterests();
//        User user1 = new User();
//        user1.setUserName(user.getEmail());
//        String userName = user1.getUserName();
//        User user2 = user1;
        Question ques = new Question();
        ques.setQuestionId(questionDTO.getQuestionId());
        List<String> name = questionDTO.getTopics();
        int questionId = ques.getQuestionId();
        ques.setQuestionString(questionDTO.getQuestion());
        ques.setTimestamp(questionDTO.getTimestamp());
        ques.setUpVote(questionDTO.getUpvotes());
        ques.setDownVote(questionDTO.getDownvotes());


        if (questionDTO.getAction() == POST_QUESTION) {
            recommendationCommandServiceImpl.saveQuestionToDb(ques);
            recommendationCommandServiceImpl.questionBelongsTopic(questionId, name);

        }

        if (questionDTO.getAction() == QUESTION_ANSWER) {
            System.out.println(questionDTO.getAnswer().size());
            int n = questionDTO.getAnswer().size();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO = questionDTO.getAnswer().get(n - 1);
            Answer answer = new Answer();
            answer.setAnswerString(answerDTO.getAnswer());
            String answerString = answer.getAnswerString();
            User user = new User();
            user.setUserName(answerDTO.getUser().getEmail());
            answer.setUser(Collections.singletonList(user));

            recommendationCommandServiceImpl.saveAnswerToDb(answer);
            recommendationCommandServiceImpl.answerIsAnswerOfQuestion(answerString, questionId);
        }

        if (questionDTO.getAction() == ANSWER_ACCEPT) {
            System.out.println(questionDTO.getAnswer().size());
            int n = questionDTO.getAnswer().size();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO = questionDTO.getAnswer().get(n - 1);
            Answer answer = new Answer();
            answer.setAnswerString(answerDTO.getAnswer());
            answer.setAccepted(answerDTO.isAccepted());
            String answerString = answer.getAnswerString();
            User user = new User();
            user.setUserName(answerDTO.getUser().getEmail());
            answer.setUser(Collections.singletonList(user));

            recommendationCommandServiceImpl.saveAnswerToDb(answer);


        }
    }


    @RabbitListener(queues = "${jsf.rabbitmq.queue}")
    public void receivedMessage(UserDTO user) {

        log.info("Received Message: " + user);


        user.setEmail(user.getEmail());
        user.setInterests(user.getInterests());
        User user1 = new User();
        user1.setUserName(user.getEmail());
        User user2 = user1;

        recommendationCommandServiceImpl.saveUserToDb(user2);

    }
}