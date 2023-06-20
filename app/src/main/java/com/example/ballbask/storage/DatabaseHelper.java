package com.example.ballbask.storage;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    public static final String COLUMN_TEAM_ID_FK = "team_id_fk";

    // Comando SQL para criar a tabela "history" com chave estrangeira
    private static final String SQL_CREATE_HISTORY_TABLE =
            "CREATE TABLE " + TABLE_HISTORY + "(" +
                    COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TEAM_NAME + " TEXT," +
                    COLUMN_TEAM_DESCRIPTION + " TEXT," +
                    COLUMN_TEAM_CONFERENCE_HISTORY + " TEXT," +
                    COLUMN_TEAM_ID_FK + " INTEGER," +
                    "FOREIGN KEY(" + COLUMN_TEAM_ID_FK + ") REFERENCES " + TABLE_TEAMS + "(" + COLUMN_TEAM_ID + "))";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria a tabela "teams" e "history"
        db.execSQL(SQL_CREATE_TEAMS_TABLE);
        db.execSQL(SQL_CREATE_HISTORY_TABLE);
    }

    public ArrayList<String> getAllRecords() {
        ArrayList<String> records = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_HISTORY, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String record = cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_ID_FK));
                records.add(record);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return records;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso haja uma atualização do banco de dados, você pode implementar aqui a lógica para migrar os dados existentes
        // OU SÓ EXCLUIR
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        onCreate(db);
    }

}
