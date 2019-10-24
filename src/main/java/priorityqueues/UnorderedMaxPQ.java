package priorityqueues;

/**
 * Unordered priority queue implementation by array.
 *
 * @param <Key> 需要使用的对象类型
 */
public class UnorderedMaxPQ<Key extends Comparable<Key>> {

    private Key[] keys;
    private int size;

    public UnorderedMaxPQ(int capacity) {
        size = 0;
        keys = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Key x) {
        keys[size++] = x;
    }

    public Key delMax() {
        if (isEmpty()) return null;
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (less(max, i)) max = i;
        }
        exch(max, size - 1);
        return keys[--size];
    }

    private boolean less(int a, int b) {
        return keys[a].compareTo(keys[b]) < 0;
    }

    private void exch( int i, int j) {
        Key temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }

    public static void main(String[] args) {
        UnorderedMaxPQ<String> pq = new UnorderedMaxPQ<String>(10);
        pq.insert("B");
        pq.insert("C");
        pq.insert("A");
        pq.insert("Z");
        System.out.println(pq.delMax());
        while (!pq.isEmpty()) {
            System.out.print(pq.delMax() + " ");
        }
    }
}
