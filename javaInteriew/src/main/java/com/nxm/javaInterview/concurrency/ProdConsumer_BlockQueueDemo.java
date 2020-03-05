package com.nxm.javaInterview.concurrency;



/*


 volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用

 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG = true;//默认开启，进行生产和消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    //生产
    public void myProd() throws Exception {
        String strValue = "";
        boolean bb;
        while (FLAG){
            strValue = atomicInteger.getAndIncrement()+"";
            bb = blockingQueue.offer(strValue,2l, TimeUnit.SECONDS);
            if (bb) {
                System.out.println(Thread.currentThread().getName() + "    线程生产：" + strValue + "成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "    线程生产：" + strValue + "失败");

            }
            TimeUnit.SECONDS.sleep(1);

        }
        System.out.println("main线程停止了生产");

    }
    //消费
    public void myConsumer() throws InterruptedException {
        String strValue = null;
        while (FLAG){
            strValue = blockingQueue.poll(2l,TimeUnit.SECONDS);
            if (null == strValue || strValue.equalsIgnoreCase("")){
                System.out.println();
                System.out.println();
                System.out.println(Thread.currentThread().getName()+" 超过两秒钟没有取到，消费推出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"  消费者消费成功"+strValue);
        }
    }
    public void myStop(){
        FLAG = false;
    }


}

public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(5));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"   生产者开始生产了");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"   消费者开始消费了");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        TimeUnit.SECONDS.sleep(5);

        myResource.myStop();

    }
}
