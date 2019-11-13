package com.zui.test.lock;

import java.util.concurrent.*;

/**
 * @author zui
 * @date 2019.11.13 7:44
 */
public class LockByAqs {

    public static final int TASK_COUNT = 100;


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < TASK_COUNT; i++) {
            int finalI = i;
            executorService.execute(() -> System.out.println(10+","+ finalI));
        }
        executorService.shutdown();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.execute(()-> System.out.println(1));
        threadPoolExecutor.shutdown();
    }
}
