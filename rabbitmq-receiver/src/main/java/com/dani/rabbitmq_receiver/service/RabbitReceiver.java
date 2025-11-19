package com.dani.rabbitmq_receiver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitReceiver {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(String message) {
        log.info(message + " received from queue");
    }
}
