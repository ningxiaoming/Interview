package com.nxm.spring_lecture.test;

import java.lang.reflect.Method;

public class MyTest {
    public static void main(String[] args) throws Exception {
        //使用反射调用MyTest2的main方法
        Class<MyTest2> clazz = MyTest2.class;
        Method method = clazz.getDeclaredMethod("main", String[].class);
        //静态方法不是属于某个类的 而是单独存在于静态变量池中的 所以invoke方法的第一个参数随意
        method.invoke(MyTest.class,new Object[]{null});


    }
}
