package com.example.springamqp.controller;


import com.example.springamqp.direct.DirectSender;
import com.example.springamqp.fanout.FanoutSender;
import com.example.springamqp.simplemq.SimpleSender;
import com.example.springamqp.topic.TopicSender;
import com.example.springamqp.workmq.WorkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api(tags = "RabbitController", description = "RabbitMQ功能测试")
@RestController
//@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private WorkSender workSender;
    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private DirectSender directSender;
    @Autowired
    private TopicSender topicSender;

    @GetMapping("/simple")
//    @ResponseBody
    public String simpleTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            simpleSender.send();
            Thread.sleep(1000);
        }
        return "nihao";
    }
    @GetMapping("/work")
//    @ResponseBody
    public String workTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
            Thread.sleep(1000);
        }
        return "nihao";
    }
    @GetMapping("/fanout")
//    @ResponseBody
    public String fanoutTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
            Thread.sleep(1000);
        }
        return "nihao";
    }
    @GetMapping("/direct")
//    @ResponseBody
    public String directTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            directSender.send(i);
            Thread.sleep(1000);
        }
        return "nihao";
    }

    @GetMapping("/topic")
//    @ResponseBody
    public String topicTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i);
            Thread.sleep(1000);
        }
        return "lalalla";
    }

}
