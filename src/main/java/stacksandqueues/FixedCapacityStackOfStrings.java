package stacksandqueues;

public class FixedCapacityStackOfStrings {
    private int N = 0;
    private String[] s;

    public FixedCapacityStackOfStrings(int size) {
        s = new String[size];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item;
    }

    public String pop() {
        return s[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
        stack.push("你好");
        stack.push("我叫");
        System.out.println(stack.pop());
        stack.push("小明");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
