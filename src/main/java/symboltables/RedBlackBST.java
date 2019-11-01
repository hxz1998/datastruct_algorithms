/**
 * Created by Xiaozhong on 2019/10/31.
 * Copyright (c) 2019/10/31 Xiaozhong. All rights reserved.
 */
package symboltables;

/**
 * 红黑树的实现
 * 平衡查找树中的典范，该结构的优势在于没有2-3查找树的复杂，却有极高的平衡性和查找效率
 * <p>
 * 在该程序中，实现的是红黑树，其基本操作和基础Binary-Search-Tree相类似，例如get/floor/iteration/selection方法都是相同的。
 * 不过在基本结构上红黑树与二叉查找树有一点不同
 * 定义了颜色字段，用来表示红链接或者黑链接
 * 定义了查询颜色的方法isRed，仅作为私有方法使用
 * <p>
 * 红色链接表示一个3-node节点
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private Node root;     // 定义整个树的根节点
    private static final boolean RED = true;    // 定义红链接，红链接像胶水一样，将两个节点粘合在一起
    private static final boolean BLACK = false; // 定义黑链接

    /**
     * 定义每个节点的基本结构
     */
    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;  // 链接颜色字段

        Node(Key key, Value value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    /**
     * 检查一个节点的颜色是否是红色链接
     * 如果节点为空，那么链接肯定是黑色的
     */
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
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
     * 将指定的节点进行左旋转，左旋转h的右链接
     * 返回的是旋转完成后的根节点
     * 基本思路：
     *  1. 记录当前根节点h的右子树为x，该右子树将会成为旋转完成后整个子树的根节点
     *  2. 将h的右节点置为x的左节点，因为该左节点介于h和x之间
     *  3. 将x的左节点置为h
     *  4. 更新x的颜色为h的颜色
     *  5. 更新h的颜色为红颜色（因为此时此刻h为x的左子树根节点）
     *  6. 更新x的大小为h的大小（因为整个子树大小没变）
     *  7. 更新h的大小为1 + h的右子树 + h的左子树
     */
    public Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 将指定的节点进行右旋转，右旋转h的左链接
     * 返回的是旋转完成后的根节点
     * 原理同上rotateLeft()方法
     */
    public Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 反转颜色，将根节点的颜色置为红颜色，左节点和右节点统统置为黑颜色
     */
    public void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /**
     * 在树中放置一个键值对
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node root, Key key, Value value) {
        // 如果根节点是空的，那么直接创建一个新的节点进行返回，和父节点使用红链接相连
        if (root == null) return new Node(key, value, 1, RED);
        int cmp = key.compareTo(root.key);
        // 如果当前的key比root的key小，那么在左侧插入该节点
        // 否则在右侧进行插入，如果找到了相同的key，那么更新value值
        if (cmp < 0) root.left = put(root.left, key, value);
        else if (cmp > 0) root.right = put(root.right, key, value);
        else root.value = value;

        // 下面这一系列操作顺序不可以改变，因为下面的情况会对上方的情况进行覆盖修复！！！
        // 如果右侧是红链接，而左侧不是红链接，那么对根节点进行左旋转
        if (isRed(root.right) && !isRed(root.left)) root = rotateLeft(root);
        // 如果左侧是红链接同时左侧的左子树是红链接，那么进行右旋转
        if (isRed(root.left) && isRed(root.left.left)) root = rotateRight(root);
        // 如果左侧是红节点，而且右侧是红节点，那么进行颜色反转
        if (isRed(root.left) && isRed(root.right)) flipColors(root);

        // 更新节点数目
        root.N = 1 + size(root.right) + size(root.left);
        return root;
    }

    /**
     * 使用一个循环操作获取指定key的value值
     */
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> account = new RedBlackBST<>();
        account.put("B", 123);
        account.put("A", 345);
        account.put("C", 789);
        account.put("E", 888);
        account.put("Z", 999);
        System.out.println(account.get("B"));
        account.put("B", 666);
        System.out.println(account.get("B"));
        System.out.println(account.get("Z"));
    }
}
