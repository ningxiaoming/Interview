package com.example.springamqp.config;


import com.example.springamqp.direct.DirectReceiver;
import com.example.springamqp.direct.DirectSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
    路由模式是可以根据路由键选择性给多个消费者发送消息的模式，
    它包含一个生产者、两个消费者、两个队列和一个交换机。
    两个消费者同时绑定到不同的队列上去，两个队列通过路由键绑定到交换机上去，
    生产者发送消息到交换机，交换机通过路由键转发到不同队列，队列绑定的消费者接收并消费消息。

 */

@Configuration
public class DirectRabbitConfig {

    @Bean
    public DirectExchange direct(){
        return new DirectExchange("exchange.direct");
    }
    @Bean
    public Queue directQueue1(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue directQueue2(){
        return new AnonymousQueue();
    }

    @Bean
    public Binding directBinding1a(DirectExchange direct,Queue directQueue1){
        return BindingBuilder.bind(directQueue1).to(direct).with("orange");
    }
    @Bean
    public Binding directBinding1b(DirectExchange direct,Queue directQueue1){
        return BindingBuilder.bind(directQueue1).to(direct).with("black");
    }
    @Bean
    public Binding directBinding2a(DirectExchange direct,Queue directQueue2){
        return BindingBuilder.bind(directQueue2).to(direct).with("green");
    }
    @Bean
    public Binding directBinding2b(DirectExchange direct,Queue directQueue2){
        return BindingBuilder.bind(directQueue2).to(direct).with("black");
    }

    @Bean
    public DirectReceiver receiver() {
        return new DirectReceiver();
    }
    @Bean
    public DirectSender directSender() {
        return new DirectSender();
    }




}
