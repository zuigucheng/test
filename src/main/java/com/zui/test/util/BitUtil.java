package com.zui.test.util;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zui
 * @description 并发压测工具类
 * @date 2019.12.24 14:06
 */
public class BitUtil {

    /**
     *
     * @param threadNum 压测线程数
     * @param testCount 测试次数
     * @param task 测试任务
     * @return 执行时间
     */
    private static long timeTasks(int threadNum,int testCount,Runnable task){
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNum);
        ThreadFactory tf = Executors.defaultThreadFactory();
        final int singleExeNum = testCount == 0 ? 1 : testCount;
        final AtomicLong sum = new AtomicLong();
        final AtomicLong min = new AtomicLong(10000);
        final AtomicLong max = new AtomicLong(0);
        for (int i = 0; i < threadNum; i++) {
            tf.newThread(new Thread(() -> {
                try {
                    startGate.await();
                    for (int j = 0; j < singleExeNum; j++) {
                        long start = System.nanoTime();
                        try {
                            task.run();
                        } finally {
                            long end = System.nanoTime();
                            long at = ((end - start) / 1000 / 1000);
                            sum.addAndGet(at);
                            if (min.get() > at) {
                                min.getAndSet(at);
                            }
                            if (max.get() < at) {
                                max.getAndSet(at);
                            }
                        }
                    }
                    endGate.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            })).start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        try {
            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        long at = ((end - start) / 1000 / 1000);

        int allCount = singleExeNum * threadNum;

        System.out.println("执行任务数：" + allCount);
        System.out.println("------------------------");
        System.out.println("所有线程共耗时：" + transStr(sum.get()));
        System.out.println("并发执行完耗时：" + transStr(at));
        System.out
                .println("单任务平均耗时：" + transStr((double) sum.get() / allCount));
        System.out.println("单线程最小耗时：" + transStr(min.get()));
        System.out.println("单线程最大耗时：" + transStr(max.get()));
        return end - start;
    }

    public static String transStr(long ms) {
        return transStr((double) ms);

    }

    public static String transStr(double ms) {
        if (ms < 1000) {
            return ms + " ms";
        }
        double s = ms / 1000;
        if (s < 1000) {
            return s + " s";
        }
        double m = s / 60;
        if (m < 60) {
            return m + " m";
        }
        double h = m / 60;
        if (h < 24) {
            return h + " h";
        }

        double d = h / 24;
        if (d < 30) {
            return d + " d";
        }
        return d + " d";
    }

}
