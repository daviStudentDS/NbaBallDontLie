package com.example.ballbask.loader;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ballbask.model.TelaExibir;
import com.example.ballbask.request.BallDontLie;

public class TeamLoader extends AsyncTaskLoader<TelaExibir> {
    private String teamName;

    public TeamLoader(Context context, String teamName) {
        super(context);
        this.teamName = teamName;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    @Nullable
    public TelaExibir loadInBackground() {
        try {
            TelaExibir telaExibir = new TelaExibir();
            telaExibir.setTimesEquipes(BallDontLie.searchTeams(teamName));
            return telaExibir;
        } catch (Exception e) {
            TelaExibir telaExibir = new TelaExibir();
            telaExibir.setMessageError(e.getMessage());
            return telaExibir;
        }
    }
}