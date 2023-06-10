package com.example.ballbask.model;

public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private int teamId;

    public Player(int id, String firstName, String lastName, String position, int teamId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeam(Team team) {

    }
}