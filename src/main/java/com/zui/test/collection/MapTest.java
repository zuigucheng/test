package com.zui.test.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zui
 * @date 2019.12.05 8:53
 */
public class MapTest {
    static volatile HashMap<Integer, Integer> map = new HashMap<>(2000);
    static volatile ConcurrentHashMap<Integer, Integer> map1 = new ConcurrentHashMap<>(2000);

    public static void main(String[] args) {
        unsafeTest(1000, map1);
    }


    private static void unsafeTest(int time, Map<Integer, Integer> map) {
        Thread t1 = new Thread(() -> {
            for (Integer i = 1; i < time * 2; i += 2) {
                map.put(i, i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (Integer i = 0; i < time * 2; i += 2) {
                map.put(i, i);
            }
        });

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 2, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2));

        threadPoolExecutor.execute(t1);
        threadPoolExecutor.execute(t2);

        try {
            Thread.sleep(1000);
            System.out.println(map.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (threadPoolExecutor != null) {
                threadPoolExecutor.shutdown();
            }
        }
    }
}
