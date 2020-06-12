package com.example.springamqp.config;



import com.example.springamqp.simplemq.SimpleReceiver;
import com.example.springamqp.simplemq.SimpleSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    简单模式是最简单的消息模式，它包含一个生产者、一个消费者和一个队列。
    生产者向队列里发送消息，消费者从队列中获取消息并消费。

 */

@Configuration
public class SimpleRabbitConfig {

    @Bean
    public Queue hello(){
        return new Queue("simple.hello");
    }
    @Bean
    public SimpleSender simpleSender(){
        return new SimpleSender();
    }

    @Bean
    public SimpleReceiver simpleReceiver(){
        return new SimpleReceiver();
    }
}
