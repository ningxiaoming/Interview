package com.nxm.javaInterview.concurrency;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class User{
    String userName;
    int age;
    public User(String userName,int age){
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}


public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User zs = new User("zs", 18);
        User ls = new User("ls", 20);

        AtomicReference<User> atomicReference = new AtomicReference<>();

        atomicReference.set(zs);

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(zs, ls)+" 当前AtomicReference中的对象是1："+atomicReference.get());
            System.out.println(atomicReference.compareAndSet(ls, zs)+" 当前AtomicReference中的对象是2："+atomicReference.get());

        },"th").start();
        System.out.println(atomicReference.compareAndSet(zs, ls)+" 当前AtomicReference中的对象是3："+atomicReference.get());


    }

}
