import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;

public class FileExtract {
public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException{
	Set<Integer> set = new HashSet<Integer>();
	Class.forName("com.mysql.jdbc.Driver");
	String connectionURL = "jdbc:mysql://localhost:3306/wikipedia";
    Connection connection = null;
    connection = DriverManager.getConnection(connectionURL, "root", "amanis");
    Statement statement1 = null;
    statement1 = connection.createStatement();
    Statement statement = null;
    statement = connection.createStatement();
    Statement statement2 = null;
    statement2 = connection.createStatement();
    Statement statement3 = null;
    statement3 = connection.createStatement();
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs = null;
    Set<Integer> si = new HashSet<Integer>();
    Set<Integer> sd = new HashSet<Integer>();
    for(int i=0;i<91149;i++){
    	si.add(i);
    }
    String cmd1=" SELECT Title,Body FROM so_posts where Topic='042011 Theoretical Computer Science' and Id='";
    String cmd = " SELECT Id FROM so_posts where Topic='042011 Theoretical Computer Science' and ParentId='";
    String cmd2=" SELECT Body FROM so_posts where Topic='042011 Theoretical Computer Science' and ParentId='";
    String cmd3=" SELECT text FROM so_comments where Topic='042011 Theoretical Computer Science' and postid='";
    
    String file = "C:\\AMANI\\MTech\\sem-4\\thesis\\pchtml\\pc";
    
    Iterator<Integer> iter = si.iterator();
	while (iter.hasNext()) {
    	
    	String f;  
    	int i = iter.next();
    	if(!(sd.contains(i))){
    	String total1 = cmd1 + i + "'and ParentId=-1;";
    	String total = cmd + i + "';";
    	String total2 = cmd2 + i + "';";
    	String total3 = cmd3 + i + "'and score > '1';";
    	
		rs1 =statement1.executeQuery(total1);
		rs = statement.executeQuery(total);
		rs2 =statement2.executeQuery(total2);
		rs3 =statement3.executeQuery(total3);
		if(rs.next()){
			//System.out.println(rs.getInt(1));
			sd.add(rs.getInt(1));
		}
	    if(rs1.next()){
	    	f = file + i + ".html";
	    	File outputfile = new File(f);
		    if (!outputfile.exists()) {
				outputfile.createNewFile();
			}
		    FileOutputStream fis = new FileOutputStream(outputfile);  
		    PrintStream out = new PrintStream(fis);  
		    System.setOut(out);
		   
		    System.out.println("ParentPost:");
			System.out.println(rs1.getString(1));
			System.out.println(rs1.getString(2));
		while(rs1.next()){
			System.out.println(rs1.getString(1));
			System.out.println(rs1.getString(2));
		}
		while(rs2.next()){
			System.out.println("ChildPosts:");
			System.out.println(rs2.getString(1));
		}
		while(rs3.next()){
			System.out.println("Comments:");
			System.out.println(rs3.getString(1));
		}
		fis.close();
	    }
	    
    }
	}
}
}

