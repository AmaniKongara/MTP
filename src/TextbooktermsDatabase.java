import java.io.IOException;
import java.sql.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TextbooktermsDatabase {
public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikipedia","root", "amanis");
	System.out.println("done!!!");
	Statement statement = null;
	Statement statement1 = null;
	statement=connection.createStatement();
	statement1=connection.createStatement();
	ResultSet rs=null;
	ResultSet rs1=null;
	
    	String x = "select * from terms;";
    	String cmd;
        rs = statement1.executeQuery(x);
        while(rs.next()){
 		   String s = rs.getString(1);
 		   String t = rs.getString(2);
    	cmd = "INSERT INTO booktaxonomy"
    		+ "(topic,parent) VALUES"
    		+ "(\""+s+ "\",\"" +t+"\");";
    	System.out.println(cmd);
    	statement.executeUpdate(cmd);
        }
        
        String x1 = "select * from contents;";
    	String cmd1;
        rs1 = statement1.executeQuery(x1);
        while(rs1.next()){
 		   String s = rs1.getString(1);
 		   String t = rs1.getString(2);
    	cmd1 = "INSERT INTO booktaxonomy"
    		+ "(topic,parent) VALUES"
    		+ "(\""+s+ "\",\"" +t+"\");";
    	System.out.println(cmd1);
    	statement.executeUpdate(cmd1);
        }
 
    connection.close();
}
}