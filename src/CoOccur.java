
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class CoOccur{
public static void main(String args[]) throws IOException{
	File trainfile = new File(args[0]);
	File testfile = new File(args[1]);
	File output = new File("E:\\december\\thesis\\cooccurtest\\output.txt");
	BufferedReader br = new BufferedReader(new FileReader(testfile));
	String line;
	while ((line = br.readLine()) != null) {
	   topics.add(line.toString());
	}
	br.close();
	int i=1;
	for (String str : topics){
		hm.put(str,i);
		i++;
	}
	DirToFiles(trainfile);
	Printlist(postlist,output);
}

public static HashMap<String, Integer> hm = new HashMap<String, Integer>();
public static List<String> topics = new ArrayList<String>();
public static List<Integer> list = new ArrayList<Integer>();
public static List<List<Integer>> postlist = new ArrayList<List<Integer>>();

public static void DirToFiles(File file) throws IOException {
  if(file.isFile())
	    FileToString(file);
  else if (file.isDirectory()){
    File[] listOfFiles = file.listFiles();
    if(listOfFiles!=null) {
      for (int i = 0; i < listOfFiles.length; i++)
        DirToFiles(listOfFiles[i]);
    }
    else {
      System.out.println(" [ACCESS DENIED]");
    }
  }
}

public static void FileToString(File file) throws IOException{
    String str1 = new Scanner(new File(file.toString())).useDelimiter("\\Z").next();
    Jaccard_Cofficient jc = new Jaccard_Cofficient();
    List<Integer> list = new ArrayList<Integer>();
    for (String ngram : ngrams(2,str1)){
    	for (String topic : topics){
    		double d = jc.jaccard(topic,ngram);
			if(d > 0.8){
            list.add(hm.get(topic));int i=0;
    	    }
        }
    }
    if(!list.isEmpty())
    postlist.add(list);
}

public static List<String> ngrams(int n, String str) {
    List<String> ngrams = new ArrayList<String>();
    String[] words = str.split(" ");
    for (int i = 0; i < words.length - n + 1; i++)
        ngrams.add(concat(words, i, i+n));
    for(int i=0;i<words.length;i++)
        ngrams.add(words[i]);
    return ngrams;
}

public static String concat(String[] words, int start, int end) {
    StringBuilder sb = new StringBuilder();
    for (int i = start; i < end; i++)
        sb.append((i > start ? " " : "") + words[i]);
    return sb.toString();
}

public static void Printlist(List<List<Integer>> pl, File outputfile) throws IOException{
	System.out.println(pl.size());
	 FileOutputStream fis = new FileOutputStream(outputfile);  
	 PrintStream out = new PrintStream(fis);  
	 System.setOut(out);
	for (List<Integer> l : pl){
		System.out.println(l);
	}
	fis.close();
	out.close();
}
}
