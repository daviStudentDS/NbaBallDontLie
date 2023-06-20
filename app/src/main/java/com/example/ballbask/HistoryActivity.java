package com.example.ballbask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ballbask.storage.DatabaseSonHelper;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private DatabaseSonHelper databaseHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        databaseHelper = new DatabaseSonHelper(this);
        ArrayList<String> records = databaseHelper.getAllRecordsFromParent();

        adapter.addAll(records);
    }
}