package com.proxy.demo3;

public class Client {
    public static void main(String[] args) {
        Host host = new Host();

        Qqq qqq = new Qqq();

        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(qqq);

        Qq q = (Qq)pih.getProxy();
//        Rent proxy = (Rent)pih.getProxy();
//        proxy.rent1();

        q.show();




    }
}
