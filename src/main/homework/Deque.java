import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first, last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = new Node<Item>();
        last = first;
    }

    private static class Node<Item> {
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
        if (size != 0) {
            Node<Item> oldFirst = first;
            first = new Node<>();
            first.value = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        } else {
            first.value = item;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Args is illegal!");
        }
        if (size != 0) {
            Node<Item> oldLast = last;
            last = new Node<>();
            last.value = item;
            last.prev = oldLast;
            oldLast.next = last;
        } else {
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
        }
        size--;
        return value;
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
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

        Deque<Integer>  deque= new Deque<Integer>();
        deque.addLast(1);
        System.out.println(deque.removeFirst());

    }

}
