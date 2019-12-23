package com.zui.test.task;

import com.zui.test.bean.User;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author zui
 * @date 2019.12.13 10:08
 */
public class Demo {

    /**
     * 保存分割后的任务
     */
    private static Map<Integer, List<String[]>> map = new HashMap<>();


    /**
     * 使用线程安全的集合或者ThreadLocal
     */
    private static Map<Integer, Set<Integer>> resultMap = new ConcurrentHashMap<>(20);

    /**
     * 方法一:双层嵌套循环:此方式适合小数据量,可避免过多繁杂操作,这种方式不做描述
     * 方法二:任务分割,此方式适合大数据量情况,可利用cpu工作最大效率
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        //1.查询所有记录,只查询id与账号
        //2.分割账号为char数组,长度相同的为一组数据
        //3.开启线程池,每组任务按位比较

        /*创建测试数据*/
        List<User> objects = new ArrayList<>();
        objects.add(new User(1, "23332"));
        objects.add(new User(2, "2332"));
        objects.add(new User(3, "23332"));
        objects.add(new User(4, "2332"));
        objects.add(new User(5, "23332"));
        objects.add(new User(6, "23332"));
        objects.add(new User(7, "33321"));
        objects.add(new User(8, "33321"));

        /*遍历字符串做任务处理,时间复杂度O(n)*/
        for (User object : objects) {

            //获取id创建数组
            String[] chars = object.getName().split("");

            /*创建新数组,新数组长度为length+1,最后一位保存id*/
            String[] chars1 = new String[chars.length + 1];
            chars1[chars1.length - 1] = String.valueOf(object.getId());
            System.arraycopy(chars, 0, chars1, 0, chars.length);

            List<String[]> objects1;

            //map键如果已存在则使用原有的,如果未存在就创建
            objects1 = (objects1 = map.get(chars1.length)) != null ? objects1 : new ArrayList<>();

            //为任务添加数据
            objects1.add(chars1);

            //重组织任务
            map.put(chars1.length, objects1);
        }

        handler();
    }

    private static void handler() throws InterruptedException {
        /*计算密集型任务,开启线程数为CPU核数+1*/
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L
                , TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadFactory() {
            int counter = 1;

            @Override
            public Thread newThread(Runnable runnable) {
                Thread t = new Thread(runnable, counter++ + "-Thread_" + counter);
                return t;
            }
        });


        //使用"门栓"(作用:等待所有线程到达释放点才向下执行)
        CountDownLatch countDownLatch = new CountDownLatch(map.size());
        try {

            /*遍历任务处理*/
            Set<Integer> integers = map.keySet();
            for (Integer integer : integers) {
                List<String[]> chars = map.get(integer);

                /*线程池提价任务,任务为内部Runnable*/
                threadPoolExecutor.execute(threadPoolExecutor.getThreadFactory().newThread(() -> {

                    /*处理方式类似String的重写equals()逻辑*/
                    int i = chars.size() - 1;
                    for (; i > 0; ) {

                        String[] v1 = chars.get(i);
                        String[] v2 = chars.get(--i);

                        int i1 = v1.length - 1;
                        while (i1-- != 0) {
                            if (!v1[i1].equals(v2[i1])) {
                                break;
                            }
                        }

                        //使用set保存,利用set不可重复的天然特性
                        Set<Integer> objects1;

                        //map键如果已存在则使用原有的,如果未存在就创建
                        objects1 = (objects1 = resultMap.get(v1.length)) != null ? objects1 : new LinkedHashSet<>();

                        //为任务添加数据
                        objects1.add(Integer.parseInt(v1[v1.length - 1]));
                        objects1.add(Integer.parseInt(v2[v1.length - 1]));

                        //重组织任务
                        resultMap.put(v1.length, objects1);
                    }

                    //释放计数
                    countDownLatch.countDown();
                }));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (threadPoolExecutor != null) {
                //关闭线程池
                threadPoolExecutor.shutdown();
                countDownLatch.await();
                System.out.println(resultMap);
            }
        }
    }
}
