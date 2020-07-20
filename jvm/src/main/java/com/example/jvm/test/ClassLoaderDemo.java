package com.example.jvm.test;

public class ClassLoaderDemo {

    public static void main(String[] args) {

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//sun.misc.Launcher$ExtClassLoader@27c170f0
        System.out.println(systemClassLoader.getParent().getParent());

        System.out.println(ClassLoaderDemo.class.getClassLoader());

        String str  = "123";

        System.out.println();
    }
}
