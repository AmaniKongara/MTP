import java.io.IOException;
import java.sql.*;

public class IndexToContentDB {
public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikipedia","root", "amanis");
	System.out.println("done!!!");
	Statement statement = null;
	Statement statement1 = null;
	statement=connection.createStatement();
	statement1=connection.createStatement();
	ResultSet rs=null;
	//ResultSet rs1=null;
	
    	String x = "select * from wordindex";
        rs = statement.executeQuery(x);
        while(rs.next()){
        	String str = "insert into topicid(topic,parent,level) values" + "(\"" +rs.getString(1).toString() + "\",\"" +rs.getString(2).toString() +"\",0)" ;
            statement1.executeUpdate(str);
        }
    connection.close();
}
}