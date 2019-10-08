package stacksandqueues;

public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N;

    public ResizingArrayStackOfStrings(int size) {
        this.N = 0;
        this.s = new String[size];
    }

    public ResizingArrayStackOfStrings() {
        this.N = 0;
        this.s = new String[1];
    }

    public void push(String item) {
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    private void resize(int size) {
        String[] copy = new String[size];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public String pop() {
        String value = s[--N];
        s[N] = null;
        if (N > 0 && N <= s.length / 4) resize(s.length / 2);
        return value;
    }

    public static void main(String[] args) {
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
        System.out.println(stack.s.length);
        stack.push("你好");
        stack.push("我叫");
        System.out.println(stack.s.length);
        System.out.println(stack.pop());
        stack.push("小明");
        System.out.println(stack.s.length);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
