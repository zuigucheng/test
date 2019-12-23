package com.zui.test.arrays;

/**
 * 定义集合行为接口,注:实现部分list方法
 *
 * @author zui
 * @date 2019.12.14 16:52
 */
public interface SimpleList<E> {
    E add(E e);

    boolean isEmpty();

    boolean contains(E e);

    boolean remove(E e);

    E get(int index);
}
