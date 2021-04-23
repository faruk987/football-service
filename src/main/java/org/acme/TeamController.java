package org.acme;

import com.google.gson.Gson;
import org.acme.models.Team;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Path("/teams")
public class TeamController {
    private JSONObject apidata = getTeamsFromApi();
    private List<Team> teams = jsonToTeamList(apidata.getJSONArray("teams"));

    public TeamController() throws Exception {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String getTeamById(@PathParam int id) throws Exception {
        try {
            String json = "";

            for (Team team : teams) {
                if (team.getId() == id) {
                     json = new Gson().toJson(team);

                }
            }

            return json;
        }catch (IndexOutOfBoundsException e){
            return "There are no more teams in the Eredivisie";
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTeams() throws Exception {
        String json = new Gson().toJson(teams);
        return json;
    }

    private JSONObject getTeamsFromApi() throws Exception {
        URL obj = new URL("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=Dutch%20Eredivisie");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject myResponse = new JSONObject(response.toString());

        return myResponse;
    }

    private List<Team> jsonToTeamList(JSONArray values) throws JSONException {
        List<Team> result = new ArrayList<>();
        for (int i = 0; i < values.length(); i++) {
        JSONObject jsonObject = values.getJSONObject(i);
            result.add(new Team(
                    jsonObject.getInt("idTeam"),
                    jsonObject.getString("strTeam"),
                    jsonObject.getString("strStadium"),
                    jsonObject.getString("strDescriptionEN"),
                    jsonObject.getString("strTeamBadge")
                    ));
        }
        return result;
    }
}
