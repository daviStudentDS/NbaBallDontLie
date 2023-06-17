package com.example.ballbask.model;

public class History {
    private final int id;
    private String teamFullName;


    private String teamCity;
    private String teamConference;

    public History(String teamName, String description) {
        this.id = 0;
        this.teamFullName = "";
        this.teamCity = "";
        this.teamConference = "";
    }

    public History(int id, String teamFullName, String teamCity, String teamConference) {
        this.id = id;
        this.teamFullName = teamFullName;
        this.teamCity = teamCity;
        this.teamConference = teamConference;
    }

    public int getId() {
        return id;
    }

    public String getTeamFullName() {
        return teamFullName;
    }

    public void setTeamFullName(String teamFullName) {
        this.teamFullName = teamFullName;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamConference() {
        return teamConference;
    }

    public void setTeamConference(String teamConference) {
        this.teamConference = teamConference;
    }

    public int getTeamName() {
        return getTeamName();
    }

    public String getDescription() {
        return getDescription();
    }
}