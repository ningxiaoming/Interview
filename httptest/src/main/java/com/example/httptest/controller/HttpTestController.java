package com.example.httptest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class HttpTestController {

//    public HttpServletResponse response;
    private static String string = "123";

    @GetMapping("/http")
    public String httpGetTest(HttpServletResponse response){
//        HttpServletResponse resp = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader("Cache-Control","max-age=1200");
//        response.setDateHeader("expries", System.currentTimeMillis() + 1000 * 3600);
        response.setHeader("last-modified","Fri, 03 Jul 2020 02:13:21 GMT");
        response.setHeader("expires","Fri, 03 Jul 2020 02:33:21 GMT");
        return string;
    }

    @GetMapping("/http1")
    public String httpGetTest1() throws InterruptedException {
        string = "888";
        httpGetTest3();
        return string;
    }

    @GetMapping("/http2")
    public String httpGetTest2() throws InterruptedException {
        String str = "1123";
        System.out.println("123");
        httpGetTest3();
        return str;
    }

    public static void httpGetTest3() throws InterruptedException {

        ArrayList<String> list = new ArrayList<String>();
        list.add("1");

        System.out.println("========================");
        Thread.sleep(20000);
        System.out.println("123");
    }
}
