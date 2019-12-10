package com.zui.test.arrays;

/**
 * @author zui
 * @date 2019.12.09 20:02
 */
public class IntArray {

    int[] datas;
    int size;
    int len;
    static final int DEFAULT_LOAD_FACTOR = 2;


    public IntArray(int size) {
        this.size = size;
        this.datas = new int[size];
        this.len = 0;
    }

    public void insertArray(int data) {
        if (len == size - 1) {
            reSize();
        }
        datas[len++] = data;
    }

    public void delArray(int loc) {
        datas[loc] = 0;
        for (int i = loc + 1; i < datas.length; i++) {
            datas[i - 1] = datas[i];
        }
        len--;

        //缩容
    }

    public void updateArray(int data, int loc) {
        datas[loc] = data;
    }

    private void reSize() {

        int[] temp = new int[size * DEFAULT_LOAD_FACTOR];


        for (int i = 0; i < this.datas.length; i++) {
            temp[i] = datas[i];
        }
        this.datas = temp;
    }
}
