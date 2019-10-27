package symboltables;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import util.DataPathTemplate;

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = StdIn.readInt();
        System.out.println(minlen);
        ST<String, Integer> st = new ST<String, Integer>();
        String[] s = new In(DataPathTemplate.build("tinyTale.txt")).readAllStrings();
        for (String word : s) {
            if (word.length() < minlen) continue;
            if (st.contains(word)) st.put(word, st.get(word) + 1);
            else st.put(word, 1);
        }

        String max = "";
        st.put(max, 0);

        for (String word : st) {
            if (st.get(word) > st.get(max)) max = word;
        }

        System.out.printf("出现次数最多的单词是：%s, 次数为 %d", max, st.get(max));
    }
}
