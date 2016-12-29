
import java.util.Hashtable;

import com.basistech.tclre.*;

public class Talendesign {

    public static void main(String[] args) throws Exception {
        RePattern pattern = HsrePattern.compile("t.{1,2}a", PatternFlags.ADVANCED);
        ReMatcher matcher = pattern.matcher("tgaagctggga");
        
        System.out.println(matcher.find());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
        System.out.println(matcher.find());
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
}