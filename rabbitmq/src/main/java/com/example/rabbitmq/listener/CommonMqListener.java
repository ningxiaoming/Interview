package com.example.rabbitmq.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class CommonMqListener {

    /**
     * 监听消费用户日志
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message){
        try {
//            UserLog userLog=objectMapper.readValue(message, UserLog.class);
            log.info("监听消费用户日志 监听到消息： {} ",message.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
