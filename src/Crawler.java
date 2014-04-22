import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class Crawler {
 
	public static void main(String[] args) throws IOException {
	    File f = new File(str+"nlp.stanford.edu/IR-book/html/htmledition/irbook.html");
		processPage("http://nlp.stanford.edu/IR-book/html/htmledition/irbook.html");
	}
	static String str = "E:\\sem-4\\thesis\\manning\\";
	static String part = "http://nlp.stanford.edu/IR-book/html/htmledition/";
	
	public static void processPage(String s) throws IOException{
		URL page = new URL(s);
		Document doc = Jsoup.connect(s).get();
		String title = doc.title();
		System.out.println(title);
		File file = new File(str+title+".html");
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(page.openStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
		String inputLine;
        while ((inputLine = in.readLine()) != null){
            try{
                bw.write(inputLine);
            }
            catch(IOException e){
                e.printStackTrace();
                return;
            }
        }
        in.close();
        bw.close();
		bw.close();
 //get all links and recursively call the processPage method
		Elements links = doc.select("a[href]");
		for(Element link: links){
			System.out.println(link.toString());
			//if(link.toString().contains(part))
			//processPage(link.toString());
		}
	}
}
