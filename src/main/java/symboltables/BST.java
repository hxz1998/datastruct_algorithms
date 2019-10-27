package symboltables;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node{
        private Value value;
        private Key key;
        private Node left, right;
        private int N;
        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

}
