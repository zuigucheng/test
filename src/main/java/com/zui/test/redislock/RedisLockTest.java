package com.zui.test.redislock;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

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
        String uuid = "";
        try {
            uuid = UUID.randomUUID().toString();

            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, uuid, 10, TimeUnit.SECONDS);

            if (!aBoolean) {
                //没有设置锁成功,可以实现拒绝策略,阻塞等处理方案
                return;
            }
        } finally {
            if (uuid.equals(redisTemplate.opsForValue().get(key))) {
                redisTemplate.delete(key);
            }
        }

        //redisson实现分布式锁
        RLock lock = redisson.getLock(key);
        lock.lock();
        lock.unlock();


    }
}
