package com.zui.test.arrays;

import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 简单ArrayList集合
 * 注:强制指定泛型,不允许多类型元素
 *
 * @author zui
 * @date 2019.12.14 16:57
 */
public class SimpleArrayList<E> implements SimpleList<E>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 元素本体
     */
    transient Object[] elementData;

    /**
     * 数组长度
     */
    private volatile int size;

    /**
     * 实际个数
     */
    private int len;

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 默认空数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 最大容量
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 不合法抛异常
     *
     * @param initialCapacity 指定容量
     */
    public SimpleArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("容量错误(需大于0),当前容量: " +
                    initialCapacity);
        }
        this.size = initialCapacity;
    }

    /**
     * 不指定默认为0数组
     */
    public SimpleArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }


    @Override
    public E add(E e) {
        // 保证容量
        ensureCapacityInternal();
        elementData[size++] = e;
        len++;
        return e;
    }

    private void ensureCapacityInternal() {
        int newCapacity;

        /*懒加载策略,如size为0则初始化数组*/
        if (size == 0) {
            initList();

            /*两步判断,如数组已满,则判断扩容后是否小于最大容量,如果是执行扩容*/
        } else if ((elementData.length == size) && (newCapacity = size + (size >> 1)) < MAX_ARRAY_SIZE) {
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void initList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public boolean contains(E e) {
        int temp = elementData.length;

        //循环解析,默认判断对象,如有需要请重写equals和hashCode方法
        while (temp-- != 0) {
            if (elementData[temp] != e) {
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean remove(E e) {
        if (isEmpty()) {
            throw new NoSuchElementException("集合为空");
        }
        removeElement(e);
        len--;
        return true;
    }

    private void removeElement(E e) {
        int temp = elementData.length;

        //循环解析,默认判断对象,如有需要请重写equals和hashCode方法
        while (temp-- != 0) {
            if (elementData[temp] == e) {
                doRemove(temp);
            }
        }
    }

    private void doRemove(int temp) {

        /*将移除位置置为空,并移动元素*/
        elementData[temp] = null;
        for (int i = temp + 1; i < elementData.length; i++) {
            elementData[i - 1] = elementData[i];
        }
    }

    @Override
    public E get(int index) {
        /*按需要做参数校验*/
        return (E) elementData[index];
    }


    public void showString() {
        System.out.print("[");
        for (int i = 0; i < elementData.length; i++) {
            System.out.println(elementData[i] + ",");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        SimpleArrayList<Object> objectSimpleArrayList = new SimpleArrayList<>();
        objectSimpleArrayList.add(1);
        objectSimpleArrayList.add(2);
        objectSimpleArrayList.add(3);
        objectSimpleArrayList.add(4);
        objectSimpleArrayList.add(5);


        int o = (int) objectSimpleArrayList.get(3);
        System.out.println(o);

        objectSimpleArrayList.remove(1);
        objectSimpleArrayList.showString();
    }
}
