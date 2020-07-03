package com.redis;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Test {

    public static void main(String[] args) {
        Timer timer = new Timer();
        int i = 0;
        float num = 10;
        float oo = num/(int)0;
        System.out.println(oo);
//        while (i<10000) {
//            timer(timer);
            i++;
//        }
//        timer.cancel();
//        System.out.println("start");
    }

    public static void timer(Timer timer1){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"run");
                timer.cancel();
            }
        }, 1000,-1);
    }
}
