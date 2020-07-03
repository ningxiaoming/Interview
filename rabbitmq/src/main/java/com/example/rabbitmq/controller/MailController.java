package com.example.rabbitmq.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MailController {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;

    @GetMapping("/login")
    public String login(String name, String pwd) {
        rabbitTemplate.setExchange(env.getProperty("mail.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("mail.routing.key.name"));
        rabbitTemplate.convertAndSend("mail发送");
        log.info("send ok");
        return name;
    }
}