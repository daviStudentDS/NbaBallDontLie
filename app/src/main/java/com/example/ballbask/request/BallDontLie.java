package com.example.ballbask.request;

import android.util.Log;

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

    public static Player getPlayerById(int playerId) throws Exception{
        JSONObject json = RequestApi.get(BASE_URL + "players/" + playerId);

        int id = json.getInt("id");
        String firstName = json.getString("first_name");
        String lastName = json.getString("last_name");
        String position = json.getString("position");
        int teamId = json.getJSONObject("team").getInt("id");

        return new Player(id, firstName, lastName, position, teamId);
    }
    public static List<Player> searchPlayers(int playerId) throws Exception {
      //   JSONArray response = RequestApi.get(BASE_URL + "players/" + playerId);
        List<Player> players = new ArrayList<>();
        return players;
//
//        for (int i = 0; i < response.length(); i++) {
//            Log.d("PLAYER_ID", String.valueOf(i));
//
//            JSONObject playerJSON = response.getJSONObject(i);
//            int id = playerJSON.getInt("id");
//            String firstName = playerJSON.getString("first_name");
//            String lastName = playerJSON.getString("last_name");
//            String position = playerJSON.getString("position");
//            int teamId = playerJSON.getJSONObject("team").getInt("id");
//
//            Player player = new Player(id, firstName, lastName, position, teamId);
//            players.add(player);
//        }

    }
}









