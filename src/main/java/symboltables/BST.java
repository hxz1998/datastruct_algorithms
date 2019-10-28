package symboltables;

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

    public static void main(String[] args) {
        BST<String, Integer> account = new BST<>();
        account.put("Bob", 123);
        account.put("Alice", 345);
        account.put("Cied", 789);
        account.put("Elyan", 888);
        account.put("Zaker", 999);
        System.out.println(account.get("Bob"));
        account.put("Bob", 666);
        System.out.println(account.get("Bob"));
        System.out.println(account.get("Zaker"));
        System.out.println(account.ceiling("Alice"));
        System.out.println(account.floor("D"));
        System.out.println(account.floor("F"));
    }

}
