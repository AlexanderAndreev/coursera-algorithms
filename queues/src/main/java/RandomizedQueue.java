import java.util.Iterator;
import java.util.NoSuchElementException;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size;

    public RandomizedQueue() {

        array = (Item[]) new Object[8];
        size = 0;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public int size() {

        return size;
    }

    public void enqueue(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }
        array[size] = item;
        size++;
        if (array.length == size) {
            Item[] copy = (Item[]) new Object[size * 2];
            System.arraycopy(array, 0, copy, 0, size);
            array = copy;
        }
    }

    public Item dequeue() {

        if (size == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(size);
        Item item = array[index];
        size--;
        if (size <= array.length / 4) {
            Item[] copy = (Item[]) new Object[array.length / 2];
            System.arraycopy(array, 0, copy, 0, index);
            System.arraycopy(array, index + 1, copy, index, size - index);
            array = copy;
        } else {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        return item;
    }

    public Item sample() {

        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[StdRandom.uniformInt(size)];
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private final Item[] array;
        private int coursor = 0;

        RandomizedQueueIterator(Item[] array, int size) {

            this.array = (Item[]) new Object[size];
            System.arraycopy(array, 0, this.array, 0, size);
            if (size > 1) {
                shuffle();
            }
        }

        private void shuffle() {

            for (int i = 1; i < array.length; i++) {
                int j = StdRandom.uniformInt(i);
                Item temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        @Override
        public boolean hasNext() {

            return coursor < array.length;
        }

        @Override
        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = array[coursor];
            coursor++;
            return item;
        }
    }

    public Iterator<Item> iterator() {

        return new RandomizedQueueIterator<>(array, size);
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        StdOut.print("Randomized: ");
        for (int i : queue) {
            StdOut.printf("%d", i);
        }
        StdOut.printf("%nRandomized: ");
        for (int i : queue) {
            StdOut.printf("%d", i);
        }
        StdOut.printf("%nSample: %d", queue.sample());
        StdOut.printf("%nSample: %d", queue.sample());
        StdOut.printf("%nDequeue: %d", queue.dequeue());
        StdOut.printf("%nDequeue: %d", queue.dequeue());
        StdOut.printf("%nRandomized after dequeue: ");
        for (int i : queue) {
            StdOut.printf("%d", i);
        }
    }

}
