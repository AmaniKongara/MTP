import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AlltopicExtract {
public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikipedia","root", "amanis");
	System.out.println("done!!!");
	Statement statement = null;
	Statement statement1 = null;
	statement=connection.createStatement();
	statement1=connection.createStatement();
	ResultSet rs=null;
	Set<String> book = new HashSet<String>();
	File output = new File("C:\\AMANI\\MTech\\sem-4\\thesis\\AllTopicsWiki\\alltopics.txt");
	if (!output.exists()) {
		output.createNewFile();
	}

	FileWriter fw = new FileWriter(output.getAbsoluteFile());
	BufferedWriter bw = new BufferedWriter(fw);
	
    	String x = "select child_topic from topicdb;";
        rs = statement.executeQuery(x);
        while(rs.next()){
        	book.add(rs.getString(1));
            bw.write(rs.getString(1));
            bw.newLine();
        }
        bw.close();
     //   String y = "select topic from topicdb;";
    connection.close();
}
}