package stacksandqueues;


public class ResizingArrayStackOfStrings<Item> {
    private Item[] s;
    private int N;

    public ResizingArrayStackOfStrings(int size) {
        this.N = 0;
        this.s = (Item[]) new Object[size];
    }

    public ResizingArrayStackOfStrings() {
        this.N = 0;
        this.s = (Item[]) new Object[1];
    }

    public void push(Item item) {
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public Item pop() {
        Item value = s[--N];
        s[N] = null;
        if (N > 0 && N <= s.length / 4) resize(s.length / 2);
        return value;
    }

    public static void main(String[] args) {
        ResizingArrayStackOfStrings<String> stack = new ResizingArrayStackOfStrings<String>();
        stack.push("你好");
        stack.push("我叫");
        System.out.println(stack.pop());
        stack.push("小明");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
