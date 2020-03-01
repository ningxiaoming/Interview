package com.nxm.javaInterview.concurrency;


/*


 */



public class SingletonDemo {

    //加上 volatile 表示不允许重新排序
    private static volatile SingletonDemo singletonDemo = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"   我是构造方法");
    }

    //DCL (Double Check Lock 双端检索机制)
    public static SingletonDemo getInstance(){
        if (singletonDemo==null){
            synchronized (SingletonDemo.class){
                if (singletonDemo==null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {

//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());

        System.out.println("====================");
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
