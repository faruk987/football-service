package org.acme.services;

import io.quarkus.cache.CacheResult;
import org.acme.models.Match;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MatchDataService {
    private final int MAX_MATCHES = 9;

    @Inject
    ApiMethodService service;

    @CacheResult(cacheName = "matches-cache")
    public List<Match> getMatchesList() throws Exception {
        return jsonToMatchList(getMatchesFromApi().getJSONArray("events"));
    }

    public Match getMatchById(int id) throws Exception {
        for (Match match : getMatchesList()) {
            if (match.getId() == id) {
                return match;
            }
        }
        return null;
    }

    private JSONObject getMatchesFromApi() throws Exception {
        URL url = new URL("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4337");

        return service.getObjectFromApi(url);
    }

    private List<Match> jsonToMatchList(JSONArray values) throws JSONException {
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < MAX_MATCHES; i++) {
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
