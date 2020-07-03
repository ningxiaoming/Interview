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
public class UserController {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;

/*    @GetMapping("/login")
    public String login(String name, String pwd) {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));

        Message message=MessageBuilder.withBody(name.getBytes()).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);

        return name;
    }*/
}