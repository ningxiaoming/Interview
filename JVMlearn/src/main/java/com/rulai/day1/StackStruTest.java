package com.rulai.day1;

public class StackStruTest {
        static class Father{

            static {
                A = 2;
            }
            public static int A = 1;
        }
        static class Son extends Father{
            public static int B = A;
        }

    public static void main(String[] args) {
        System.out.println(Son.B);
    }
}
