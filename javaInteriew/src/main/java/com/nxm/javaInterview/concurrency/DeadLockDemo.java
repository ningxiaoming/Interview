package com.nxm.javaInterview.concurrency;




/*
        死锁：
            指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象，
            若无外力干涉那它们都将无法推进下去。
        查找方法：命令：jps -l
                      jstack xxx；进程数。

 */

import java.util.concurrent.TimeUnit;

/*
        死锁的Demo
 */
class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;
    public HoldLockThread(String lockA,String lockB){
        this.lockA=lockA;
        this.lockB=lockB;
    }


    @Override
    public void run() {

        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 持有"+lockA+"\t 尝试获得："+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 持有"+lockB+"\t 尝试获得："+lockA);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }            }
        }

    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();


    }
}
