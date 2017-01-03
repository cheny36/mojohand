package mojohand;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import mojohand.Talendesign;


@Path("GeneInfo")
public class GeneInfoResources {
    
    @GET
    public JsonArray geneInfo(){
        JsonArrayBuilder results = Json.createArrayBuilder();
        
        String[] stuff = Talendesign.getGeneInfo(3239, "yi.chen901@gmail.com");
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("Accession number", stuff[0])
                .add("Gene start", stuff[1])
                .add("Gene stop", stuff[2]);
        if (stuff[3] != null){
            objectBuilder.add("Name", stuff[3]);
        }
        if (stuff[4] != null){
            objectBuilder.add("Other Names", stuff[4]);
        }
        if (stuff[5] != null){
            objectBuilder.add("Summary", stuff[5]);
        }
        results.add(objectBuilder);
        return results.build();
    }
}
