package com.example.ballbask.model;

import com.example.ballbask.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TelaExibir {
    private List<Team> timesEquipes;
    private String messageError;

    public boolean hasError() {
        return messageError != null;
    }

    public void setMessageError(String errorMessage) {
        messageError = errorMessage;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setTimesEquipes(List<Team> timesEquipes) {
        this.timesEquipes = timesEquipes != null ? new ArrayList<>(timesEquipes) : new ArrayList<>();
    }

    public List<Team> getTimesEquipes() {
        return new ArrayList<>(timesEquipes);
    }
}
