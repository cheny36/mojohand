
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
    public void getGeneInfoTest() throws FileNotFoundException, UnsupportedEncodingException{
        //we can't check this with assert, since NCBI might update the info,
        //which would change the values of the results; Check manually; This test
        //also should verify that getWebContent() is also working
        //prints example XML files to text files
        
        for (String result: Talendesign.getGeneInfo(3239, "yi.chen901@gmail.com")){
            System.out.println(result);
        }
        System.out.println();
        System.out.println();
        for (String result: Talendesign.getGeneInfo("NC_000002.12", 176087486, 176095937, "yi.chen901@gmail.com")){
            System.out.println(result);
        }

        String url = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi"
                + "?db=nuccore&id=" + "NC_000002.12"
                + "&seq_start=" + 176087486
                + "&seq_stop=" + 176095937
                + "&retmode=xml&strand=" + 1
                + "&tool=tal_tool&email=" + "yi.chen901@gmail.com";
        PrintWriter writer = new PrintWriter("exampleEfetchXML.txt", "UTF-8");
        writer.println(Talendesign.getWebContent(url, 1));
        writer.close();
    }
    
    @Test
    public void getSeqsTest(){
        Talendesign.getSeqNCBI(null, null, "yi.chen901@gmail.com", "NC_000002.12", 176087486, 176095937, 0);
    }
    
}
