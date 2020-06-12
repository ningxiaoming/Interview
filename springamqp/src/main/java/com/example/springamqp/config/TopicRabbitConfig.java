package com.example.springamqp.config;


import com.example.springamqp.topic.TopicReceiver;
import com.example.springamqp.topic.TopicSender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    通配符模式是可以根据路由键匹配规则选择性给多个消费者发送消息的模式，
    它包含一个生产者、两个消费者、两个队列和一个交换机。
    两个消费者同时绑定到不同的队列上去，两个队列通过路由键匹配规则绑定到交换机上去，
    生产者发送消息到交换机，交换机通过路由键匹配规则转发到不同队列，队列绑定的消费者接收并消费消息
 */


@Configuration
public class TopicRabbitConfig {

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("exchange.topic");
    }

    @Bean
    public Queue topicQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue topicQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding topicBinding1a(TopicExchange topic,Queue topicQueue1){
        return BindingBuilder.bind(topicQueue1).to(topic).with("*.orange.*");
    }

    @Bean
    public Binding topicBinding1b(TopicExchange topic,Queue topicQueue1){
        return BindingBuilder.bind(topicQueue1).to(topic).with("*.*.rabbit");
    }

    @Bean
    public Binding topicBinding2a(TopicExchange topic,Queue topicQueue2){
        return BindingBuilder.bind(topicQueue2).to(topic).with("lazy.#");
    }

    @Bean
    public TopicReceiver topicReceiver() {
        return new TopicReceiver();
    }

    @Bean
    public TopicSender topicSender() {
        return new TopicSender();
    }


}
