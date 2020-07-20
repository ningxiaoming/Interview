package com.rulai.threadlocal;

public class Demo1 {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 10);

    public static void updata(){
        Integer integer = threadLocal.get();
        threadLocal.set(integer + 10);
        System.out.println(Thread.currentThread().getName()+threadLocal.get());
    }

    public static void main(String[] args) {

        new Thread(()->{
            updata();
        },"thread1").start();

        new Thread(()->{
            updata();
        },"thread2").start();

        new Thread(()->{
            updata();
        },"thread3").start();

    }
}
