package com.example.ballbask.request;

import com.example.ballbask.model.Team;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BallDontLie {

    private static final String BASE_URL = "https://www.balldontlie.io/api/v1/";// DEFINIÇÃO DA API POR CONSTANTE
    public  static ArrayList<Team> requisitoTime() throws Exception {
        JSONArray response = RequestApi.get(BASE_URL + "teams").getJSONArray("data");
        ArrayList<Team> times = new ArrayList<>();

        for (int i = 0; i < response.length(); i++){
            JSONObject timeJSON = response.getJSONObject(i);
            int id = timeJSON.getInt("id");
            String nomeCompleto = timeJSON.getString("full_name");
            String cidade = timeJSON.getString("city");
            String conferencia = timeJSON.getString("conference");
            Team time = new Team(id, nomeCompleto, cidade, conferencia);
            times.add(time);
        }

        return times;
        //return RequestApi.get(BASE_URL + "teams");
    }
}