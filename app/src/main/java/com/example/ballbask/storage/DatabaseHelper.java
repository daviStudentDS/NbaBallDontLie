package com.example.ballbask.storage;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ballbask.model.History;

import java.util.ArrayList;
import java.util.Optional;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "teams.db";
    private static final int DATABASE_VERSION = 1;

    // Nome da tabela e colunas
    public static final String TABLE_HISTORY = "history";
    public static final String COLUMN_HISTORY_ID = "history_id";
    public static final String COLUMN_TEAM_NAME = "team_name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria a tabela "history"
        String SQL_CREATE_HISTORY_TABLE =
                "CREATE TABLE " + TABLE_HISTORY + "(" +
                        COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_TEAM_NAME + " TEXT)";

        db.execSQL(SQL_CREATE_HISTORY_TABLE);
    }

    public Optional<ArrayList<History>> getAllTeamsHistory() {
        ArrayList<History> histories = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_HISTORY +
                " ORDER BY " + COLUMN_HISTORY_ID + " DESC";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_HISTORY_ID));
                @SuppressLint("Range") String teamName = cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NAME));

                History history = new History(teamName);
                histories.add(history);
            } while (cursor.moveToNext());
        }
        cursor.close();

        if (histories.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(histories);
        }
    }


    public boolean addTeamHistory(String teamName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEAM_NAME, teamName);

        long result = db.insert(TABLE_HISTORY, null, values);

        return result != -1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso haja uma atualização do banco de dados, você pode implementar aqui a lógica para migrar os dados existentes
        // OU SÓ EXCLUIR
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }
}