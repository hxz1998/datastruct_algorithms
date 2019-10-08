package stacksandqueues;

public class LinkedStackOfStrings {
    private Node first = null;

    public LinkedStackOfStrings() {
        first = new Node();
        first.next = null;
        first.item = null;
    }

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first.next == null;
    }

    public void push(String value) {
        Node oldFirst = first;
        first = new Node();
        first.item = value;
        first.next = oldFirst;
    }

    public String pop() {
        String value = first.item;
        first = first.next;
        return value;
    }

    public static void main(String[] args) {
        LinkedStackOfStrings linkedStackOfStrings = new LinkedStackOfStrings();
        linkedStackOfStrings.push("你好");
        linkedStackOfStrings.push("我叫");
        System.out.println(linkedStackOfStrings.pop());
        linkedStackOfStrings.push("小明");
        System.out.println(linkedStackOfStrings.pop());
        System.out.println(linkedStackOfStrings.pop());
    }
}
