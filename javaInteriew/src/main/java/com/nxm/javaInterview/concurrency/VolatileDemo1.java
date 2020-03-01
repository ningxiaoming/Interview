package com.nxm.javaInterview.concurrency;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{

    volatile int number = 0;

    public void iTo60(){
        number=60;
    }

    //注意：  此时number前面是加了 volatile修饰的
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }


}

/*
       1.验证Volatile的  可见性
        1.1: 假设  number=0  ；number变量之前没有加关键字修饰（public，private等）
        1.2: 属性添加了volatile，可以解决可见性问题。
       2.验证Volatile不保证原子性
        2.1: 原子性指的是什么意思？
              不可分割，完整性，也即某个线程正在做某个业务时，中间不可以被加塞或者不可以被分割。
              需要整体完整，要么同时成功，要么同时失败。
        2.2:  volatile不保证原子性演示：
        2.3: why

        2.4: 如何解决原子性
            * 加sync
            * 使用我们的juc下AtomicInteger


 */

public class VolatileDemo1 {

    public static void main(String[] args) throws InterruptedException {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000 ; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        //需要等待上面20个线程执行完成后，  再用main线程取得最终结果值：

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int type; finally number value:  "+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type; finally number value:  "+myData.atomicInteger);


    }

    //可以保证可见行 ， 即时通知其他线程变量已经改变。
    private static void seeOkVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.iTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number value"+ myData.number);
        },"aaa").start();

        //第二个线程 是 main线程
        while (myData.number == 0){


        }
        System.out.println(Thread.currentThread().getName()+"   "+myData.number+"\t mission is over");
    }
}
