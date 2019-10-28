/**
 * Created by Xiaozhong on 2019/10/28.
 * Copyright (c) 2019/10/28 Xiaozhong. All rights reserved.
 */
package symboltables;

public class SequentialSearchST<Key, Value> {

    private Node first;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key k, Value v, Node next) {
            this.key = k;
            this.value = v;
            this.next = next;
        }
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Null key!");
        for (Node node = first; node != null; node = node.next)
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        first = new Node(key, value, first);
    }

    public Value get(Key key) {
        // 查找到了相关的节点
        for (Node node = first; node != null; node = node.next) if (node.key.equals(key)) return node.value;
        // 未查找到相关的节点
        return null;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        st.put("Bob", 12);
        st.put("Alice", 13);
        System.out.println(st.get("Bob"));
        st.put("Bob", 111);
        System.out.println(st.get("Bob"));
    }
}
