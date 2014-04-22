/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Amani
 */
public class Jaccard_Cofficient {

    /**
     * @param args the command line arguments
     */
    public static double jaccard(String fs, String query) {
        // TODO code application logic here
        //Scanner in = new Scanner(System.in);

        //System.out.print("Enter the String 1 : ");
        String s1 = fs.toLowerCase();
        //System.out.print("Enter the String 2 : ");
        String s2 = query.toLowerCase();
        ArrayList<String> kgram1 = new ArrayList();
        ArrayList<String> kgram2 = new ArrayList();
        Iterator it1, it2;
        ArrayList<String> union = new ArrayList();
        ArrayList<String> intersection = new ArrayList();
        for (int i = 0; i <= s1.length() - 2; i++) {
        	System.out.println("------------------------------------------------------------------------");
            String seq1 = s1.subSequence(i, i + 2).toString();
            if (!kgram1.contains(seq1)) {
                kgram1.add(seq1);
            }
        }
        for (int j = 0; j <= s2.length() - 2; j++) {
            String seq2 = s2.subSequence(j, j + 2).toString();
            if (!kgram2.contains(seq2)) {
                kgram2.add(seq2);
            }
        }
        it1 = kgram1.iterator();
        it2 = kgram2.iterator();
        while (it1.hasNext()) {
            String s = it1.next().toString();
            if (!union.contains(s)) {
                union.add(s);
            }
        }
        while (it2.hasNext()) {
            String s = it2.next().toString();
            if (!union.contains(s)) {
                union.add(s);
            }
        }
        it1 = kgram1.iterator();
        while (it1.hasNext()) {
            String s = it1.next().toString();
            if (!intersection.contains(s) && kgram2.contains(s)) {
                intersection.add(s);
            }
        }
//        //System.out.println("\n\nUnion set");
//        //System.out.print("{ ");
//        for (int i = 0; i < union.size(); i++) {
//            System.out.print("'" + union.get(i) + "',");
//        }
//        System.out.println(" }\nSize : " + union.size());
//        System.out.println("\n\nIntersection set");
//        System.out.print("{ ");
//        for (int i = 0; i < intersection.size(); i++) {
//            System.out.print("'" + intersection.get(i) + "',");
//        }
//        System.out.println(" }\nSize : " + intersection.size());
        double i = intersection.size();
        double u = union.size();
        double jaccard = i / u;
        //System.out.format("\nJaccard is %f\n", jaccard);
        return jaccard;
    }
}
