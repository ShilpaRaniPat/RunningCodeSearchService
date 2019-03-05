package com.stackroute.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/v1")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private AmqpTemplate amqpTemplate;

    //for user authentication service
    @Value("${jsh.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsh.rabbitmq.routingkey}")
    private String routingKey;

    //For recommandation command service
    @Value("${jsf.rabbitmq.exchange}")
    private String exchange1;

    @Value("${jsf.rabbitmq.routingkey}")
    private String routingKey1;

    /*
    saving userDTO in db
     */
    private final static String QUEUE_NAME = "register";
    @PostMapping("signup")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        User signUp = new User(user.getEmail(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getInterests());
        amqpTemplate.convertAndSend(exchange, routingKey, signUp);
        amqpTemplate.convertAndSend(exchange1, routingKey1, signUp);

        try{
            //User signUp1 = new User(userDTO.getFirstName(),userDTO.getLastName(),userDTO.getEmail(),encoder.encode(userDTO.getPassword()),userDTO.getInterests());
            userService.saveUser(signUp);
            return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
        }

    }
}

