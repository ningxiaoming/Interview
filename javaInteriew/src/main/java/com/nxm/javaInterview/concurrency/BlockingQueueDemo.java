package com.nxm.javaInterview.concurrency;


/*
    ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按照 FIFO（先进先出）原则对元素进行排序
    LinkedBlockingQueue：一个基于链表的阻塞队列，此队列按照FIFO（先进先出）原则对元素进行排序，吞吐量要高于 ArrayBlockingQueue
    SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常
    要高



    1 队列

    2 阻塞队列
        2.1 阻塞队列有没有好的一面

        2.2 不得不阻塞，你如何管理

 */

import java.util.concurrent.*;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

        //抛出异常操作组
        blockingQueue.add(1);
        blockingQueue.remove();
        blockingQueue.element();

        //不抛出异常组  成功返回true，失败返回false
        blockingQueue.offer(1);
        blockingQueue.poll();
        blockingQueue.peek();

        //慎用组   队满的时候  阻塞-->程序不停。
        blockingQueue.put(1);
        blockingQueue.take();

        //设置阻塞时间组
        blockingQueue.offer(1,2, TimeUnit.SECONDS);
        blockingQueue.poll(2,TimeUnit.SECONDS);


    }
}
