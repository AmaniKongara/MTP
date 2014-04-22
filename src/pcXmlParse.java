import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
 
public class pcXmlParse {
  public static void main(String argv[]) throws IOException {
	  File inputfile = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\Alchemypcfiles\\samplexml");
	  File topicfile = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\AllTopicsWiki\\alltopics.txt");
	  File outputfile = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\coccurtopics.txt");
		if(!(outputfile.exists())){
			outputfile.createNewFile();
		}
		FileOutputStream fis = new FileOutputStream(outputfile);  
		 PrintStream out = new PrintStream(fis);  
		 System.setOut(out);
		
	  ReadTopics(topicfile);
	  ParseXml(inputfile);
	  out.close();
  }
 
  static Set<String> st = new HashSet<String>();
  //static int count=0;
  
  private static void ReadTopics(File topicfile) throws IOException {
	// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(topicfile));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.replaceAll("_"," ");
		   st.add(line.toLowerCase());
		}
		br.close();
		//System.out.println(st.size());
}

static void ParseXml(File fXmlFile){
		  if(fXmlFile.isFile())
			    getKeywords(fXmlFile);
		  else if (fXmlFile.isDirectory()){
		    File[] listOfFiles = fXmlFile.listFiles();
		    if(listOfFiles!=null) {
		      for (int i = 0; i < listOfFiles.length; i++)
		        ParseXml(listOfFiles[i]);
		    }
		    else {
		      System.out.println(" [ACCESS DENIED]");
		    }
  }
}

private static void getKeywords(File fXmlFile) {
	// TODO Auto-generated method stub
	try{
	 Set<String> sk = new HashSet<String>();
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

	doc.getDocumentElement().normalize();
	NodeList nList = doc.getElementsByTagName("keyword");
	for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			//System.out.println(eElement.getElementsByTagName("text").item(0).getTextContent());
			sk.add(eElement.getElementsByTagName("text").item(0).getTextContent());
		}
	}
	//System.out.println(fXmlFile + " : " + sk.size());
	System.out.println(fXmlFile + " : " + sk.size());
	System.out.println("-----------------------------");
	compare(sk,st);
	
	
    } catch (Exception e) {
	e.printStackTrace();
    }
}

private static void compare(Set<String> sk, Set<String> st2) throws IOException {
	for (String t : st){
		for (String k : sk){
			double d = jaccard(k,t);
			if(d > 0.8){
				System.out.println(k + ":" + t);
			}
		}
	}
}

public static double jaccard(String fs, String query) {
    String s1 = fs.toLowerCase();
    String s2 = query.toLowerCase();
    ArrayList<String> kgram1 = new ArrayList<String>();
    ArrayList<String> kgram2 = new ArrayList<String>();
    Iterator it1, it2;
    ArrayList<String> union = new ArrayList<String>();
    ArrayList<String> intersection = new ArrayList<String>();
    for (int i = 0; i <= s1.length() - 2; i++) {
        String seq1 = s1.subSequence(i, i + 2).toString();
        if (!kgram1.contains(seq1)) {
            kgram1.add(seq1);
        }
    }
    for (int j = 0; j <= s2.length() - 2; j++) {
        String seq2 = s2.subSequence(j, j + 2).toString();
        if (!kgram2.contains(seq2)) {
            kgram2.add(seq2);
        }
    }
    it1 = kgram1.iterator();
    it2 = kgram2.iterator();
    while (it1.hasNext()) {
        String s = it1.next().toString();
        if (!union.contains(s)) {
            union.add(s);
        }
    }
    while (it2.hasNext()) {
        String s = it2.next().toString();
        if (!union.contains(s)) {
            union.add(s);
        }
    }
    it1 = kgram1.iterator();
    while (it1.hasNext()) {
        String s = it1.next().toString();
        if (!intersection.contains(s) && kgram2.contains(s)) {
            intersection.add(s);
        }
    }
    double i = intersection.size();
    double u = union.size();
    double jaccard = i / u;
    return jaccard;
}
}