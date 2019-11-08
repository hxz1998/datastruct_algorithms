/**
 * Created by Xiaozhong on 2019/11/4.
 * Copyright (c) 2019/11/4 Xiaozhong. All rights reserved.
 */
package symboltables;

/**
 * 使用基本的连续查找符号表来实现的拉链散列表
 */
public class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    /**
     * 构建默认大小位997的散列表
     */
    public SeparateChainingHashST() {
        this(997);
    }

    /**
     * 提供一个大小，然后根据这个大小来构建相应大小的散列表
     */
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<>();
    }

    /**
     * 重要的计算哈希值的方法，通过取得31位有效位数进行除留余数法运算
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * 根据key值进行哈希值计算，然后获取到数组下标后再到相应的符号表中进行查找
     */
    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    /**
     * 将一个键值对放置到散列表中
     */
    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }
}
