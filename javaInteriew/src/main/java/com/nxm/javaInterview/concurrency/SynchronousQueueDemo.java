package com.nxm.javaInterview.concurrency;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/*

        阻塞队列SynchronousQueue的演示
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"线程生产了 1");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"线程生产了 2");
                blockingQueue.put("3");
                System.out.println(Thread.currentThread().getName()+"线程生产了 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"线程消费了 1");
                TimeUnit.SECONDS.sleep(3);
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"线程消费了 2");
                TimeUnit.SECONDS.sleep(3);
                blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"线程消费了 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();

    }
}
