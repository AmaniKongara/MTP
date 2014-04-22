import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class dotFile {
public static void main(String[] args) throws IOException {
	File input = new File("E:\\sem-4\\thesis\\manninghtml\\index.html");
	Document doc = Jsoup.parse(input,"UTF-8", "");
	//Document doc = Jsoup.connect("http://nlp.stanford.edu/IR-book/html/htmledition/irbook.html").get();
	File f = new File("E:\\sem-4\\thesis\\indexterms.dot");
	if(!f.exists()){
		f.createNewFile();
	}
	FileWriter fw = new FileWriter(f.getAbsoluteFile());
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write("graph hospitalgraph{");
	bw.write("\n");
	
    Elements div1 = doc.select("ul li ");
    System.out.println(div1.size());
    for (Element el4 : div1) {
       // System.out.println(el4.select("a").first().text());
        String str =el4.select("a").first().text().replace("-","" );
        str = str.replace("(","");
        str = str.replace(")"," ");
        str = str.replace("'","");
        str = str.replace("?", "");
        str = str.replace(".", "");
        str = str.replaceAll("/", "");
        str = str.replace("//", "");
        str = str.replace(":","");
        str = str.replaceAll(" ","_");
        //System.out.println(str);
        bw.write(str + "[shape=box];");
        bw.write("\n");
    }
    bw.write("\n");
    bw.write("\n");
    bw.write("\n");
    for(Element el5 : div1){
    	String str1 = el5.select("a").first().text();
    	String str2 = el5.parent().parent().select("a").first().text();
    	str1 = str1.replace("-","");
    	str1 = str1.replace("(","");
    	str1 = str1.replace(")","");
    	str1 = str1.replace("'", "");
    	str1 = str1.replace("?", "");
    	str1 = str1.replace(".", "");
    	str1 = str1.replaceAll("/", "");
    	str1 = str1.replaceAll("//","");
    	str1 = str1.replace(":","");
    	str1 = str1.replaceAll(" ","_");
    	str2 = str2.replace("(","");
    	str2 = str2.replace(")","");
    	str2 = str2.replace("'", "");
    	str2 = str2.replace("?", "");
    	str2 = str2.replace(".", "");
    	str2 = str2.replaceAll("/", "");
    	str2 = str2.replaceAll("//","");
    	str2 = str2.replace(":","");
    	str2 = str2.replace("-","");
    	str2 = str2.replaceAll(" ","_");
        bw.write(str2 + "--" + str1 + ";");
        bw.write("\n");
         //System.out.println(" 1  " +el3.select("a").first().text());
    }
    bw.write("}");
    bw.close();
}
}