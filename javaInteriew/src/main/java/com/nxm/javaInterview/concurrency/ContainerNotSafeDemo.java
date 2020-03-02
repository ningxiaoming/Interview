package com.nxm.javaInterview.concurrency;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/*
    集合类不安全问题  ----->  因为 add() 方法为了效率没有加锁

    new HashSet<>().add("1");---->   HashSet底层由HashMap实现---->add方法底层是put 将add的值作为 put的key value值 是一个常量。

    故障现象：
        java.util.ConcurrentModificationException
    导致原因：


    解决方案：
        1。new Vector<>();
        2。Collections.synchronizedList(new ArrayList<>());
        3。new CopyOnWriteArrayList<>();  ：写时复制
        CopyOnWrite容器即写时复制。往一个容器添加元素的时候，不直接往当前容器 Object[] 添加，而是将当前容器 Object[] 进行copy
        ，复制出一个新的容器 Object[] newElements，然后往新的容器 Object[] newElements 里添加元素，添加完元素之后，再将原容器
        的引用指向新的容器 setArray(newElements)；。这样做的好处是可以将CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器
        不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写的操作不是同一个容器。
      public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }


 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }

    private static void setNotSafe() {
        Set<String> set = new HashSet<>();
        Set<String> set1 = Collections.synchronizedSet(new HashSet<>());
        CopyOnWriteArraySet<Object> set2 = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add("xxx");
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new ArrayList<>();//不安全
        List<String> list1 = new Vector<>();//方案一 安全
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());//方案二  安全
        List<String> list3 = new CopyOnWriteArrayList<>();//方案三


        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add("xxx");
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
