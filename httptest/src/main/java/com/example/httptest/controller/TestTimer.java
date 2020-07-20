package com.example.httptest.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

    public static void main(String[] args) {

        System.out.println(show(3));
    }


    //查询第100001个质数
/*    public static long show(Integer index){
        ArrayList<Long> list = new ArrayList<>();
        list.add(2l);
        long i = 3;

        while (list.size()<index){
            boolean flag = true;
            for (long j = 3;j<i/2;j+=2){
                if (i%j == 0){
                    flag = false;
                    break;
                }
            }
            if (flag){
                list.add(i);
            }
            i+=2;
        }

        return list.get(list.size()-1);
    }*/

    public static long show(long index){
        ArrayList<Long> list = new ArrayList<>();
        list.add(2l);
        long i = 3;
        while (list.size()<index){
            boolean flag = true;
            for (long j = 3 ; j<=i/2 ; j+=2){
                if (i%j==0){
                    flag = false;
                    break;
                }
            }
            if (flag){
                list.add(i);
            }
            i+=2;
        }
        return list.get(list.size()-1);
    }
}
