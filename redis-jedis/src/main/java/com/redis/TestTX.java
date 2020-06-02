package com.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.60.190",6379);

        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","word");
        jsonObject.put("name","rulai");

        Transaction multi = jedis.multi();
        String s = jsonObject.toJSONString();

//        multi.watch()
        try {
            multi.set("user1",s);
            multi.set("user2",s);
            int i = 1/0;
            multi.exec();
        } catch (Exception e) {
            //放弃事务
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }



    }
}
