import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;


public class HtmlToTxt {
	public static void main(String args[]) throws IOException{
		File f = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\pchtml");
		Process(f);
	}
	public static void Process(File f) throws IOException 
	  {
	    if(f.isFile())
	    {
	     FileReader reader = new FileReader(f.getAbsolutePath());
	     String str2=f.getName();
	     String s = f.getAbsolutePath();
	     s = s.replace(str2,"pctxt\\");
		 String str3= str2.replace(".html", ".txt");
		// String str4=str + "\\\\" ;
		 s = s+str3;
		 System.out.println(s);
	     f=new File(s);
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
	  else if (f.isDirectory())
	  {
	      //System.out.println(spcs + "[DIR] " + aFile.getName());
	      File[] listOfFiles = f.listFiles();
	      if(listOfFiles!=null) 
	      {
	        for (int i = 0; i < listOfFiles.length; i++)
	          Process(listOfFiles[i]);
	      }
	      else 
	      {
	        System.out.println(" [ACCESS DENIED]");
	      }
	    }
	  }
}
