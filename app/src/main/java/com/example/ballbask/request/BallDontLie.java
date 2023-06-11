package com.example.ballbask.request;

import com.example.ballbask.model.Player;
import com.example.ballbask.model.Team;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BallDontLie {

    private static final String BASE_URL = "https://www.balldontlie.io/api/v1/";

    public static ArrayList<Team> getTeams() throws Exception {
        JSONArray response = RequestApi.get(BASE_URL + "teams").getJSONArray("data");
        ArrayList<Team> teams = new ArrayList<>();

        for (int i = 0; i < response.length(); i++) {
            JSONObject teamJSON = response.getJSONObject(i);
            int id = teamJSON.getInt("id");
            String fullName = teamJSON.getString("full_name");
            String city = teamJSON.getString("city");
            String conference = teamJSON.getString("conference");
            Team team = new Team(id, fullName, city, conference);
            teams.add(team);
        }

        return teams;
    }

    public static List<Team> searchTeams(String teamName) throws Exception {
        JSONArray response = RequestApi.get(BASE_URL + "teams?search=" + teamName).getJSONArray("data");
        List<Team> teams = new ArrayList<>();

        for (int i = 0; i < response.length(); i++) {
            JSONObject teamJSON = response.getJSONObject(i);
            int id = teamJSON.getInt("id");
            String fullName = teamJSON.getString("full_name");
            String city = teamJSON.getString("city");
            String conference = teamJSON.getString("conference");
            Team team = new Team(id, fullName, city, conference);
            teams.add(team);
        }

        return teams;
    }

    public static List<Player> searchPlayers(String playerName) throws Exception {
        JSONArray response = RequestApi.get(BASE_URL + "players?search=" + playerName).getJSONArray("data");
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < response.length(); i++) {
            JSONObject playerJSON = response.getJSONObject(i);
            int id = playerJSON.getInt("id");
            String firstName = playerJSON.getString("first_name");
            String lastName = playerJSON.getString("last_name");
            String position = playerJSON.getString("position");
            int teamId = playerJSON.getInt("team_id");
            Player player = new Player(id, firstName, lastName, position, teamId);
            players.add(player);
        }

        return players;
    }
}



