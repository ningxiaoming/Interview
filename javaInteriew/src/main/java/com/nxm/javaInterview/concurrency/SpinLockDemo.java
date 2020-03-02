package com.nxm.javaInterview.concurrency;



class Person{

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeValue1(int a){
        a = 10;
    }
    public void changeValue2(Person person){
        person.setName("xxx");
    }
    public void changeValue3(String str){
        str = "qqq";
    }
}

public class SpinLockDemo {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("zhangsan");
        int a = 100;
        person.changeValue1(a);
        System.out.println(a);
        person.changeValue2(person);
        System.out.println(person.getName());
        String str = "www";
        person.changeValue3(str);
        System.out.println(str);

    }
}
