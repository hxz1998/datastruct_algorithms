package symboltables;


import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class BST<Key extends Comparable<Key>, Value> {

    // 定义整个二叉树的根节点
    private Node root;

    // 定义私有类
    private class Node {
        private Value value;        // 值
        private Key key;            // 键
        private Node left, right;   // 左右两个子节点
        private int N;              // 当前节点下（包括当前节点）的节点数目

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    /**
     * 查询整个二叉树的大小
     */
    public int size() {
        return size(root);
    }

    /**
     * 查询某一个节点的大小
     */
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     * 在二叉树中查找某一个节点，如果不存在那么返回null，否则返回相应的值
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key) {
        // 以root为根节点进行查找并返回key所对应的值
        // 如果未命中则返回null
        if (null == root) return null;
        int cmp = key.compareTo(root.key);
        // 如果相同，那么直接返回对应的值
        if (cmp == 0) return root.value;
            // 如果当前key大于了根节点，那么去右边子树中查找
        else if (cmp > 0) return get(root.right, key);
            // 如果当前key小于了根节点，那么去左边子树中查找
        else return get(root.left, key);
    }

    /**
     * 更新或者插入新条目
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        // 如果根节点为空的，那么就直接创建一个节点，并且初始化相应的值
        if (root == null) return new Node(key, value, 1);
        int cmp = key.compareTo(root.key);
        // 如果找到了一个相同的节点，那么对节点的值进行更新
        if (cmp == 0) root.value = value;
            // 如果当前的key值小于了根节点，那么将会插入到根节点的左边去，所以递归调用，否则插入到右边子树去
        else if (cmp < 0) root.left = put(root.left, key, value);
        else root.right = put(root.right, key, value);
        // 更新N值，左边大小 + 右边大小 + 自己
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    /**
     * 找到最小的键
     */
    public Key min() {
        return min(root).key;
    }

    private Node min(Node root) {
        // 如果根节点左边的键为空，那么直接就是根节点。否则进行递归调用遍历左子树找到最小的。
        if (root.left == null) return root;
        return min(root.left);
    }

    /**
     * 找到最大的键
     */
    public Key max() {
        return max(root).key;
    }

    private Node max(Node root) {
        // 如果根节点右边的键为空，那么直接就是根节点，否则进行递归调用遍历右子树查找最大的。
        if (root.right == null) return root;
        return max(root.right);
    }

    /**
     * 查找小于等于指定key值的键
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node root, Key key) {
        // 如果根节点就是空的，那么直接返回null，表示啥也没找到
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        // 如果相同了，那么直接返回root，获取到root所带有的值
        if (cmp == 0) return root;
            // 否则进行递归调用，检查左边是否存在符合要求的值
        else if (cmp < 0) return floor(root.left, key);
            // 如果左边不存在需要的值，那么肯定在根节点或者右边子树
        else {
            // 对右边子树进行检查，如果没找到那么就直接返回根节点
            Node t = floor(root.right, key);
            if (t != null) return t;
            else return root;
        }
    }

    /**
     * 查找大于等于key值的键
     */
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    /**
     * 原理同floor函数
     */
    private Node ceiling(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return root;
        else if (cmp > 0) return ceiling(root.right, key);
        else {
            Node t = ceiling(root.left, key);
            if (t != null) return t;
            else return root;
        }
    }

    /**
     * 选择函数，选择在树中排名为k的键值
     */
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node root, int k) {
        // 如果根节点为空，那么就直接返回空值
        if (root == null) return null;
        // 记录根节点的左子树大小
        int t = size(root.left);
        // 如果左子树大于了k，那么所要找的节点肯定在左子树中，否则就在右子树中
        if (t > k) return select(root.left, k);
            // 对于右子树的处理，需要减掉左子树的排名
        else if (t < k) return select(root.right, k - t - 1);
            // 当相等的时候，那么表示找到了相关的节点键值
        else return root;
    }

    /**
     * 查找某个键值在整个树中的排名
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node root, Key key) {
        // 如果根是空的，那么直接返回0，树是一棵空树
        if (root == null) return 0;
        int cmp = key.compareTo(root.key);
        // 如果查找的键值小于了根值，那么排名肯定在左子树中，否则就在右子树中
        if (cmp < 0) return rank(root.left, key);
            // 对右子树的查找比较特殊，首先加上根节点和左子树的大小，然后再使用右子树作为根节点进行计数
        else if (cmp > 0) return 1 + size(root.left) + rank(root.right, key);
            // 如果找到了，那么直接返回左子树的大小
        else return size(root.left);
    }

    /**
     * 删除最小的节点
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node root) {
        // 如果左边的半个树是空的，那么就直接使用右子树作为根节点
        if (root.left == null) return root.right;
        // 否则要删除的最小节点肯定在根节点的左半部分，递归调用进行处理即可
        root.left = deleteMin(root.left);
        // 更新节点计数器
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    /**
     * 删除掉指定的键值
     * 比较难！
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        // 如果待删除的键值小于了根节点，那么就递归调用左子树进行删除，否则进行右子树调用
        if (cmp < 0) root.left = delete(root.left, key);
        else if (cmp > 0) root.right = delete(root.right, key);
        /*
        如果相同了，那么很好，对当前节点进行以下处理：
        1. 检查左右子树是否为空，如果为空，那么直接返回非空的那个节点进行替代本节点
        2. 否则：
            1. 保存当前的根节点为 t
            2. 将root设置为t的后继节点（就是在正常顺序中排在t的后面一个节点）
            3. 将root的右子树设置为删除掉最小值（后继节点）之后的子树，因为最小节点已经被用了
            4. 将root的左子树设置为t的左子树
         */
        else {
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            Node t = root;
            root = min(t.right);
            root.right = deleteMin(t.right);
            root.left = t.left;
        }
        // 更新节点计数器
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    /**
     * 中序打印所有的节点
     */
    private void print(Node root) {
        if (root == null) return;
        print(root.left);
        System.out.print(root.key + " ");
        print(root.right);
    }

    /**
     * 获取所有的节点，使用中序排列
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * 获取范围为[lo..hi]的节点，中序排列
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node root, Queue<Key> queue, Key lo, Key hi) {
        if (root == null) return;
        int cmplo = lo.compareTo(root.key);
        int cmphi = hi.compareTo(root.key);
        if (cmplo < 0) keys(root.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(root.key);
        if (cmphi > 0) keys(root.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        BST<String, Integer> account = new BST<>();
        account.put("B", 123);
        account.put("A", 345);
        account.put("C", 789);
        account.put("E", 888);
        account.put("Z", 999);
        System.out.println(account.get("B"));
        account.put("B", 666);
        System.out.println(account.get("B"));
        System.out.println(account.get("Z"));
        System.out.println(account.ceiling("A"));
        System.out.println(account.floor("D"));
        System.out.println(account.floor("F"));

        Iterable<String> iterable = account.keys("B", "E");
        Iterator iterator = iterable.iterator();
        System.out.println();
        while(iterator.hasNext()) {
            System.out.print(iterator.next() + "---");
        }
        System.out.println();
        account.print(account.root);
        System.out.println(account.rank("C"));

        account.delete("B");
        System.out.println(account.select(1));

    }

}
