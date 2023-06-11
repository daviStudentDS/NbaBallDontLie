package com.example.ballbask.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerExibir {
    private List<Player> jogadores;
    private String mensagemErro;

    public boolean possuiErro() {
        return mensagemErro != null;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public void setJogadores(List<Player> jogadores) {
        this.jogadores = jogadores != null ? new ArrayList<>(jogadores) : new ArrayList<>();
    }

    public List<Player> getJogadores() {
        return new ArrayList<>(jogadores);
    }
}
