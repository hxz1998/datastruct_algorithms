/**
 * Created by Xiaozhong on 2018/12/9.
 * Copyright (c) 2018/12/9 Xiaozhong. All rights reserved.
 */
package cn.happyzhong;

public class ArrayList<E> implements List {

    /**
     * 数据域
     */
    protected Object[] data = null;

    /**
     * 默认数组大小
     */
    private static final int defaultSize = 8;

    /**
     * 指示当前容器装了多少个元素
     */
    private int length = 0;

    /**
     * 容器容量大小
     */
    private int capacity = 0;

    public ArrayList() {
        data = new Object[defaultSize];
        capacity = defaultSize;
    }

    public ArrayList(int size) {
        if (size > 0) {
            data = new Object[size];
            capacity = size;
        } else {
            data = new Object[defaultSize];
            capacity = defaultSize;
        }
    }

    @Override
    public void add(Object e) {
        if (ensureCapacity()) {
            data[length++] = e;
        } else {
            capacity = capacity + defaultSize;
            Object[] temp = new Object[capacity];
            for (int i = 0; i < length; i++) {
                temp[i] = data[i];
            }
            data = temp;
            data[length++] = e;
            temp = null;
        }
    }

    @Override
    public void remove(Object o) {
        int index = get(o);
        if (index != -1) {
            for (int i = index; i < length - 1; i++) {
                data[i] = data[i + 1];
            }
            length--;
        }
    }

    @Override
    public E get(int index) {
        return (E) data[index];
    }

    @Override
    public int get(Object o) {
        if (contains(o)) {
            for (int i = 0; i < length; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < length; i++) {
            if (o.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void singleCase() {
        for (int i = 0; i < getLength(); i++) {
            for (int j = i + 1; j < getLength(); j++) {
                if (get(i).equals(get(j))) {
                    remove(get(j));
                    j--;
                }
            }
        }
    }

    /**
     * 辅助方法，确认空间容量够用
     *
     * @return 是否空间容量够用
     */
    private boolean ensureCapacity() {
        return getLength() != capacity;
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        content.append("ArrayList{data={");
        for (int i = 0; i < getLength(); i++) {
            content.append(data[i] + ", ");
        }
        content.append("}}");
        return content.toString();
    }

    /**
     * 专门服务于元素为整型数据类型的方法，根据标志数据来分割列表
     *
     * @param flag 标志数据
     * @return 根据分割情况所构成的两个数组，0->大于标志数据的对象集合，1->小于标志数据的对象集合
     */
    public ArrayList[] split(int flag) {
        ArrayList[] result = {new ArrayList(), new ArrayList()};
        for (int i = 0; i < getLength(); i++) {
            if (Integer.parseInt(data[i].toString()) > 0) {
                result[0].add(data[i]);
            } else {
                result[1].add(data[i]);
            }
        }
        return result;
    }
}
