package com.example.ballbask.loader;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ballbask.model.TreinadorExibir; // Renomeando a classe
import com.example.ballbask.request.BallDontLie;

public class TreinadorLoader extends AsyncTaskLoader<TreinadorExibir> { // Renomeando a classe

    private int treinadorId; // Renomeando a variável

    public TreinadorLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.treinadorId = args.getInt("treinadorId"); // Renomeando a variável
    }

    @Nullable
    @Override
    public TreinadorExibir loadInBackground() {
        TreinadorExibir treinadorExibir = new TreinadorExibir(); // Renomeando a classe

        try {
            treinadorExibir.setTreinador(BallDontLie.getTreinadorById(treinadorId)); // Renomeando a classe
        } catch (Exception e) {
            treinadorExibir.setMensagemErro(e.getMessage());
        }

        return treinadorExibir;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
