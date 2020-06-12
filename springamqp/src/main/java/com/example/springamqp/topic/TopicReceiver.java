package com.example.springamqp.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class TopicReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicReceiver.class);

    @RabbitListener(queues = "#{topicQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{topicQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    public void receive(String in, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'", receiver, in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in {}s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
