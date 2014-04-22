import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IndexPageTerms {
public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikipedia","root", "amanis");
	System.out.println("done!!!");
	Statement statement = null;
	statement=connection.createStatement();

	ArrayList<String> elements = new ArrayList<String>();
	File input = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\index-1.htm");
	Document doc = Jsoup.parse(input,"UTF-8", "");
	Elements div1 = doc.select("DL DT strong");
   int i=0;
    for (Element el4 : div1) {
    	elements.add(el4.text());
    	i++;
    }
    System.out.println(elements.size());
    Elements div2 = doc.select("DL DD");
    int x=0, k=0;
   
    for (Element el5 : div2) {
    	Elements el6 = el5.select("a");
    	x++;
    	String st = elements.get(k);
    	st = st.replaceAll("'","");
    	for(Element e : el6){
    		String s = e.attr("HREF");
    		String[] sp = s.split("/");
    		int j = sp[sp.length-1].indexOf(".");
    		String str = sp[sp.length-1].substring(0,j);
    		str = str.replaceAll("'","");
    		String cmd = "insert into terms(topic,parent) values(\"" + st + "\",\"" + str+"\");";
    		System.out.println(cmd);
    		statement.executeUpdate(cmd);
    	}
    	System.out.println("--------");
    	k++;
    }
    connection.close();
}
}