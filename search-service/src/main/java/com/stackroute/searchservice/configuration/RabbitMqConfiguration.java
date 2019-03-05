package com.stackroute.searchservice.configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMqConfiguration{
    @Value("${jsa.rabbitmq.queue}")
    private String queueName;
    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;
    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;
    @Value("${jsb.rabbitmq.queue}")
    private String queueName1;
    @Value("${jsb.rabbitmq.exchange}")
    private String exchange1;
    @Value("${jsb.rabbitmq.routingkey}")
    private String routingKey1;
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }
    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    Queue queue1() {
        return new Queue(queueName1, false);
    }
    @Bean
    DirectExchange exchange1() {
        return new DirectExchange(exchange);
    }
    @Bean
    Binding binding1(Queue queue1, DirectExchange exchange1) {
        return BindingBuilder.bind(queue1).to(exchange1).with(routingKey1);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}



