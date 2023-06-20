package com.example.ballbask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.ballbask.loader.TeamLoader;
import com.example.ballbask.model.Team;
import com.example.ballbask.model.TelaExibir;

import com.example.ballbask.storage.DatabaseHelper;

import java.util.List;

public class SearchTeamsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<TelaExibir> {
    private EditText editTextTeamName;
    private Button buttonSearch;
    private TextView textViewTeamName;
    private TextView textViewCidade;
    private TextView textViewConferencia;

    private static final int LOADER_ID = 1;
    private LoaderManager.LoaderCallbacks<TelaExibir> loaderCallbacks; // Objeto global

    private DatabaseHelper databaseHelper;
    private ListView teamsListView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_teams);

        editTextTeamName = findViewById(R.id.editTextTeamName);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewTeamName = findViewById(R.id.textViewTeamName);
        textViewCidade = findViewById(R.id.textViewCidade);
        textViewConferencia = findViewById(R.id.textViewConferencia);

        loaderCallbacks = this; // Atribuir a instância atual do LoaderCallbacks

        databaseHelper = new DatabaseHelper(this);
        teamsListView = findViewById(R.id.listView);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String teamName = editTextTeamName.getText().toString().trim();

                if (!teamName.isEmpty()) {
                    performTeamSearch(teamName);
                } else {
                    Toast.makeText(SearchTeamsActivity.this, "Digite o nome do time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void performTeamSearch(String teamName) {
        Bundle args = new Bundle();
        args.putString("teamName", teamName);

        LoaderManager.getInstance(this).restartLoader(LOADER_ID, args, loaderCallbacks);


    }

    @Override
    public Loader<TelaExibir> onCreateLoader(int id, Bundle args) {
        String teamName = args.getString("teamName");
        return new TeamLoader(this, teamName);
    }
    @Override
    public void onLoadFinished(Loader<TelaExibir> loader, TelaExibir data) {
        if (data.hasError()) {
            setTeamNotFoundState();
            return;
        }

        List<Team> teams = data.getTimesEquipes();
        String teamName = editTextTeamName.getText().toString().trim();
        Team searchedTeam = findTeamByName(teams, teamName);

        if (searchedTeam != null) {
            setTeamData(searchedTeam);

        } else {
            setTeamNotFoundState();
        }
    }

    @Override
    public void onLoaderReset(Loader<TelaExibir> loader) {
        // Atribuir strings vazias diretamente aos campos de texto
        textViewTeamName.setText("");
        textViewCidade.setText("");
        textViewConferencia.setText("");
    }

    private void setTeamNotFoundState() {
        textViewTeamName.setText("Time não encontrado");
        textViewCidade.setText("");
        textViewConferencia.setText("");
    }

    private void setTeamData(Team team) {
        textViewTeamName.setText(team.getFullName());
        textViewCidade.setText(team.getCity());
        textViewConferencia.setText(team.getConference());
    }

    private Team findTeamByName(List<Team> teams, String teamName) {
        if (teams != null && !teams.isEmpty()) {
            for (Team team : teams) {
                if (team.getFullName().equalsIgnoreCase(teamName)) {
                    return team;
                }
            }
        }
        return null;
    }

}
