import java.util.*;

public class BigramofaString {

    public static List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
    	String str = "What is a good special-case sorting algorithm? I have a dataset which is a number of objects arranged in a 2-D grid.";
        Set<String> st = new HashSet<String>();
        Set<String> st1 = new HashSet<String>();
    	st.add("special-case sorting algorithm?");
    	st.add("is a number");
    	st.add("a number of");
    	st.add("number of objects");
    	st.add("a number");
    	for (int n = 3; n >=1; n--) {
            for (String ngram : ngrams(n,str))
            	/*if(st.contains(ngram))
                System.out.println(ngram);*/
            	st1.add(ngram);
        }
    	System.out.println(st1.size());
    	st1.retainAll(st);
    	System.out.println(st1.size());
    	System.out.println(st1);
    }
}