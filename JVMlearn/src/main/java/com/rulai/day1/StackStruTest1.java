package com.rulai.day1;

public class StackStruTest1 {
    public static void main(String[] args) {
        Runnable r = () ->{
            System.out.println(Thread.currentThread().getName() + "开始");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName() + "结束");
        };

        Thread thread1 = new Thread(r, "thread1");
        Thread thread2 = new Thread(r, "thread2");

        thread1.start();
        thread2.start();
    }

}


class DeadThread{
    static {
        if (true) {
            System.out.println(Thread.currentThread().getName() + "初始化了当前类");
            while (true) {

            }
        }
    }
}
