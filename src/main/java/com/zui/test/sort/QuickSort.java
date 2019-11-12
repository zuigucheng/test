package com.zui.test.sort;

import com.zui.test.util.DataCreated;

import java.util.Arrays;

/**
 * @author zui
 * @date 2019.11.12 16:49
 */
public class QuickSort {

    final static int[] data = DataCreated.createData();

    public static void main(String[] args) {
        sort();
    }

    public static void sort() {
        qSort(data, 0, data.length - 1);
    }

    private static void qSort(int[] data, int left, int right) {

        int base = data[left];
        int ll = data[left];
        int rr = data[right];
        int temp;

        while (ll < rr) {
            while (ll < rr && data[rr] >= base) {
                rr--;
            }
            if (ll < rr) {
                temp = data[ll];
                data[ll] = data[rr];
                data[rr] = temp;
                ll++;
            }

            while (ll < rr && data[ll] <= base) {
                rr--;
            }
            if (ll < rr) {
                temp = data[ll];
                data[ll] = data[rr];
                data[rr] = temp;
                rr--;
            }
        }

        if (left < ll) {
            qSort(data, left, ll - 1);
        }
        if (ll < right) {
            qSort(data, ll + 1, right);
        }
    }
}
