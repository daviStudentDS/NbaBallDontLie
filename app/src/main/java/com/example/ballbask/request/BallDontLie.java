package com.example.ballbask.request;

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
}