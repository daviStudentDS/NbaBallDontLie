package com.example.ballbask;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.ballbask.loader.PlayerLoader;
import com.example.ballbask.model.Player;
import com.example.ballbask.model.PlayerExibir;

import java.util.List;
import java.util.Optional;

public class SearchPlayerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PlayerExibir> {
    private EditText editTextPlayerId;
    private Button buttonSearch;
    private TextView textViewPlayerName;
    private TextView textViewTeamName;
    private TextView textViewPosition;

    private static final int LOADER_ID = 1;
    private LoaderManager.LoaderCallbacks<PlayerExibir> loaderCallbacks;

    private static final String TAG = SearchPlayerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_player);

        editTextPlayerId = findViewById(R.id.editTextPlayerId);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewPlayerName = findViewById(R.id.textViewPlayerName);
        textViewTeamName = findViewById(R.id.textViewTeamName);
        textViewPosition = findViewById(R.id.textViewPosition);

        loaderCallbacks = this;

        buttonSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String playerIdText = editTextPlayerId.getText().toString().trim();

                    if (!TextUtils.isEmpty(playerIdText)) {
                        try {
                            int playerId = Integer.parseInt(playerIdText);
                            performPlayerSearch(playerId);
                        } catch (NumberFormatException e) {
                            Toast.makeText(SearchPlayerActivity.this, "Digite um ID de jogador válido", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SearchPlayerActivity.this, "Digite o ID do jogador", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void performPlayerSearch(int playerId) {
        Bundle args = new Bundle();
        args.putInt("playerId", playerId);

        LoaderManager.getInstance(this).restartLoader(LOADER_ID, args, loaderCallbacks);
    }

    @Override
    public Loader<PlayerExibir> onCreateLoader(int id, Bundle args) {
        return new PlayerLoader(this, args);
    }

    @Override
    public void onLoadFinished(Loader<PlayerExibir> loader, PlayerExibir data) {
        if (data.possuiErro()) {
            textViewPlayerName.setText(data.getMensagemErro());
            textViewTeamName.setText("");
            textViewPosition.setText("");
            return;
        }

        setPlayerData(data.getPlayer());

        /*
        List<Player> players = data.getJogadores();
        int playerId = Integer.parseInt(editTextPlayerId.getText().toString().trim());

        Optional<Player> searchPlayer = findPlayerById(players, playerId);

        if (searchPlayer.isPresent()) {
            setPlayerData(searchPlayer.get());
        } else {
            setPlayerNotFoundState();
        }
         */
    }

    @Override
    public void onLoaderReset(Loader<PlayerExibir> loader) {
        textViewPlayerName.setText("");
        textViewTeamName.setText("");
        textViewPosition.setText("");
    }

    private void setPlayerNotFoundState() {
        textViewPlayerName.setText("Jogador não encontrado");
        textViewTeamName.setText("");
        textViewPosition.setText("");
    }

    private void setPlayerData(Player player) {
        String playerName = player.getFirstName() + " " + player.getLastName();
        String teamId = String.valueOf(player.getTeamId());

        textViewPlayerName.setText(playerName);
        textViewTeamName.setText(teamId);
        textViewPosition.setText(player.getPosition());
    }

    private Optional<Player> findPlayerById(List<Player> players, int playerId) {
        for (Player player : players) {
            if (player.getId() == playerId) {
                return Optional.of(player);
            }
        }

        return Optional.empty();
    }
}
