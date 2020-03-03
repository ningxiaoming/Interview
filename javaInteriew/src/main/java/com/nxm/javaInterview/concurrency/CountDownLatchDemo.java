package com.nxm.javaInterview.concurrency;


import java.util.concurrent.CountDownLatch;

/*
        CountDownLatchDemo 此类的作用是  让各个线程按照预想的 顺序执行   减法。
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        closeDoor();


    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 1; i < 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 号员工下班走人");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" 门卫最后下班走人");
    }
}
