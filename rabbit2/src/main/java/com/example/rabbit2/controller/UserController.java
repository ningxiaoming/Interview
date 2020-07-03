package com.example.rabbit2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
    public class UserController {
 
    private static final Logger log= LoggerFactory.getLogger(UserController.class);
 
    private static final String Prefix="user";
 
    @Autowired
    private ObjectMapper objectMapper;
 
/*    @Autowired
    private UserMapper userMapper;
 
    @Autowired
    private UserLogMapper userLogMapper;*/
 
    @Autowired
    private RabbitTemplate rabbitTemplate;
 
    @Autowired
    private Environment env;
 
//    @RequestMapping(value = Prefix+"/login",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @GetMapping("/login")
    public String login(@RequestParam("userName") String userName,@RequestParam("password") String password){
//        BaseResponse response=new BaseResponse(StatusCode.Success);
        String response = "成功了。。。";
        try {
            //TODO：执行登录逻辑
//            User user=userMapper.selectByUserNamePassword(userName,password);
            if (userName!=null){
                //TODO：异步写用户日志
                try {
//                    UserLog userLog=new UserLog(userName,"Login","login",objectMapper.writeValueAsString(user));
//                    userLog.setCreateTime(new Date());
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
                    rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));
 
                    Message message= MessageBuilder.withBody(objectMapper.writeValueAsBytes(userName)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
                    message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
                    rabbitTemplate.convertAndSend(message);         
                }catch (Exception e){
                    e.printStackTrace();
                }
 
                //TODO：塞权限数据-资源数据-视野数据
            }else{
                response = "失败了";
//                response=new BaseResponse(StatusCode.Fail);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }}