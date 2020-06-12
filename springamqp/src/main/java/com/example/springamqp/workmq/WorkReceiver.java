package com.example.springamqp.workmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "work.hello")
public class WorkReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkReceiver.class);

    private final int instance;

    public WorkReceiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'", this.instance, in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in {}s", this.instance, watch.getTotalTimeSeconds());

    }
    private void doWork(String in) throws InterruptedException {
        for (char ch:in.toCharArray()
             ) {
            if (ch == '.'){
                Thread.sleep(1000);
            }
        }
    }
}
