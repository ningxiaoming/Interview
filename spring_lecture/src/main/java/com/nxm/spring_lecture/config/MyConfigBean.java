package com.nxm.spring_lecture.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class MyConfigBean {
    @Value("${myConfig.myObject.myName}")
    private String myName;
    @Value("${myConfig.myObject.myAge}")
    private String myAge;

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void setMyAge(String myAge) {
        this.myAge = myAge;
    }

    public String getMyName() {
        return myName;
    }

    public String getMyAge() {
        return myAge;
    }
}
