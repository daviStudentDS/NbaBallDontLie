package com.example.ballbask.model;

public class Team {
    public int id;
    public String nomeCompleto;
    public String cidade;
    public String conferencia;

    public Team(int id, String nomeCompleto, String cidade, String conferencia){
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cidade = cidade;
        this.conferencia = conferencia;
    }
}
