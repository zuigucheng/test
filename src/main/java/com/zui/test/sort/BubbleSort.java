package com.zui.test.sort;

import com.zui.test.util.DataCreated;

/**
 * @author zui
 * @date 2019.11.12 16:38
 */
public class BubbleSort {

    final static int[] data = DataCreated.createData();

    public static void main(String[] args) {
        sort();
    }

    public static void sort(){
        for (int i = 0; i < data.length; i++) {
            boolean flag = false;
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
            if (flag) {
                break;
            }
        }

    }
}
