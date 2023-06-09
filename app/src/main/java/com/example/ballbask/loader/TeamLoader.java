package com.example.ballbask.loader;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ballbask.model.Team;
import com.example.ballbask.model.TelaExibir;
import com.example.ballbask.request.BallDontLie;

import java.util.ArrayList;


public class TeamLoader extends AsyncTaskLoader<TelaExibir> {
    private Bundle query;

    public TeamLoader(Context context, Bundle query) {
        super(context);
        this.query = query;
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
            telaExibir.timesEquipes = BallDontLie.requisitoTime();
            return telaExibir;

        } catch (Exception e) {
            TelaExibir telaExibir = new TelaExibir();
            telaExibir.message_Error = e.getMessage();
            return telaExibir;
        }


    }
}

