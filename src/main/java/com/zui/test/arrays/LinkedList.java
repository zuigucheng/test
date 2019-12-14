package com.zui.test.arrays;

/**
 *
 * 单向链表简单实现
 * @author zui
 * @date 2019.12.14 18:02
 */
public class LinkedList<E> implements SimpleList<E>{

    /**
     *
     * 头结点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    /**
     * 为方便操作,记录长度
     */
    private int size;

    /**
     * 添加逻辑,如果首尾为空则首尾都等于此节点,如果首尾不为空则将其加入最后节点并重置尾结点
     * @param e
     * @return
     */
    @Override
    public E add(E e){
        Node<E> eNode = new Node<>(e);
        if (this.head == null){
            this.head = eNode;
        }else {
            this.tail.next = eNode;
        }
        this.tail = eNode;
        this.size++;
        return e;
    }

    /**
     * 指定位置添加
     * @param e
     * @param index
     */
    public void add(int index,E e){
        Node<E> node = new Node<>(e);

        /*依据长度与后继结点找到插入位置*/
        Node temp = this.head;
        while (index-- != 0){
            temp = temp.next;
        }

        /*首先应将新加入节点的后继结点置为原来节点的后继结点,然后在将原节点后继结点置为新结点.*/
        node.next = temp.next;
        temp.next = node.next;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(E e) {
        Node temp = this.head;
        while (temp.next != null){
            if (temp.e == e){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        Node temp = this.head;
        while (temp.next != null){

            /*不能将temp直接置为删除位置,因为未设置前置节点*/
            if (temp.next.e == e){
                temp.next = temp.next.next;
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        Node temp = this.head;
        while (index-- != 0){
            temp = temp.next;
        }
        return (E) temp.e;
    }
}

/**
 * 节点
 * @param <E> 数据泛型
 */
class Node<E>{

    public Node(E e) {
        this.e = e;
    }

    /**
     * 后置节点
     */
    Node next;

    /**
     * 前置节点
     */
    Node pre;

    /**
     * 实际元素
     */
    E e;
}
