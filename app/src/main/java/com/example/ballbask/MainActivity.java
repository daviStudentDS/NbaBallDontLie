package com.example.ballbask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.btnPlayer);
        Button button2 = findViewById(R.id.btnTeam);
    }

    public void Pass1(View v) {
        Intent intent1 = new Intent(MainActivity.this, SearchPlayerActivity.class);
        startActivity(intent1);
    }

    public void Pass2(View v) {
        Intent intent2 = new Intent(MainActivity.this, SearchTeamsActivity.class);
        startActivity(intent2);
    }
}
