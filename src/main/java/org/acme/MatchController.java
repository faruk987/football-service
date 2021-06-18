package org.acme;

import com.google.gson.Gson;
import org.acme.services.MatchDataService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/matches")
public class MatchController {

    @Inject
    MatchDataService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMatches() throws Exception {
        String json = new Gson().toJson(service.getMatchesList());
        return json;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String getMatchById(@PathParam int id) throws Exception {
        try {
            String json = "";

            if (service.getMatchById(id) != null) json = new Gson().toJson(service.getMatchById(id));

            return json;
        }catch (IndexOutOfBoundsException e){
            return "Wrong Id!";
        }
    }
}
