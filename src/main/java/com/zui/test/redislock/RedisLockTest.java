package com.zui.test.redislock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zui
 * @date 2019.11.12 21:09
 */
public class RedisLockTest {

    @Autowired
    Redisson redisson;
    @Autowired
    RedisTemplate redisTemplate;

    public void redisTest() {
        String key = "lock_key";
        String uuid = UUID.randomUUID().toString();

        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, uuid, 10, TimeUnit.SECONDS);

        if (!aBoolean) {
            return;
        }

        RLock lock = redisson.getLock(key);
        lock.lock();
        lock.unlock();

        if (uuid.equals(redisTemplate.opsForValue().get(key))) {
            redisTemplate.delete(key);
        }
    }
}
