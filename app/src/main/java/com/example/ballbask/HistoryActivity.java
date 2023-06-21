package com.example.ballbask;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ballbask.model.History;
import com.example.ballbask.storage.DatabaseHelper;

import java.util.ArrayList;
import java.util.Optional;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<History> teamHistory;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.listView);
        teamHistory = new ArrayList<History>();
        String[] teamName = new String[0];
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamName);
        listView.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(this);
        populateHistory();
    }

    private void populateHistory() {
        Optional<ArrayList<History>> historyOptional = databaseHelper.getAllTeamsHistory();

        if (historyOptional.isPresent()) {
            ArrayList<History> historyList = historyOptional.get();
            teamHistory.addAll(historyList);
            adapter.notifyDataSetChanged();
        }
    }
}