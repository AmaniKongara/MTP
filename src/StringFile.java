import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;


public class StringFile {
public static void main(String args[]) throws IOException{
	 
    File aFile = new File(args[0]);
    File f = new File(args[1]);
    tokenizeTopics(f);
    //System.out.println(sto.size());
    Process(aFile);	
}	

static Set<String> sto = new HashSet<String>();
static String file = "E:\\december\\thesis\\test1\\out\\";
static int j = 0;
public static void Process(File aFile) throws IOException 
{
	
  if(aFile.isFile()){
	  String f = file + j + ".txt";
		File outputfile = new File(f);
	    if (!outputfile.exists()) {
			outputfile.createNewFile();
		}
	    tokenize(aFile,outputfile);
  }
  else if (aFile.isDirectory()){
    File[] listOfFiles = aFile.listFiles();
    System.out.println(listOfFiles.length);
    if(listOfFiles!=null) {
      for (int i = 0; i < listOfFiles.length; i++){
    	  j++;
    	  System.out.println(j);
        Process(listOfFiles[i]);
      }
    }
    else {
      System.out.println(" [ACCESS DENIED]");
    }
  }
}

public static void tokenize(File f, File outputfile) throws IOException{
	 Set<String> st = new HashSet<String>();
		StringTokenizer tokenizer;  
		BufferedReader input = null;    
		input = new BufferedReader(new FileReader(f));  
		String line;   
		while ((line = input.readLine()) != null) { 
		  tokenizer = new StringTokenizer(line);    
		  while (tokenizer.hasMoreTokens()){// process tokens in line  
		    String token = tokenizer.nextToken();  
		    st.add(token); 
		  }// close inner while 
		}
		input.close();
		System.out.println(st.size());
		
		Jaccard_Cofficient jc = new Jaccard_Cofficient();
		FileOutputStream fis = new FileOutputStream(outputfile); 
	    PrintStream out = new PrintStream(fis); 
	    System.setOut(out);
		for( String str1 : sto){
			for (String str2 : st){
				double d = jc.jaccard(str1,str2);
				if(d > 0.6){
					System.out.println(str1 + "-" + str2 + "-" + d);
				}
			}
		}
		out.close();
		 fis.close();
		return;
}

public static void tokenizeTopics(File f) throws IOException{
		StringTokenizer tokenizer;  
		BufferedReader input = null;    
		input = new BufferedReader(new FileReader(f));  
		String line;   
		while ((line = input.readLine()) != null) { 
		  tokenizer = new StringTokenizer(line);    
		  while (tokenizer.hasMoreTokens()){// process tokens in line  
		    String token = tokenizer.nextToken();  
		    sto.add(token); 
		  }// close inner while 
		}
		input.close();	
		return;
}
}