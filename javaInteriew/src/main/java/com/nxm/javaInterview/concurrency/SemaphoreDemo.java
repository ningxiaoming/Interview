package com.nxm.javaInterview.concurrency;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//模拟抢车位：   六车抢三个车位
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);//模拟三个停车位

        for (int i = 1; i < 7; i++) {
            new Thread(()->{
                try {
                    //表示当前线程抢到停车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 号车抢到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+" 号车走了");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //表示当前线程释放停车位
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
