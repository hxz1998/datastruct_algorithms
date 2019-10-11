import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first, last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = new Node<Item>();
        first.next = null;
        first.prev = null;
        last = first;
    }

    private class Node<Item> {
        Item value;
        Node<Item> next;
        Node<Item> prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Args is illegal!");
        }
        if (size > 0) {
            Node<Item> oldFirst = first;
            first = new Node<>();
            first.value = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        } else if (size == 0) {
            first.value = item;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Args is illegal!");
        }
        if (size > 0) {
            Node<Item> oldLast = last;
            last = new Node<>();
            last.value = item;
            last.prev = oldLast;
            oldLast.next = last;
        } else if (size == 0) {
            last.value = item;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty!");
        }
        Item value = first.value;
        if (size != 1) {
            first = first.next;
            first.prev = null;
        } else {
            first.value = null;
        }
        size--;
        return value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
        Item value = last.value;
        if (size != 1) {
            last = last.prev;
            last.next = null;
        } else {
            last.value = null;
        }
        size--;
        return value;
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null && current.value != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Empty");
            }
            Item value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("不支持的操作！");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
//        Deque<Integer> deque = new Deque<Integer>();
//        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addLast(3);
//        deque.addLast(4);
//        Iterator<Integer> iterator = deque.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }
//        System.out.println();
//        while (!deque.isEmpty()) {
//            System.out.print(deque.removeFirst() + " ");
////            System.out.print(deque.removeLast() + " ");
//        }

        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        System.out.println(deque.removeFirst());
        System.out.println(deque.iterator().hasNext());
        deque.addLast(2);

        Deque<Integer> deque1 = new Deque<>();
        for (int i = 0; i < 10; i++) {
            deque1.addFirst(i);
        }
        Iterator iterator = deque1.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(deque1.removeFirst());
        }

        Deque<Integer> deque2 = new Deque<Integer>();
        deque2.addLast(1);

        Deque<Integer> deque3 = new Deque<>();
        deque3.addLast(1);
        deque3.addLast(2);
        System.out.println(deque3.removeFirst());
        System.out.println(deque3.removeFirst());
        Iterator iterator1 = deque3.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }

}
