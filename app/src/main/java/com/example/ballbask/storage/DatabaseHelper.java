package com.example.ballbask.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "teams.db";
    private static final int DATABASE_VERSION = 1;

    // Nome da tabela e colunas
    public static final String TABLE_TEAMS = "teams";
    public static final String COLUMN_TEAM_ID = "team_id";
    public static final String COLUMN_TEAM_FULL_NAME = "team_full_name";
    public static final String COLUMN_TEAM_CITY = "team_city";
    public static final String COLUMN_TEAM_CONFERENCE = "team_conference";

    // Comando SQL para criar a tabela "teams"
    private static final String SQL_CREATE_TEAMS_TABLE =
            "CREATE TABLE " + TABLE_TEAMS + "(" +
                    COLUMN_TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TEAM_FULL_NAME + " TEXT," +
                    COLUMN_TEAM_CITY + " TEXT," +
                    COLUMN_TEAM_CONFERENCE + " TEXT)";

    // Nome da tabela e colunas
    public static final String TABLE_HISTORY = "history";
    public static final String COLUMN_HISTORY_ID = "history_id";
    public static final String COLUMN_TEAM_NAME = "team_name";
    public static final String COLUMN_TEAM_DESCRIPTION = "team_description";
    public static final String COLUMN_TEAM_CONFERENCE_HISTORY = "team_conference";

    // Comando SQL para criar a tabela "history"
    private static final String SQL_CREATE_HISTORY_TABLE =
            "CREATE TABLE " + TABLE_HISTORY + "(" +
                    COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TEAM_NAME + " TEXT," +
                    COLUMN_TEAM_DESCRIPTION + " TEXT," +
                    COLUMN_TEAM_CONFERENCE_HISTORY + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria a tabela "teams" e "history"
        db.execSQL(SQL_CREATE_TEAMS_TABLE);
        db.execSQL(SQL_CREATE_HISTORY_TABLE);
    }

    public long insertHistory(String teamName, String teamDescription, String conference) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEAM_NAME, teamName);
        values.put(COLUMN_TEAM_DESCRIPTION, teamDescription);
        values.put(COLUMN_TEAM_CONFERENCE_HISTORY, conference);

        long id = db.insert(TABLE_HISTORY, null, values);
        db.close();

        return id;
    }

    public Cursor getAllHistory() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_HISTORY, null, null, null, null, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso haja uma atualização do banco de dados, você pode implementar aqui a lógica para migrar os dados existentes
        // OU SÓ EXCLUIR
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }
}
