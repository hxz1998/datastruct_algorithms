import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> current;

    private int size;

    private class Node<Item> {
        Item value;
        Node<Item> next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = new Node<Item>();
        current = first;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Null args");
        if (size == 0) {
            current.value = item;
        } else {
            Node<Item> node = new Node<>();
            node.value = item;
            current.next = node;
            current = current.next;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty container!");
        int index = StdRandom.uniform(size) + 1;
        Node<Item> node = first;
        Node<Item> prev = first;
        Item value = null;
        if (index == size) {
            if (size > 2) {
                while (node.next.next != null) {
                    node = node.next;
                }
                value = node.next.value;
                node.next = null;
            } else if (size == 2) {
                value = node.next.value;
                node.next = null;
            } else if (size == 1) {
                value = node.value;
                node.value = null;
            }
        } else if (index == 1) {
            value = first.value;
            first = first.next;
        } else {
            while (--index > 1) {
                node = node.next;
            }
            value = node.next.value;
            node.next = node.next.next;
        }
        size--;
        return value;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Empty container!");
        int index = StdRandom.uniform(size) + 1;
        Node<Item> node = first;
        while (--index > 0) {
            node = node.next;
        }
        return node.value;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException("没有该元素。");
            }
            return dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("不支持的操作。");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        for (int i = 0; i < 10; i++) System.out.print(queue.sample() + " ");
        System.out.println();
//        for (int i = 0; i < 5; i++) System.out.print(queue.dequeue() + " ");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next());
    }
}
