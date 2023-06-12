package com.example.ballbask.storage;

import android.content.Context;
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

    // Comando SQL para criar a tabela
    private static final String SQL_CREATE_TEAMS_TABLE =
            "CREATE TABLE " + TABLE_TEAMS + "(" +
                    COLUMN_TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TEAM_FULL_NAME + " TEXT," +
                    COLUMN_TEAM_CITY + " TEXT," +
                    COLUMN_TEAM_CONFERENCE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria a tabela teams
        db.execSQL(SQL_CREATE_TEAMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso haja uma atualização do banco de dados, você pode implementar aqui a lógica para migrar os dados existentes
        // ou simplesmente descartar a tabela antiga e criar uma nova
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        onCreate(db);
    }
}