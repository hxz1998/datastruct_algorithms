import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class Node<Item> {
        Item value;
        Node<Item> next;
        Node<Item> prev;
    }

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        first = new Node<>();
        first.next = null;
        first.prev = null;
        last = first;
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
        if (item == null) throw new IllegalArgumentException("Illegal args");
        if (size == 0) {
            last.value = item;
        } else {
            Node<Item> oldLast = last;
            last = new Node<>();
            last.value = item;
            last.prev = oldLast;
            oldLast.next = last;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty");
        int index = StdRandom.uniform(size) + 1;
        Item value = null;
        if (size == 1) {
            value = first.value;
            first.value = null;
            first.next = null;
            first.prev = null;
            last = first;
        } else {
            if (index == 1) {
                value = first.value;
                first.value = null;
                first = first.next;
                first.prev = null;
            } else if (index == size) {
                value = last.value;
                last.value = null;
                last = last.prev;
                last.next = null;
            } else {
                Node<Item> current = first;
                while (--index > 0) {
                    current = current.next;
                }
                value = current.value;
                current.prev.next = current.next;
            }
        }
        size--;
        return value;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Empty");
        if (size == 1) {
            return first.value;
        } else {
            int index = StdRandom.uniform(size) + 1;
            if (index == size) {
                return last.value;
            } else {
                Node<Item> current = first;
                while (--index != 0) {
                    current = current.next;
                }
                return current.value;
            }
        }
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
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(i);
        }
        while (!rq.isEmpty()) {
            System.out.println(rq.size() + " " + rq.dequeue() + " ");
        }
        System.out.println();
        Iterator<Integer> iterator = rq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
