package priorityqueues;

import java.security.Key;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] keys;
    private int size;

    public MaxPQ(int capacity) {
        keys = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Key x) {
        keys[++size] = x;
        swim(size);
    }

    /**
     * 删除最大的元素，在MaxPQ中使用完全树表示的时候也就是Root节点
     *
     * @return 要删除的节点
     */
    public Key delMax() {
        if (isEmpty()) return null;
        Key value = keys[1];
        exch(1, size--);
        sink(1);
        keys[size + 1] = null;
        return value;
    }

    /**
     * 上浮指定下标的数值
     *
     * @param k 指定的下标，在这里总是指向子节点
     */
    private void swim(int k) {
        // 因为总是指向子节点，所以需要满足＞1和父节点小于子节点时再进行交换和上浮
        while (k > 1 && less(k/2 , k)){
            exch(k, k / 2);
            k /= 2;// 切换为父节点
        }
    }

    /**
     * 下沉指定下标的数值
     *
     * @param k 指定的下标，在这里总是指向父节点
     */
    private void sink(int k) {
        // 因为指向了父节点，所以其子节点应该小于 2 * k ≤ size
        while (2 * k <= size) {
            int j = 2 * k;  // 找到与k相关的两个子节点下标
            if (j < size && less(j, j + 1)) j++;    // 如果j没有越界，并且两个子节点正序排列，那么j自增
            if (less(j, k)) break;  // 如果子节点小于了父节点，那么跳出循环
            exch(j, k); // 如果不满足上述条件，那么进行下沉操作
            k = j;  // 检查下一个节点（子节点）是否正常
        }
    }

    private boolean less(int i, int j) {
        return keys[i].compareTo(keys[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = keys[i];
        keys[i] = keys[j];
        keys[j] = t;
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>(10);
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
