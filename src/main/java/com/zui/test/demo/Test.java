package com.zui.test.demo;

/**
 * @author zui
 * @date 2019.12.05 19:55
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Test2.a);
//        Thread.sleep(1000);
//        System.out.println(Test2.num);
    }
}

class Test2 {
    public static final String a = "JD";
    public static final Double num = Math.random();

    static {
        System.out.print("OK");
    }
}
