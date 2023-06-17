package com.example.ballbask.loader;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ballbask.model.PlayerExibir;
import com.example.ballbask.request.BallDontLie;

public class PlayerLoader extends AsyncTaskLoader<PlayerExibir> {


    private int playerId;

    public PlayerLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.playerId = args.getInt("playerId");
    }

    @Nullable
    @Override
    public PlayerExibir loadInBackground() {
        PlayerExibir playerExibir = new PlayerExibir();

        try {
            playerExibir.setJogador(BallDontLie.getPlayerById(playerId));
        } catch (Exception e) {
            playerExibir.setMensagemErro(e.getMessage());
        }

        return playerExibir;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}