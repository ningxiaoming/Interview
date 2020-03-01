package com.nxm.javaInterview.concurrency;


import java.util.concurrent.atomic.AtomicInteger;

/*
        CAS是什么？ ====>compareAndSet
         比较并交换

        1。Unsafe类+CAS思想（自旋）
        2。AtomicInteger：
            CAS -----> Unsafe ----> CAS底层思想（do while 循环）----> (CAS缺点)ABA问题
            ----> 原子引用更新 -----> 如何规避ABA问题

            * ABA问题 ：指线程获取到的值是正确的  但是他的版本已经不是原来的版本了
            * 解决ABA问题： 理解原子引用 + 新增一种机制，那就是加入修改版本号（类似于时间戳）


 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2020)+"current data  "+ atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 2021)+"current data  "+ atomicInteger.get());


    }
}
