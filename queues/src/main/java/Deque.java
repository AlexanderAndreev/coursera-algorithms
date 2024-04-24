import java.util.Iterator;
import java.util.NoSuchElementException;


import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {

        private final Item value;
        private Node<Item> next;
        private Node<Item> prev;

        Node(Item value) {

            this.value = value;
        }
    }

    private static class DequeIterator<E> implements Iterator<E> {

        private Node<E> node;

        private DequeIterator(Node<E> node) {

            this.node = node;
        }

        @Override
        public boolean hasNext() {

            return node != null;
        }

        @Override
        public E next() {

            if (!hasNext()) throw new NoSuchElementException();
            Node<E> temp = node;
            node = temp.next;
            return temp.value;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int size = 0;

    public boolean isEmpty() {

        return size == 0;
    }

    public int size() {

        return size;
    }

    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> node = new Node<>(item);
        node.next = head;
        if (size > 0) {
            head.prev = node;
        } else {
            tail = node;
        }
        head = node;
        size++;
    }

    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> node = new Node<>(item);
        node.prev = tail;
        if (size > 0) {
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
        size++;
    }

    public Item removeFirst() {

        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<Item> node = head;
        head = node.next;
        if (size > 1) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return node.value;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<Item> node = tail;
        tail = node.prev;
        if (size > 1) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return node.value;
    }

    public Iterator<Item> iterator() {

        return new DequeIterator<>(head);
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        StdOut.printf("Empty deque: %b%n", deque.isEmpty());
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addLast(4);
        StdOut.printf("Empty deque: %b%n", deque.isEmpty());
        StdOut.printf("Deque size: %d%n", deque.size());
        StdOut.printf("Deque: ");
        for (int i : deque) {
            StdOut.printf("%d ", i);
        }
        StdOut.printf("%nRemove first: %d%n", deque.removeFirst());
        StdOut.printf("Remove last: %d%n", deque.removeLast());
        StdOut.printf("Deque size: %d%n", deque.size());
        StdOut.printf("Result deque: ");
        for (int i : deque) {
            StdOut.printf("%d ", i);
        }
    }

}
