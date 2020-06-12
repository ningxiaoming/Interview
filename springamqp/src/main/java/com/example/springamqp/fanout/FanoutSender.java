package com.example.springamqp.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class FanoutSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutSender.class);

    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "exchange.fanout";

    public void send(int index){
        StringBuilder builder = new StringBuilder("Hello");
        int limitIndex = index % 3 +1;

        for (int i = 0; i < limitIndex; i++) {
            builder.append('.');
        }
        builder.append(index + 1);
        String message = builder.toString();
        template.convertAndSend(exchangeName,"",message);
        LOGGER.info(" [x] Sent '{}',发送消息", message);

    }
}
