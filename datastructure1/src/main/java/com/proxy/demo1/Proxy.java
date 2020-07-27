package com.proxy.demo1;

import com.proxy.demo3.Rent;

public class Proxy implements Rent {

    private Host host;

    public Proxy(){}
    public Proxy(Host host){
        this.host = host;
    }


    @Override
    public void rent() {
        host.rent();
        seeHost();
    }

    @Override
    public void rent1() {

    }

    public void seeHost(){
        System.out.println("客户看房");
    }

}
