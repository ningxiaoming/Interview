package com.example.springamqp.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class FanoutReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutReceiver.class);

    @RabbitListener(queues = "#{fanoutQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{fanoutQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    private void receive(String in,int receiver) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.info("instance {} [x] Received '{}',收到消息", receiver, in);
        doWork(in);
        stopWatch.stop();
        LOGGER.info("instance {} [x] Received '{}',接收消息完成使用的秒",receiver, stopWatch.getTotalTimeSeconds());
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()
             ) {
            if (ch == '.'){
                Thread.sleep(1000);
            }
        }
    }
}
