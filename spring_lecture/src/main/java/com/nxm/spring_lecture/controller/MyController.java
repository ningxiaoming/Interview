package com.nxm.spring_lecture.controller;


import com.nxm.spring_lecture.config.MyConfigBean;
import com.nxm.spring_lecture.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class MyController {

    @Value("${myConfig.myObject.myName}")
    private String myName;

    @Autowired
    private MyConfigBean myConfigBean;

    @GetMapping(value = "/qq")
    public User qq(){
        User user = new User();
        user.setId(123);
        user.setName(this.myName);
        System.out.println(this.myConfigBean.getMyAge());
        return user;
    }
}
