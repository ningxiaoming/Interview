package com.proxy.demo1;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//客户租房
public class Client implements InvocationHandler {
    public static void main(String[] args) {
        Host host = new Host();

        Proxy proxy = new Proxy(host);
        proxy.rent();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
