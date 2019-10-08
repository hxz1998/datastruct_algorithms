package stacksandqueues;

public class FixedCapacityStackOfStrings<Item> {
    private int N = 0;
    private Item[] s;

    public FixedCapacityStackOfStrings(int size) {
        s = (Item[]) new Object[size];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        s[N++] = item;
    }

    public Item pop() {
        return s[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings<String> stack = new FixedCapacityStackOfStrings<String>(10);
        stack.push("你好");
        stack.push("我叫");
        System.out.println(stack.pop());
        stack.push("小明");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
