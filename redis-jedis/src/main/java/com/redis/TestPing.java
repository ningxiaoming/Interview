package com.redis;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.60.190",6379);
        System.out.println(jedis.ping());
        System.out.println(jedis.randomKey());

//        jedis.

    }
}
