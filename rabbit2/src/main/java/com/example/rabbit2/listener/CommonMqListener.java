package com.example.rabbit2.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
    public class CommonMqListener {

    private static final Logger log = LoggerFactory.getLogger(CommonMqListener.class);

    @Autowired
    private ObjectMapper objectMapper;
 
/*    @Autowired
    private UserLogMapper userLogMapper;
 
    @Autowired
    private MailService mailService;*/

    /**
     * 监听消费用户日志
     *
     * @param message
     */
/*    @RabbitListener(queues = "${log.user.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message) {
        try {
//            UserLog userLog=objectMapper.readValue(message, UserLog.class);
            log.info("监听消费用户日志 监听到消息： {} ", message);
            //TODO：记录日志入数据表
//            userLogMapper.insertSelective(userLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}