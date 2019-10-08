package stacksandqueues;

public class LinkedQueueOfStrings {
    private class Node {
        String item;
        Node next;
    }

    private Node first;
    private Node last;

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(String value) {
        Node oldLast = last;
        last = new Node();
        last.item = value;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public String dequeue() {
        String value = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return value;
    }

    public static void main(String[] args) {
        LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
        queue.enqueue("你好");
        queue.enqueue("我叫");
        queue.enqueue("小明");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("你好");
        queue.enqueue("我叫");
        queue.enqueue("小明");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
