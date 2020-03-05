package com.nxm.javaInterview.concurrency;


import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"   come Callable");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //目前两个线程： main   AA

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread thread = new Thread(futureTask,"AA");
        thread.start();

        while (!futureTask.isDone()){

        }

        futureTask.get();//建议放在最后。 给AA线程足够多的时间。

        System.out.println(Thread.currentThread().getName()+futureTask.get());

    }
}
