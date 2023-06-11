package com.example.ballbask.loader;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ballbask.model.PlayerExibir;
import com.example.ballbask.request.BallDontLie;

public class PlayerLoader extends AsyncTaskLoader<PlayerExibir> {
    private String playerName;

    public PlayerLoader(@NonNull Context context, String firstName, String lastName) {
        super(context);
        this.playerName = firstName + " " + lastName;
    }

    @Nullable
    @Override
    public PlayerExibir loadInBackground() {
        PlayerExibir playerExibir = new PlayerExibir();

        try {
            playerExibir.setJogadores(BallDontLie.searchPlayers(playerName));
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