package com.nxm.javaInterview.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){

        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" 正在写操作");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+" 写操作完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }


    }

    public  void get(String key){

        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读操作");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成" + result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }

}


/*
    读写分离锁：

        多个线程同时读一个资源类 没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。

        但是如果有一个线程想去写共享资源类，就不应该再有其他线程可以对改资源进行读或者写。

        小总结：
                读-读能共存
                读-写不能共存
                写-写不能共存


                写操作：原子+独占   ：整个过程必须是一个完整的统一体，中间不许被分割，不许被打断。

                ReentrantReadWriteLock : 的写操作 writeLock 严格控制顺序
                                         的读操作 readLock  不严格控制顺序
 */

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 3; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt);
            },"t1").start();
        }

        for (int i = 0; i < 3; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },"t2").start();
        }



    }
}
