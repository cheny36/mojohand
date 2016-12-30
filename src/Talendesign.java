
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.basistech.tclre.*;

public class Talendesign {

    public static void main(String[] args) throws Exception {
//        RePattern pattern = HsrePattern.compile("t.{1,2}a", PatternFlags.ADVANCED);
//        ReMatcher matcher = pattern.matcher("tgaagctggga");
//        
//        System.out.println(matcher.find());
//        System.out.println(matcher.start());
//        System.out.println(matcher.end());
//        System.out.println(matcher.find());
        
        String accession;
        int seqStart;
        int seqEnd;
        
        ArrayList<String> Labels = new ArrayList<String>();
        ArrayList<String> Sequences = new ArrayList<String>();
    }
    
    
    
    
    
    /**
     * Produces the reverse complement of a DNA sequence
     * @param s original DNA sequence
     * @return reverse complement sequence
     */
    public static String reverseComplement(String s){
        Hashtable<Character, Character> key = new Hashtable<Character, Character>();
        key.put('a', 't');
        key.put('t', 'a');
        key.put('g', 'c');
        key.put('c', 'g');
        key.put('[', ']');
        key.put(']', '[');
        key.put('.', '-');
        key.put(',', '-');
        
        s = s.toLowerCase();
        
        StringBuilder x = new StringBuilder();
        for(int i = s.length()-1; i >= 0; i--){
            x.append(key.get(s.charAt(i)));
        }
        return x.toString();
    }
    
    /**
     * Changes a DNA sequence into TALEN RVD sequences
     * @param s original DNA sequence
     * @return new RVD sequence
     */
    public static String dnaToRvd(String s){
        Hashtable<Character, String> key = new Hashtable<Character, String>();
        key.put('a', "NI");
        key.put('t', "NG");
        key.put('g', "NN");
        key.put('c', "HD");
        
        s = s.toLowerCase();
        
        StringBuilder x = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            x.append(key.get(s.charAt(i)));
        }
        return x.toString();
    }

    /**
     * Attempts to obtain a given html file within a certain amount of tries
     * @param url string with web address
     * @param attempts number of tries before failure
     * @return string containing html/css parsed web content
     */
    public static String getWebContent(String url, int attempts){
        Document doc = null;
        boolean success = true;
        for(int i = 0; i < attempts; i++){
            success = true;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                success = false;
                e.printStackTrace();
            } finally {
                if (success){
                    break;
                }
            }
        }
        if (!success){
            System.exit(0);
        }
        return doc.toString();
    }

    /**
     * Obtain gene information from a gene ID from the ncbi database
     * @param gene
     * @param email
     * @return String array containing: accession number, gene start, gene end, symbol, aka, summary
     */
    public static String[] getGeneInfo(int gene, String email){
        //I don't know how else to pass the results other than in a String array, which seems improper
        String url = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esummary.fcgi?db=gene&id="
                + gene
                + "&tool=tal_tool&email="
                + email;
        String geneSummary = getWebContent(url, 3);
        BufferedReader reader = new BufferedReader(new StringReader(geneSummary));
        String[] results = new String[6];

        String line;
        try {
            while((line = reader.readLine()) != null){
                if (line.contains("ChrAccVer") && results[0] == null){
                    results[0] = reader.readLine().trim(); //I'm assuming they won't send bad xmls
                }
                if (line.contains("ChrStart") && results[1] == null){
                    results[1] = reader.readLine().trim();
                }
                if (line.contains("ChrStop") && results[2] == null){
                    results[2] = reader.readLine().trim();
                }
                if (line.contains("NomenclatureSymbol") && results[3] == null){
                    results[3] = reader.readLine().trim();
                }
                if (line.contains("OtherAliases") && results[4] == null){
                    results[4] = reader.readLine().trim();
                }
                if (line.contains("<Summary>") && results[5] == null){
                    results[5] = reader.readLine().trim();
                }
            }
        } catch (IOException e) { //failed at reading the summary file :/
            System.exit(0);
            e.printStackTrace();
        }
        return results;
    }
    
    /**
     * Pulls DNA sequences and labels from the NCBI database based off coordinates and
     * accession number
     * @param Labels arraylist for storing our labels
     * @param Sequences arraylist for storing our gene sequences
     */
    public static void getSeqNCBI(ArrayList<String> Labels, ArrayList<String> Sequences){
        
    }


}