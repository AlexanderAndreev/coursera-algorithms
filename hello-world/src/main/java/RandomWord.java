import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        String result = null;
        int i = 1;
        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            if (StdRandom.bernoulli(1d / i)) {
                result = temp;
            }
            i++;
        }
        StdOut.println(result);
    }
}
