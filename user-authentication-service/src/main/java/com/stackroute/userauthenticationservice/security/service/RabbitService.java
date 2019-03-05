package com.stackroute.userauthenticationservice.security.service;

import com.stackroute.userauthenticationservice.model.User;
import com.stackroute.userauthenticationservice.model.UserDTO;
import com.stackroute.userauthenticationservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {
    @Autowired
    UserRepository userRepository;

    @RabbitListener(queues = "${jsh.rabbitmq.queue}")
    public void receivedMessage(UserDTO msg) {

        System.out.println("Received Message: " + msg);
        User user=new User(msg.getEmail(),msg.getPassword());
        userRepository.save(user);
    }
}
