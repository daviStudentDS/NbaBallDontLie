package com.example.ballbask.model;

import java.util.ArrayList;
import java.util.List;

public class TreinadorExibir {
    private List<Treinador> treinadors;
    private Treinador treinador;
    private String mensagemErro;

    public boolean possuiErro() {
        return mensagemErro != null;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public void setJogadores(List<Treinador> jogadores) {
        this.treinadors = treinadors != null ? new ArrayList<>(treinadors) : new ArrayList<>();
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }


    public String getMensagemErro(){
        return mensagemErro;
    }
    public List<Treinador> getTreinadors() {
        return new ArrayList<>(treinadors);
    }

    public Treinador getPlayer(){
        return this.treinador;
    }

}
