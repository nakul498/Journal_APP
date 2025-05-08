package com.educ.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisServiceTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void test(){
        redisTemplate.opsForValue().set("email", "abc@gmail.com");
        Object email = redisTemplate.opsForValue().get("salary");
        int a = 1;
    }
}
