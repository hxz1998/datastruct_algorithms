/**
 * Created by Xiaozhong on 2018/12/9.
 * Copyright (c) 2018/12/9 Xiaozhong. All rights reserved.
 */
package cn.happyzhong;

public class LinkedList<E> implements List {

    private Node<E> head = null;
    private Node<E> last = null;
    private int length = 0;

    public LinkedList() {
        head = new Node<>(null, null);
        last = head;
    }

    /**
     * 单链表私有类，辅助实现单链表。
     *
     * @param <E> 数据域数据类型
     */
    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(Object o) {
        last.next = new Node<E>((E) o, null);
        last = last.next;
        this.length++;
    }

    @Override
    public void remove(Object o) {
        if (contains(o)) {
            Node node = head;
            int index = get(o);
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            this.length--;
        }
    }

    @Override
    public E get(int index) {
        if (index + 1 > length) {
            return null;
        } else {
            Node node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return (E) node.item;
        }
    }

    @Override
    public int get(Object o) {
        Node node = head.next;
        for (int i = 0; i < length; i++) {
            if (node.item.equals(o)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return get(o) != -1;
    }

    @Override
    public boolean isEmpty() {
        return getLength() == 0;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public void singleCase() {
        for (int i = 0; i < this.getLength(); i++) {
            for (int j = i + 1; j < this.getLength(); j++) {
                if (get(i).equals(get(j))) {
                    remove(get(j));
                    j--;
                }
            }
        }
    }
}
