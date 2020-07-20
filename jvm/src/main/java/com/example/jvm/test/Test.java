package com.example.jvm.test;

public class Test {

    public static void main(String[] args) {

        new Thread(()->{
            System.out.println(111);
        },"AA").start();

    }
}
