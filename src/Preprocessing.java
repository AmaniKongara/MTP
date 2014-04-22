import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;


public class Preprocessing
{
     int spc_count=-1;
	  public void Process(File aFile,String str) throws IOException 
	  {
		int j,k;
	    spc_count++;
	    String spcs = "";
	    for (int i = 0; i < spc_count; i++)
	      spcs += " ";
	    File f;
	    if(aFile.isFile())
	    {
	     FileReader reader = new FileReader(aFile.getAbsolutePath());
	    // System.out.println(aFile.getAbsolutePath());
	     String str2=aFile.getName();
		 String str3= str2.replace(".html", ".txt");
		 String str4=str + "\\\\" ;
		 String str5=str4+str3;
		 System.out.println(str5);
	     f=new File(str5);
    	  if(!f.exists())
    	  {
    	  f.createNewFile();
    	  }
        StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
        String line;
        while ( (line=br.readLine()) != null) 
        {
          sb.append(line);
        }
        String textOnly = Jsoup.parse(sb.toString()).text();
        FileOutputStream fis = new FileOutputStream(f); 
	    PrintStream out = new PrintStream(fis); 
	    System.setOut(out);
        System.out.println(textOnly);
    	br.close();
	    out.close();
	    fis.close();
      }
      
	  else if (aFile.isDirectory())
	  {
	      //System.out.println(spcs + "[DIR] " + aFile.getName());
	      File[] listOfFiles = aFile.listFiles();
	      if(listOfFiles!=null) 
	      {
	        for (int i = 0; i < listOfFiles.length; i++)
	          Process(listOfFiles[i],str);
	      }
	      else 
	      {
	        System.out.println(spcs + " [ACCESS DENIED]");
	      }
	    }
	    spc_count--;
	  }
	  static HashMap<String, String> listOfWords1 = new HashMap<String, String>();

	  void tokenize(File f) throws IOException
	  {
	        System.out.println("BEFORE STOPWORD REMOVAL");
	  	  BufferedWriter finalOuputBW1 = new BufferedWriter(new FileWriter(new File("vocal1.csv")));

		  if (f.isDirectory())
		    {
		      File[] listOfFiles = f.listFiles();
		      if(listOfFiles!=null) 
		      {
		        for (int i = 0; i < listOfFiles.length; i++)
		        {
		    BufferedReader br = new BufferedReader(new FileReader(listOfFiles[i]));
		    String str1=listOfFiles[i].getAbsolutePath();
		    listOfFiles[i].delete();
		    File newfile=new File(str1);
		    newfile.createNewFile();
	       BufferedWriter bw = new BufferedWriter(new FileWriter(newfile.getAbsoluteFile()));
		    String line;
	    	int numoftokens=0;

		    while ( (line=br.readLine()) != null)
		    {
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreElements())
				{
					String str11=st.nextToken();
					str11= str11.toLowerCase();
					System.out.println(str1);

					if (str11.contains(",")) 
					{
						  str11 = str11.replace(",","");
						}
					if (str11.contains(".")) 
					{
						  str11 = str11.replace(".","");
						}
					if (str11.contains(":")) 
					{
						  str11 = str11.replace(":","");
						}
					if (str11.contains("'")) 
					{
						  str11 = str11.replace("'","");
						}
					if (str11.contains("-")) 
					{
						  str11 = str11.replace("-","");
						}
					if (str11.contains(")")) 
					{
						  str11 = str11.replace(")","");
						  }
					if (str11.contains("(")) 
					{
						  str11 = str11.replace("(","");
						}
					if (str11.contains("·")) 
					{
						  str11 = str11.replace("·","");
						}
					if (str11.contains("•"))
					{
						str11=str11.replace("•","");
					}
					if (str11.contains("–")) 
					{
						  str11 = str11.replace("–","");
						}
					if (str11.contains("\"")) 
					{
						  str11 = str11.replace("\"","");
						}
					if (str11.contains("[")) 
					{
						  str11 = str11.replace("[","");
						}
					if (str11.contains("]")) 
					{
						  str11 = str11.replace("]","");
						}
					if (str11.contains("^")) 
					{
						  str11 = str11.replace("^","");
						}
					if (str11.contains("$")) 
					{
						  str11 = str11.replace("$","");
						}
					if (str11.contains("#")) 
					{
						  str11 = str11.replace("#","");
						}
					if (str11.contains("@")) 
					{
						  str11 = str11.replace("@","");
						}
					if (str11.contains("?")) 
					{
						  str11 = str11.replace("?","");
						}
					if (str11.contains("/")) 
					{
						  str11 = str11.replace("/","");
						}
					if (str11.contains("\\")) 
					{
						  str11 = str11.replace("\\","");
						}
					if (str11.contains("<")) 
					{
						  str11 = str11.replace("<","");
						}
					if (str11.contains("<")) 
					{
						  str11 = str11.replace("<","");
						}
					if (str11.contains("=")) 
					{
						  str11 = str11.replace("=","");
						}
					if (str11.startsWith("")) 
					{
						  str11 = str11.substring(0,str11.length());
						}
					str11=str11.trim();
					if(str11.length()>1)
					{
						numoftokens++;
			            bw.write(str11+"\n");
					}
				}          
		    }
	           bw.close();
	           int k=makeWordList(newfile);
	           System.out.println("<"+str1+ "> : "+ numoftokens+" : " + k);
	           finalOuputBW1.write("<"+str1+"> : " + numoftokens+" : " + k + "\n");

	       }
	    }
	   }
	  System.out.println("See the output in vocal1.csv file created in your directory");
	  }

	  public int makeWordList(File f) throws IOException
		{
		    BufferedReader br = new BufferedReader(new FileReader(f));
		       HashMap<String,String> listOfWords = new HashMap<String,String>();
	    	    String word;
		        while ( (word=br.readLine()) != null)
	          {
	           	listOfWords1.put(word,word);
	          }
			      br.close();  
	      return listOfWords1.keySet().size();

		}

	  
	  static HashMap<String, String> listOfWords = new HashMap<String, String>();
	    
	  public int makeStopWordList(File f) throws IOException
		{
		    BufferedReader br = new BufferedReader(new FileReader(f));
	    	    String word;
		        while ( (word=br.readLine()) != null)
	          {
	               listOfWords.put(word,word);
	          }
	    //  System.out.println("No. of unique tokens:"+listOfWords.size());
	  
	      br.close();
	      return listOfWords.keySet().size();

		}
	  void removestop(File file) throws IOException
		{
		  
		  BufferedWriter finalOuputBW = new BufferedWriter(new FileWriter(new File("vocal.csv")));
		  Scanner in = new Scanner(System.in);
			System.out.println("\n Now to create the tokenized files with stopword removal,Enter the path of the stopword file:");
			String str = in.nextLine();
			 BufferedReader br= new BufferedReader(new FileReader(str));
				List<String> stopWrds = new ArrayList<String>();
				String line;
		        while( ( line = br.readLine() ) != null ) 
		        {
		    	stopWrds.add(line.trim());
		        }
		    	br.close();
			    stopWrds.toArray(new String[stopWrds.size()]);
			       System.out.println("AFTER STOPWORD REMOVAL");
			 if (file.isDirectory())
			    {
			      File[] listOfFiles = file.listFiles();
			      if(listOfFiles!=null) 
			      {
			        for (int i = 0; i < listOfFiles.length; i++)
			        {
			            BufferedReader br1 = new BufferedReader(new FileReader(listOfFiles[i]));
	     String str1=listOfFiles[i].getAbsolutePath();
		   listOfFiles[i].delete();
		   File newfile=new File(str1);
		   newfile.createNewFile();
	     BufferedWriter bw = new BufferedWriter(new FileWriter(newfile.getAbsoluteFile()));
			int numoftokens=0;
			String line1;
		   while( (line1 = br1.readLine() ) != null ) 
		   {
			 for (String rs: stopWrds)
			  {
			   if (line1.equals(rs))
				line1 = line1.replace(rs, "");
			   }
			   if(line1.length()>0)
				{
			      numoftokens++;
		          bw.write(line1+"\n");
				 }
			}
		   bw.close();
		   int k=makeStopWordList(newfile);
	     //System.out.println("<"+str1+ "> : <"+ numoftokens+"> : <" + k+">");     
	     finalOuputBW.write("<"+str1+"> : " + numoftokens+" : " + k + "\n");
	         
	     br.close();
	     
		    }
		  }
	  }
	  System.out.println("See the output in vocal.csv file created in your directory");
	}

	  public static void main(String[] args) throws IOException 
	  {
	    Scanner in = new Scanner(System.in);
		System.out.println("Enter the path of collection:");
	    String nam = in.nextLine();
     	File aFile = new File(nam);
     	
     	Scanner in1 = new Scanner(System.in);
   	    System.out.println("Enter the name of the Directory you want to create for text files along with the whole path:");
   	    String str = in1.nextLine();
   		File file = new File(str);
   		file.mkdir();
	    
   		Preprocessing ov= new Preprocessing();
	    ov.Process(aFile,str);
	    //ov.tokenize(file);
	    //ov.removestop(file);
	    
	    in1.close();
	  }
	}
