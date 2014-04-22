import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaxNgramMatch {
	public static void main(String args[]) throws IOException{
	File input = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\samplepctxt");
    File topicfile = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\AllTopicsWiki\\alltopics.txt");

	ReadTopics(topicfile);
	parsefiles(input);
	
}
	static Set<String> st = new HashSet<String>();
	
	private static void ReadTopics(File topicfile) throws IOException {
		// TODO Auto-generated method stub
			BufferedReader br = new BufferedReader(new FileReader(topicfile));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("_", " ");
			   st.add(line.toLowerCase());
			}
			br.close();
			/*System.out.println(st);
			System.out.println("----------------");*/
	}
	
	private static void parsefiles(File input) throws IOException {
		// TODO Auto-generated method stub
		if(input.isFile()){
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(input));
			StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			 sb.append(line);
		}
		String fl = sb.toString();
		fl = fl.replaceAll("[^\\w_]", " ");
		/*System.out.println(fl);
		System.out.println("--------------------");*/
		fl = fl.toLowerCase();
		System.out.println(input);
		System.out.println("-----------------");
		findMaxString(fl,st);
		System.out.println("-----------------");
		}
		else if (input.isDirectory()){
	    File[] listOfFiles = input.listFiles();
	    if(listOfFiles!=null) {
	      for (int i = 0; i < listOfFiles.length; i++)
	        parsefiles(listOfFiles[i]);
	    }
	    else {
	      System.out.println(" [ACCESS DENIED]");
	    }
	  }
	}

	private static void findMaxString(String txtfile, Set<String> st) {
		// TODO Auto-generated method stub
		System.out.println();
		for (String t : st){
			t = " " + t + " ";
			if(txtfile.contains(t) && t.length()>4){
				System.out.println(t);
			}
			/*else{
				 for (int n = 1; n <= 3; n++) {
			            for (String ngram : ngrams(n,t))
			                if(txtfile.contains(ngram))
			                	System.out.println(ngram);
				 }
			}*/
		}
	}
	
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

}
