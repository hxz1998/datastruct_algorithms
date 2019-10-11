import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] container;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        container = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < this.size; i++) {
            copy[i] = container[i];
        }
        container = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Illegal args");
        if (size == container.length) resize(2 * size);
        container[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty");
        int index = StdRandom.uniform(size);
        Item value = container[index];
        container[index] = container[size - 1];
        container[size - 1] = null;
        size--;
        if (size > 0 && size == container.length / 4) resize(container.length / 2);
        return value;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Empty");
        int index = StdRandom.uniform(size);
        return container[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] list;
        private int maxIndex;

        public RandomizedQueueIterator() {
            maxIndex = size;
            list = new int[size];
            for (int i = 0; i < size; i++) {
                list[i] = i;
            }
            StdRandom.shuffle(list);
        }

        @Override
        public boolean hasNext() {
            return maxIndex > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("没有该元素。");
            }
            int select = list[--maxIndex];
            return container[select];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("不支持的操作。");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(579);
        rq.enqueue(987);
        rq.enqueue(453);
        rq.enqueue(541);
        rq.enqueue(830);
        rq.enqueue(753);
        rq.enqueue(280);
        rq.enqueue(219);
        rq.enqueue(8);
        rq.enqueue(63);
        rq.enqueue(791);
        Iterator<Integer> iterator = rq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
