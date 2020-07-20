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
    public String httpGetTest(String str) throws InterruptedException {

        System.out.println(Thread.currentThread().getName()+"Runing");
        Thread.sleep(5000);

        return str;
    }

    @GetMapping("/http1")
    public String httpGetTest1(String str) throws InterruptedException {
//        string = "888";
        httpGetTest3();
        return str;
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
