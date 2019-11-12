package com.zui.test.sort;

/**
 * @author zui
 * @date 2019.11.12 16:43
 */
public class SpeedComparison {

    private static final int cycle = 1000000;

    public static void main(String[] args) {

        System.out.println("===============100数据量排序1000000次================");

        long l = System.currentTimeMillis();
        for (int i = 0; i <= cycle; i++) {
            BubbleSort.sort();
        }
        long l1 = System.currentTimeMillis();
        System.out.println("冒泡排序 = " + (l1 - l));

        System.out.println("===============================");

        long l3 = System.currentTimeMillis();
        for (int i = 0; i <= cycle; i++) {
            SelectionSort.sort();
        }
        long l4 = System.currentTimeMillis();
        System.out.println("选择排序 = " + (l4 - l3));

        System.out.println("===============================");

        long l5 = System.currentTimeMillis();
        for (int i = 0; i <= cycle; i++) {
            QuickSort.sort();
        }
        long l6 = System.currentTimeMillis();
        System.out.println("快速排序 = " + (l6 - l5));
    }
}
