package com.team.gaoguangjin.javabase.collection.list;

import java.util.*;


/**
 * @author:gaoguangjin
 * @date 2017/6/28 14:48
 */
public class MyArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    private int defaultSize = 10;
    private Object[] array;
    private int capacity;
    private int size;


    private static final long serialVersionUID = -7516220118806916627L;

    @Override
    public E get(int index) {
        if (index > size-1) {
            throw new IndexOutOfBoundsException("数据越界");
        }
        return (E) array[index];
    }

    @Override
    public int size() {
        return size;
    }

    public MyArrayList(int capabilities) {
        initSize(capabilities);
    }

    private void initSize(int capabilities) {
        array = (E[]) new Object[capabilities];
        capacity = capabilities;
    }

    public MyArrayList() {
        initSize(defaultSize);
    }

    private void checkSize(Object[] array) {
        int nowLength = array.length;
        if (nowLength + 1 >= capacity) {
            int newCapacity = capacity + nowLength * 2;
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    @Override
    public E remove(int index) {
        return super.remove(index);
    }

    public static void main(String[] args) {
        List<String> list = new MyArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println("args = [" + list.size() + "]"+";"+list.get(2));
    }

    @Override
    public boolean add(E e) {
        checkSize(array);
        array[size] = e;
        size++;
        return true;
    }

}
