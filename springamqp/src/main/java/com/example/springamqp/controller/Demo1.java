package com.example.springamqp.controller;

import java.math.BigInteger;

public class Demo1 {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 10);

    private static volatile int i = 100;

    public static void updata(){
        Integer integer = threadLocal.get();
        threadLocal.set(integer + 10);
//        System.out.println(Thread.currentThread().getName()+"===="+threadLocal.get());
        System.out.println(Thread.currentThread().getName()+"===="+i);
    }

    public static void main(String[] args) {



    }
}
