package com.nxm.javaInterview.concurrency;


/*
        题目二：synchronized和Lock有什么区别？用新的Lock有什么好处？你举例说说：
        1：原始构成：
            synchronized是关键字属于JVM层面的，
                monitorenter（底层是通过monitor对象来完成，。。。。）
            Lock是具体的l类（JUC）是api层面的锁
        2：使用方法
            synchronized不需要手动释放锁
            Reentrant需要手动加锁 释放锁
        3：等待是否可中断
            synchronized不可中断
            Reentrant可中断，1：设置超时方法，tryLock(long timeout,TimeUnit unit)
                            2：lockInterruptibly()代码块中，调用interrupt()方法可中断
        4：加锁是否公平
             syn非公平锁
             ReentrantLock默认非公平锁。-——————构造方法中传入true则为公平锁
        5：锁绑定多个条件Condition。
            synchronized没有
            ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程，要么全部唤醒所有线程。

 */

/*
        题目：多线程之间按照顺序调用，实现A->B->C三个线程启动   要求
        AA打印五次，BB打印10次，CC打印15次
        紧接着
        AA打印五次，BB打印10次，CC打印15次
        。。。。
        循环10轮
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private int number  = 1;  //A:1,B2,C3
    private Lock lock = new ReentrantLock();
    private Condition c1 =lock.newCondition();
    private Condition c2 =lock.newCondition();
    private Condition c3 =lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            while (number!=1){
                c1.await();
            }
            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName()+"线程打印了:"+i+"次");
            }
            number = 2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number!=3){
                c3.await();
            }
            for (int i = 1; i < 16; i++) {
                System.out.println(Thread.currentThread().getName()+"线程打印了:"+i+"次");
            }
            number = 1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number!=2){
                c2.await();
            }
            for (int i = 1; i < 11; i++) {
                System.out.println(Thread.currentThread().getName()+"线程打印了:"+i+"次");
            }
            number = 3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resource.print5();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resource.print10();
            }
            },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resource.print15();
            }
            },"CC").start();
    }
}
