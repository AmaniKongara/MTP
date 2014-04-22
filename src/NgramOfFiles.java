import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class NgramOfFiles {
public static void main(String args[]) throws IOException{
	File trainfile = new File(args[0]);
	File testfile = new File(args[1]);
	BufferedReader br = new BufferedReader(new FileReader(testfile));
	String line;
	while ((line = br.readLine()) != null) {
		//System.out.println(line.toString().length());
	   topics.add(line.toString());
	}
	br.close();
	int i=1;
	for (String str : topics){
		hm.put(str,i);
		i++;
	}
	//System.out.println(hm.size());
	DirToFiles(trainfile);
	Printlist(postlist);
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
    //System.out.println(listOfFiles.length);
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
	FileInputStream fis = null;
    String str = "";
    fis = new FileInputStream(file);
    int content;
    while ((content = fis.read()) != -1) {
    	str += (char) content;
        }
    Jaccard_Cofficient jc = new Jaccard_Cofficient();
    List<Integer> list = new ArrayList<Integer>();
    for (String ngram : ngrams(2,str)){
    	for (String topic : topics){
    		double d = jc.jaccard(topic,ngram);
			if(d > 0.8){
            System.out.println(topic + "-" + ngram);
            System.out.println(hm.get(topic));
            list.add(hm.get(topics));
            //System.out.println(list.get(0));
    	    }
        }
    }
    if(!list.isEmpty())
    	for(int i=0; i<list.size();i++)
    		System.out.println(list.get(i));
    postlist.add(list);
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

public static void Printlist(List<List<Integer>> pl){
	//System.out.println(pl.size());
	for (List<Integer> l : pl){
		//System.out.println(l.size());
		Iterator<Integer> myListIterator = l.iterator(); 
		//for(int i=0; i< l.size(); i++){
			//System.out.println(l.get(2));
	//	}
	}
}
}
