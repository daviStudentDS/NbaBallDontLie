package com.example.ballbask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ballbask.loader.TeamLoader;
import com.example.ballbask.model.TelaExibir;



public class SearchTeamsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<TelaExibir> {
    TextView teste1;
    Button buttonSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_teams);

        EditText editTextTeamName = findViewById(R.id.editTextTeamName);
        buttonSearch = findViewById(R.id.buttonSearch);


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @NonNull
    @Override
    public Loader<TelaExibir> onCreateLoader(int id, @Nullable Bundle args) {
        return new TeamLoader(this, args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<TelaExibir> loader, TelaExibir data) {
        Log.d("ma", "b");
        if(data.TemErro()){
            buttonSearch.setText(data.message_Error);

            return;
        }
        buttonSearch.setText(data.timesEquipes.get(0).nomeCompleto);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<TelaExibir> loader) { /* Not implmented */ }



    }
