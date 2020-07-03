package com.example.springamqp.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Test {

    public static void main(String[] args) {

        File file = new File("D:\\testtt.txt");
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append("'").append(s).append("'").append(",");
//                result.append(s).append(",");
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(result.toString());
    }
}
