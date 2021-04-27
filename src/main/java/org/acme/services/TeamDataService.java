package org.acme.services;

import org.acme.models.Team;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class TeamDataService {

    @Inject
    ApiMethodService service;

    @CacheResult(cacheName = "teams-cache")
    public List<Team> getTeamList() throws Exception {
        return jsonToTeamList(getTeamsFromApi().getJSONArray("teams"));
    }

    public Team getTeamById(int id) throws Exception {
        for (Team team : getTeamList()) {
            if (team.getId() == id) {
                return team;
            }
        }
        return null;
    }

    private JSONObject getTeamsFromApi() throws Exception {
        URL url = new URL("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=Dutch%20Eredivisie");

        return service.getObjectFromApi(url);
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
