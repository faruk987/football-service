package org.acme;

import com.google.gson.Gson;
import org.acme.models.Match;
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

@Path("/matches")
public class MatchController {
    private JSONObject apidata = getMatchesFromApi();
    private List<Match> matches = jsonToMatchList(apidata.getJSONArray("events"));

    public MatchController() throws Exception {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMatches() throws Exception {
        String json = new Gson().toJson(matches);
        return json;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String getTeamById(@PathParam int id) throws Exception {
        try {
            String json = "";

            for (Match match : matches) {
                if (match.getId() == id) {
                    json = new Gson().toJson(match);
                }
            }

            return json;
        }catch (IndexOutOfBoundsException e){
            return "Wrong Id!";
        }
    }

    private JSONObject getMatchesFromApi() throws Exception {
        URL obj = new URL("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4337");
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

    private List<Match> jsonToMatchList(JSONArray values) throws JSONException {
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            JSONObject jsonObject = values.getJSONObject(i);
            String time = jsonObject.getString("strTime");
            result.add(new Match(
                    jsonObject.getInt("idEvent"),
                    jsonObject.getString("strHomeTeam"),
                    jsonObject.getString("strAwayTeam"),
                    jsonObject.getString("strLeague"),
                    time.substring(0, time.length() - 3),
                    jsonObject.getString("dateEvent")
                ));
        }
        return result;
    }
}
