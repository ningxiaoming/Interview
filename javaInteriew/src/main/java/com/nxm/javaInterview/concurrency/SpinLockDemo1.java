package com.nxm.javaInterview.concurrency;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*
        题目：实现一个自旋锁
        自旋锁的好处：循环比较获取直到成功为止，没有类似的wait的阻塞。

        通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒，B随后进来
        发现：当前有线程持有锁，不是null，所以只能通过自旋等待，直到A释放锁后，B随后抢到。

 */
public class SpinLockDemo1 {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"  come in");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void myUnLock(){
        atomicReference.compareAndSet(Thread.currentThread(),null);
        System.out.println(Thread.currentThread().getName()+"  go away");
    }

    public static void main(String[] args) {

        SpinLockDemo1 spinLockDemo1 = new SpinLockDemo1();

        new Thread(()->{
            spinLockDemo1.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo1.myUnLock();
        },"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            spinLockDemo1.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo1.myUnLock();
        },"t2").start();

    }

}
