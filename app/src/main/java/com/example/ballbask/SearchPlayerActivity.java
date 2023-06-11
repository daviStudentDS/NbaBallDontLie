package com.example.ballbask;

import android.os.Bundle;
import android.text.TextUtils;
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

public class SearchPlayerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PlayerExibir> {
    private EditText editTextPlayerName;
    private Button buttonSearch;
    private TextView textViewPlayerName;
    private TextView textViewTeamName;
    private TextView textViewPosition;

    private static final int LOADER_ID = 1;
    private LoaderManager.LoaderCallbacks<PlayerExibir> loaderCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_player);

        editTextPlayerName = findViewById(R.id.editTextPlayerName);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewPlayerName = findViewById(R.id.textViewPlayerName);
        textViewTeamName = findViewById(R.id.textViewTeamName);
        textViewPosition = findViewById(R.id.textViewPosition);

        loaderCallbacks = this;

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = editTextPlayerName.getText().toString().trim();

                if (!TextUtils.isEmpty(playerName)) {
                    performPlayerSearch(playerName);
                } else {
                    Toast.makeText(SearchPlayerActivity.this, "Digite o nome do jogador", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void performPlayerSearch(String playerName) {
        String[] names = TextUtils.split(playerName, " ");

        if (names.length >= 2) {
            String firstName = names[0].trim();
            String lastName = names[1].trim();

            Bundle args = new Bundle();
            args.putString("firstName", firstName);
            args.putString("lastName", lastName);

            LoaderManager.getInstance(this).restartLoader(LOADER_ID, args, loaderCallbacks);
        } else {
            Toast.makeText(SearchPlayerActivity.this, "Digite o primeiro e o segundo nome do jogador", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<PlayerExibir> onCreateLoader(int id, Bundle args) {
        String firstName = args.getString("first_Name");
        String lastName = args.getString("last_Name");
        return new PlayerLoader(this, firstName, lastName);
    }

    @Override
    public void onLoadFinished(Loader<PlayerExibir> loader, PlayerExibir data) {
        if (data.possuiErro()) {
            setPlayerNotFoundState();
            return;
        }

        List<Player> players = data.getJogadores();
        String playerName = editTextPlayerName.getText().toString().trim();
        String[] names = TextUtils.split(playerName, " ");
        String firstName = names[0].trim();
        String lastName = names[1].trim();
        Player searchedPlayer = findPlayerByName(players, firstName, lastName);

        if (searchedPlayer != null) {
            setPlayerData(searchedPlayer);
        } else {
            setPlayerNotFoundState();
        }
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
        String fullName = player.getFirstName() + " " + player.getLastName();

        textViewPlayerName.setText(fullName);
        textViewTeamName.setText(player.getTeamId());
        textViewPosition.setText(player.getPosition());
    }

    private Player findPlayerByName(List<Player> players, String firstName, String lastName) {
        if (players != null && !players.isEmpty()) {
            for (Player player : players) {
                if (TextUtils.equals(player.getFirstName(), firstName) && TextUtils.equals(player.getLastName(), lastName)) {
                    return player;
                }
            }
        }
        return null;
    }
}