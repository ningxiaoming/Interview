package com.nxm.javaInterview.concurrency;


import java.util.concurrent.*;

/*

        ThreadPoolExecutor   :线程池的底层实现类

        第四种获得线程的方法：使用java多线程的方式，线程池
 */


/*

    public ThreadPoolExecutor(int corePoolSize,     ：线程池中的常驻核心线程数
                              int maximumPoolSize,  ：线程池能够容纳同时执行的最大线程数，此值必须大于等于一。
                              long keepAliveTime,   ：多余的空闲线程的存活时间。当前线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime时，
                                                      多余空闲线程会被销毁直到只剩下corePoolSize个线程为止。
                              TimeUnit unit,        ：keepAliveTime的单位。
                              BlockingQueue<Runnable> workQueue,    ：任务队列，被提交但尚未提交的任务。
                              ThreadFactory threadFactory,          ：表示生成线程池中工作线程的线程工厂，用于创建线程 一般用默认的即可。
                              RejectedExecutionHandler handler)     ：拒绝策略，（当队满并且工作线程大于线程池中最大线程数是  拒绝）
                                                                      表示当队列满了并且工作线程大于等于线程池的最大线程数（maximumPoolSize）

 */
/*
        newFixedThreadPool
        newSingleThreadExecutor
        newCachedThreadPool
        实际开发中都不使用这三个线程池， 因为这三个线程池中使用的队列是 LinkedBlockingQueue 这个队列的长度太长了了 21亿
        实际开发中自定义线程池。

        ThreadPoolExecutor类的四种拒绝策略：
            1。AbortPolicy()直接抛异常
            2。CallerRunsPolicy()回退调用者（多余的能处理就处理，不能处理的回退给调用者）
            3。DiscardOldestPolicy()抛弃队列中等待最久的任务
            4。DiscardPolicy()直接丢弃任务。

       问题：生产环境上如何设置线程池的最大数量（maximumPoolSize）
            合理配置线程池你是如何考虑的？
                    corePoolSize ： 0（类似于懒加载）
                    maximumPoolSize：根据cpu核数  进行设置
                            CPU密集型任务配置尽可能少的线程数量。
                            一般公式：maximumPoolSize=cup核数+1；

                            IO密集型：由于IO密集型任务线程并不是一直在执行任务，
                            则应配置尽可能多的线程，如：maximumPoolSize=cup核数*2；
                            IO密集型时，大部分线程都阻塞，故需要多配置线程数。
                            参考公式： CPU核数/(1-阻塞系数)   阻塞系数一般在 0.8~0.9之间。
                            比如 8核CPU： 8/(1-0.9) = 80个线程。

 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        threadPoolInit();
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,1l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());

        try {
            //模拟10个客户调用这5个线程
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 处理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }

    private static void threadPoolInit() {
        //一池五个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //一池一个线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池n线程
        ExecutorService threadPoolN = Executors.newCachedThreadPool();


        try {
            //模拟10个客户调用这5个线程
            for (int i = 0; i < 100; i++) {
                threadPoolN.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 处理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolN.shutdown();
        }
    }
}
