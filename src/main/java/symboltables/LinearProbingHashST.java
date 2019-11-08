/**
 * Created by Xiaozhong on 2019/11/5.
 * Copyright (c) 2019/11/5 Xiaozhong. All rights reserved.
 */
package symboltables;

/**
 * 实现了基于线性探测的符号表
 */
public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] values;

    /**
     * 使用默认大小来构造散列表
     */
    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    /**
     * 指定大小构造空的散列表
     */
    public LinearProbingHashST(int size) {
        keys = (Key[]) new Object[size];
        values = (Value[]) new Object[size];
        this.M = size;
    }

    /**
     * 哈希函数，取低31位，然后取模值
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * 重新调整大小
     */
    private void resize(int size) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(size);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                t.put(keys[i], values[i]);
        keys = t.keys;
        values = t.values;
        M = t.M;
    }

    /**
     * 放置一个键值对进去
     */
    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M);
        int j;
        for (j = hash(key); keys[j] != null; j = (j + 1) % M) {
            if (keys[j].equals(key)) values[j] = value;
            return;
        }
        keys[j] = key;
        values[j] = value;
        N++;
    }

    /**
     * 根据键来获取相应的值，如果找不到那么就返回一个空值
     */
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return values[i];
        return null;
    }

    /**
     * 判断是否包含一个指定的键值
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * 根据键来删除一个键值对
     */
    public void delete(Key key) {
        if (!contains(key)) return;
        // 计算理论上的下标索引
        int i = hash(key);
        // 定位实际上的下标索引
        while (!key.equals(keys[i]))
            i = (i + 1) % M;
        // 将其置空，删除
        keys[i] = null;
        values[i] = null;
        // 将之后的元素挨个往前移动，需要重新计算位置，而不是简单的往前挪
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToRedo, valueToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8) resize(M / 2);
    }

    public static void main(String[] args) {
        LinearProbingHashST<Integer, Integer> linearProbingHashST = new LinearProbingHashST<>();
        for (int i = 0; i < 32; i++) {
            linearProbingHashST.put(i, i);
        }
        linearProbingHashST.delete(2);
        linearProbingHashST.delete(18);
        for (int i = 0; i < 32; i++) {
            System.out.println(linearProbingHashST.get(i));
        }
        System.out.printf("\n=====\n%d 的hash为：%x", 2, linearProbingHashST.hash(2));
        System.out.printf("\n=====\n%d 的hash为：%x", 32, linearProbingHashST.hash(32));
        System.out.printf("\n=====\n%d 的hash为：%x", 15, linearProbingHashST.hash(15));
    }
}
