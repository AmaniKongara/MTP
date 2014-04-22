import java.io.File;
import java.io.IOException;
import java.sql.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContentPageTerms {
public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikipedia","root", "amanis");
	System.out.println("done!!!");
	Statement statement = null;
	Statement statement1 = null;
	statement=connection.createStatement();
	statement1=connection.createStatement();
	ResultSet rs=null;
	
	File input = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\contents.htm");
	Document doc = Jsoup.parse(input,"UTF-8", "");
	//Document doc = Jsoup.connect("http://nlp.stanford.edu/IR-book/html/htmledition/irbook.html").get();
	
	Elements div4 = doc.select("ul li ul li ul li ul li");
    for (Element el4 : div4) {
    	String s = el4.select("a").attr("HREF");
     
         Element el3 = el4.parent().parent();
         Element el2 = el3.parent().parent();
         Element el1 = el2.parent().parent();
         Element el0 = el1.parent().parent();
         
         String s1 = el3.select("a").first().attr("HREF");
         String s2 = el2.select("a").first().attr("HREF");
         String s3 = el1.select("a").first().attr("HREF");
         String s4 = el0.select("a").first().attr("HREF");
         
         String[] sp = s.split("/");
 		int j = sp[sp.length-1].indexOf(".");
 		
 		String[] s1p = s1.split("/");
 		int k = s1p[s1p.length-1].indexOf(".");
         
         String cmd = "INSERT INTO Contents"
    		+ "(topic,parent) VALUES"
    		+ "(\""+sp[sp.length-1].substring(0,j)+ "\",\"" +s1p[s1p.length-1].substring(0,k)+ "\");";
         statement.executeUpdate(cmd);
         System.out.println(cmd);
    }
    
    Elements div3 = doc.select("ul li ul li ul li ");
    for (Element el4 : div3) {
    	String s = el4.select("a").attr("HREF");
   
         Element el3 = el4.parent().parent();
         Element el2 = el3.parent().parent();
         Element el1 = el2.parent().parent();
         
         String s1 = el3.select("a").first().attr("HREF");
         String s2 = el2.select("a").first().attr("HREF");
         String s3 = el1.select("a").first().attr("HREF");
    
         String[] sp = s.split("/");
  		int j = sp[sp.length-1].indexOf(".");
  		
  		String[] s1p = s1.split("/");
  		int k = s1p[s1p.length-1].indexOf(".");
          
         
         String chk = "select topic from Contents where topic=\"" +sp[sp.length-1].substring(0,j)+ "\";";
         rs = statement.executeQuery(chk);
         String cmd = "INSERT INTO Contents"
     		+ "(topic,parent) VALUES"
     		+ "(\""+sp[sp.length-1].substring(0,j)+ "\",\"" +s1p[s1p.length-1].substring(0,k)+ "\");";
         if(!rs.next()){
        	 System.out.println(cmd);
             statement.executeUpdate(cmd);
         }
    }
    
    Elements div2 = doc.select("ul li ul li ");
    for (Element el4 : div2) {
        String s = el4.select("a").attr("HREF");
         Element el3 = el4.parent().parent();
         Element el2 = el3.parent().parent();
         
         String s1 = el3.select("a").first().attr("HREF");
         String s2 = el2.select("a").first().attr("HREF");
       
         String[] sp = s.split("/");
   		int j = sp[sp.length-1].indexOf(".");
   		
   		String[] s1p = s1.split("/");
   		int k = s1p[s1p.length-1].indexOf(".");
           
         
         String chk = "select topic from Contents where topic=\"" +sp[sp.length-1].substring(0,j)+ "\";";
         rs = statement.executeQuery(chk);
         String cmd = "INSERT INTO Contents"
     		+ "(topic,parent) VALUES"
     		+ "(\""+sp[sp.length-1].substring(0,j)+ "\",\"" +s1p[s1p.length-1].substring(0,k)+ "\");";
         if(!rs.next()){
        	 System.out.println(cmd);
             statement.executeUpdate(cmd);
             }
    }
    statement.close();
    connection.close();
}
}