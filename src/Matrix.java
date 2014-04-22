import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Matrix {
public static void main(String args[]) throws IOException{
	List<String> topics = new ArrayList<String>();
	HashMap<String, Integer> hm = new HashMap<String, Integer>();
	List<List<Integer>> postlist = new ArrayList<List<Integer>>();
	File file = new File("E:\\december\\thesis\\test1\\input.txt");
	BufferedReader br = new BufferedReader(new FileReader(file));
	String line;
	while ((line = br.readLine()) != null) {
	   topics.add(line.toString());
	}
	br.close();
	//Map<Integer,String> map = new HashMap<Integer,String>();
	int i=1;
	for (String str : topics){
		List<Integer> list = new ArrayList<Integer>();
		hm.put(str,i);
		//postlist.add();
		i++;
	}
	System.out.println(hm.get("SAT"));
}
}
