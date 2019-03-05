package com.stackroute.service;

import com.stackroute.domain.QuestionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {
    @Autowired
    Notifications notifications;

    @RabbitListener(queues = "${jsd.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO msg) {

        System.out.println("Received Message: " + msg);
        if(msg.getAction()==Actions.QUESTION_ANSWER){
            notifications.generateAnswerNotification(msg.getUser().getEmail(),msg.getQuestion());
        }
        if(msg.getAction()==Actions.ANSWER_ACCEPT){
            notifications.generateLikedNotification(msg.getAnswer().get(0).getUser().getEmail(),msg.getQuestion());
        }
    }

}