package com.example.springamqp.simplemq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "simple.hello")
public class SimpleReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleReceiver.class);

    @RabbitHandler
    public void receive(String in){
        LOGGER.info(" [x] Received '{}'", in);
    }

}
