import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


public class Ntoken {
public static void main(String args[]) throws IOException{
	Reader reader = new StringReader("This is a test string");      
	NGramTokenizer gramTokenizer = new NGramTokenizer(reader, 1, 3);

	CharTermAttribute charTermAttribute = gramTokenizer.addAttribute(CharTermAttribute.class);

	while (gramTokenizer.incrementToken()) {
	String token = charTermAttribute.toString();
	System.out.println(token);}
}
}
