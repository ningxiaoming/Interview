package com.nxm.javaInterview.concurrency;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{

    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getId()+"   invoked sendSMs()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId()+"   invoked sendEmail()");
    }


    @Override
    public void run() {
        get();
    }
    Lock lock = new ReentrantLock();

    Lock lock1 = new ReentrantLock();

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"   invoked get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock1.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"   invoked set()");
        }finally {
            lock1.unlock();
        }
    }

}


/*
        可重入锁（也叫递归锁）

        指在同一线程外层函数获得锁之后，内层递归函数仍然能获得该锁的代码，
        在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁，

        也就是说，线程可以进入任何一个它已经拥有的锁所同步这的代码块。


 */
public class ReenterLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        System.out.println("=============");

        Thread thread = new Thread(phone);
        thread.start();
        Thread thread1 = new Thread(phone);
        thread1.start();
    }
}
