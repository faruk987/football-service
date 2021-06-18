package org.acme;

import com.google.gson.Gson;
import org.acme.services.TeamDataService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/teams")
public class TeamController {

    @Inject
    TeamDataService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String getTeamById(@PathParam int id) throws Exception {
        try {
            String json = "";

            if (service.getTeamById(id) != null) json = new Gson().toJson(service.getTeamById(id));

            return json;
        }catch (IndexOutOfBoundsException e){
            return "There are no more teams in the Eredivisie";
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTeams() throws Exception {
        return new Gson().toJson(service.getTeamList());
    }
}
