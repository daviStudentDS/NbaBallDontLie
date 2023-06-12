package com.example.ballbask.model;

public class Team {
    private int id;
    private String fullName;
    private String city;
    private String conference;

    public Team(int id, String fullName, String city, String conference) {
        this.id = id;
        this.fullName = fullName;
        this.city = city;
        this.conference = conference;
    }

    public int getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }

    public String getCity() {
        return city;
    }

    public String getConference() {
        return conference;
    }

}