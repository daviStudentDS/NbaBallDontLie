package com.example.ballbask;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> playerFullName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.activity_history, R.id.textViewTeamName, playerFullName);
        listView.setAdapter(adapter);

        populateHistory();
    }

    private void populateHistory() {
        // Simulating the history data
        ArrayList<String> records = new ArrayList<>();
        records.add("History 1");
        records.add("History 2");
        records.add("History 3");

        playerFullName = records;
        adapter.notifyDataSetChanged();
    }
}