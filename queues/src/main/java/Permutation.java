import java.util.Iterator;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        int i = 0;
        Iterator<String> iterator = randomizedQueue.iterator();
        while (i < k && iterator.hasNext()) {
            StdOut.println(iterator.next());
            i++;
        }
    }
}
