package com.nxm.javaInterview;


/*
    问题：hashcode相等两个类一定相等吗?equals呢?相反呢?

 */
public class HashCode {

    public static void main(String[] args) {
        System.out.println(handleCase(10));
    }

    public static int handleCase(int n){
        try{
            n += 1;
            if(n/0 > 0){
                n+=1;
            } else {
                n -= 10;
            }
            return n;
        } catch(Exception e) {
            e.printStackTrace();
            n++;
        }
        n++;
        return n++;
    }

}
