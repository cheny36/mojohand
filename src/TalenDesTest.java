
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
}
