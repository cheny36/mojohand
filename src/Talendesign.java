
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

}