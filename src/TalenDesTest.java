
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Talendesign api
 * @author Yi Chen
 */
public class TalenDesTest extends Assert{
    
    @Test
    public void reverseComplementTest(){
        assertEquals("atcg", Talendesign.reverseComplement("cgat"));
        assertEquals("", Talendesign.reverseComplement(""));
        assertEquals("[atcgcgcg]", Talendesign.reverseComplement("[cgcgcgat]"));
    }

    @Test
    public void dnaRvdTest(){
        assertEquals("HDNNNING", Talendesign.dnaToRvd("cgat"));
        assertEquals("", Talendesign.reverseComplement(""));
    }
    
    @Test
    public void getGeneInfoTest(){
        //we can't check this with assert, since NCBI might update the info,
        //which would change the values of the results; Check manually; This test
        //also should verify that getWebContent() is also working
        
        for (String result: Talendesign.getGeneInfo(3239, "yi.chen901@gmail.com")){
            System.out.println(result);
        }
        System.out.println();
        System.out.println();
        for (String result: Talendesign.getGeneInfo("NC_000002.12", 176087486, 176095937, "yi.chen901@gmail.com")){
            System.out.println(result);
        }
        
    }
    
}
