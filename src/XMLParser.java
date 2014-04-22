import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;
import java.util.Iterator;

import edu.jhu.nlp.wikipedia.*;

public class XMLParser{ 
  public static void main(String args[]) {
  WikiXMLParser wxsp = WikiXMLParserFactory.getSAXParser("E:\\sem-4\\wikitopics\\enwiki");
  WikiXMLParser wxsp1 = WikiXMLParserFactory.getSAXParser("E:\\sem-4\\wikitopics\\enwiki");
  File outputfile = new File("E:\\sem-4\\wikitopics\\techtopics.txt");
  try {
	  FileOutputStream fis = new FileOutputStream(outputfile);  
	  PrintStream out = new PrintStream(fis);  
	  System.setOut(out);
	  final String str = "Category:Theoretical computer science";
	  //final Vector<String> vc=new Vector<String>();
      wxsp.setPageCallback(new PageCallbackHandler() { 
                     public void process(WikiPage page) {
                           if(page.getCategories().contains("Theoretical computer science")){ 
                        	   System.out.println(page.getTitle());
                        	   //vc.add(page.getTitle());
                           }
                           //System.out.println(vc.size());
                     }
      });   
      wxsp.parse();
     fis.close();
     out.close();
  }catch(Exception e) {
          e.printStackTrace();
  }
  }
}