package com.nxm.javaInterview.concurrency;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/*

    ABA问题的解决方案      AtomicStampedReference
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>();
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10,1);

    public static void main(String[] args) {
//        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

        System.out.println("===============ABA问题==================");
        Integer integer = Integer.valueOf(200);
        atomicReference.set(integer);
        new Thread(()->{
            //compareAndSet   的第一个参数和原始参数的比较 使用的  ==号  所以是内存地址的比较
            System.out.println(atomicReference.compareAndSet(integer, 101)+"  1  "+atomicReference.get());
            System.out.println(atomicReference.compareAndSet(101, 100)+"  2  "+atomicReference.get());
        },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 1200)+"  3  "+ atomicReference.get());
        },"t2").start();

        System.out.println("===============ABA问题解决方案 AtomicStampedReference ==================");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet(10, 11, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+" 是否修改成果："+b+"  修改之后的版本号："+atomicStampedReference.getStamp()+"  修改之后的值："+atomicStampedReference.getReference());
            boolean b1 = atomicStampedReference.compareAndSet(11, 10, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+" 是否修改成果："+b1+"  修改之后的版本号："+atomicStampedReference.getStamp()+"  修改之后的值："+atomicStampedReference.getReference());

        },"t3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b1 = atomicStampedReference.compareAndSet(10, 101, stamp, atomicStampedReference.getStamp() + 1);
            boolean b2 = atomicStampedReference.compareAndSet(10, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName()+" 是否修改成果："+b1+"  "+b2+"  修改之后的版本号："+atomicStampedReference.getStamp()+"  修改之后的值："+atomicStampedReference.getReference());

        },"t4").start();

    }
}
