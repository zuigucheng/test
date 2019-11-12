package com.zui.test.sort;

import com.zui.test.util.DataCreated;

/**
 * @author zui
 * @date 2019.11.12 16:25
 */
public class SelectionSort {

    final static int[] data = DataCreated.createData();

    public static void main(String[] args) {
        sort();
    }

    public static void sort() {
        for (int i = 0; i < data.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = data[min];
                data[min] = data[i];
                data[i] = temp;
            }
        }

    }
}
