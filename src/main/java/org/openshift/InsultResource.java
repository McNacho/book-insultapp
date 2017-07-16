package org.openshift;


import java.util.HashMap;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by nacho on 16/07/17.
 */

@RequestScoped
@Path("/insult")
public class InsultResource {

    @GET()
    @Produces("application/json")
    public HashMap<String, String> getInsult() {
        HashMap<String, String> theInsult = new HashMap<>();
        theInsult.put("insult", new InsultGenerator().generateInsult());
        return theInsult;
    }

}
