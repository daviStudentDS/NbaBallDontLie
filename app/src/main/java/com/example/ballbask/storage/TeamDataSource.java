package com.example.ballbask.storage;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ballbask.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public TeamDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertTeam(String fullName, String city, String conference) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TEAM_FULL_NAME, fullName);
        values.put(DatabaseHelper.COLUMN_TEAM_CITY, city);
        values.put(DatabaseHelper.COLUMN_TEAM_CONFERENCE, conference);

        return database.insert(DatabaseHelper.TABLE_TEAMS, null, values);
    }

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_TEAMS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_TEAM_ID));
                @SuppressLint("Range") String fullName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TEAM_FULL_NAME));
                @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TEAM_CITY));
                @SuppressLint("Range") String conference = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TEAM_CONFERENCE));

                Team team = new Team(id, fullName, city, conference);
                teams.add(team);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return teams;
    }

    public void updateTeam(Team team) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TEAM_FULL_NAME, team.getFullName());
        values.put(DatabaseHelper.COLUMN_TEAM_CITY, team.getCity());
        values.put(DatabaseHelper.COLUMN_TEAM_CONFERENCE, team.getConference());

        String whereClause = DatabaseHelper.COLUMN_TEAM_ID + " = ?";
        String[] whereArgs = { String.valueOf(team.getId()) };

        database.update(DatabaseHelper.TABLE_TEAMS, values, whereClause, whereArgs);
    }

    public void deleteTeam(int teamId) {
        String whereClause = DatabaseHelper.COLUMN_TEAM_ID + " = ?";
        String[] whereArgs = { String.valueOf(teamId) };

        database.delete(DatabaseHelper.TABLE_TEAMS, whereClause, whereArgs);
    }
}