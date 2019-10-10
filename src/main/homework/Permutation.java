import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//import util.DataPathTemplate;
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        int count = Integer.parseInt(args[0]);
//        String[] strings = In.readStrings(DataPathTemplate.build("duplicates.txt"));
//        String[] strings = StdIn.readAllStrings();
//        for (String item : strings) randomizedQueue.enqueue(item);
        String string = StdIn.readString();
        while (string != null) {
            randomizedQueue.enqueue(string);
            string = StdIn.readString();
        }
        for (int i = 0; i < count; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
