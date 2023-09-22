package com.example.ballbask.model;

public class Treinador
{
    public int Id;
    private String Name;
    public int ExperienceYears ;
    public int TeamId;
    public Team Team;

    public Treinador(int id, String name, int experienceYears, int teamId) {
    }

    public void Player(int id, String name, int experienceYears, int teamId, Team team)
    {
        Id = id;
        Name = name;
        ExperienceYears = experienceYears;
        TeamId = teamId;
        Team = team;
    }

    public String getName() {
        return Name;
    }

    public int getExperienceYears() {
        return ExperienceYears;
    }

    public int getTeamId() {
        return TeamId;
    }


    public int getId() { return Id; }
}