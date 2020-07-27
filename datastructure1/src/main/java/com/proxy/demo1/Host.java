package com.proxy.demo1;


import com.proxy.demo3.Rent;

//房东
public class Host implements Rent {

    public void rent(){
        System.out.println("房东租房");
    }

    public void rent1(){
        System.out.println("房东租房qqqq");
    }

}
