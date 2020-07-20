package com.example.httptest;

import java.math.BigInteger;

public class test {

    public static void main(String[] args) {

        BigInteger sum = BigInteger.valueOf(1);
        BigInteger i = BigInteger.valueOf(1);
        while (i.compareTo(BigInteger.valueOf(3))<=0){
            sum = sum.multiply(i);
            i = i.add(BigInteger.valueOf(1));

        }
        System.out.println(sum);
    }
}
