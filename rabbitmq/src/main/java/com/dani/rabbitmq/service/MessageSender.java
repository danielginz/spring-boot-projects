package com.dani.rabbitmq.service;

import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Setter
@Service
public class MessageSender {

    @Value("${queue.name}")
    private String queueName;

    //It doesn't matter which one, RabbitTemplate or AmqpTemplate, to use
    @Autowired
    private RabbitTemplate rabbitTemplate;//it's class

    @Autowired
    private AmqpTemplate amqpTemplate;//it's interface

    public void send(String message) {
        amqpTemplate.convertAndSend(queueName, message);
    }

}
