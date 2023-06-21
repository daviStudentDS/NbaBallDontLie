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

    public static final String TABLE_PLAYERHISTORY = "tb_historyplayer";
    public static final String COLUMN_PLAYERHISTORY_ID = "id";
    public static final String COLUMN_PLAYERHISTORY_NAME = "playername";

    public static final String TABLE_TEAMHISTORY = "tb_historyplayer";
    public static final String COLUMN_TEAMHISTORY_ID = "id";
    public static final String COLUMN_TEAMHISTORY_NAME = "teamname";

    // Comando SQL para criar a tabela "history" com chave estrangeira
    private static final String SQL_CREATE_PLAYERHISTORY_TABLE =
            "CREATE TABLE " + TABLE_PLAYERHISTORY + "(" +
                    COLUMN_PLAYERHISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_PLAYERHISTORY_NAME + " TEXT)";

    private static final String SQL_CREATE_TEAMHISTORY_TABLE =
            "CREATE TABLE " + TABLE_TEAMHISTORY + "(" +
                    COLUMN_TEAMHISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TEAMHISTORY_NAME + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria a tabela "teams" e "history"
        db.execSQL(SQL_CREATE_PLAYERHISTORY_TABLE);
        db.execSQL(SQL_CREATE_TEAMHISTORY_TABLE);
    }

    public Optional<ArrayList<History>> getAllPlayersHistory(){
        ArrayList<History> histories = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * FROM %s ORDER BY %s DESC",
                DatabaseHelper.TABLE_PLAYERHISTORY, DatabaseHelper.COLUMN_PLAYERHISTORY_ID);

        // Runs the query.
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            History history = new History();
            int index;

            // index = cursor.getColumnIndex(DB.COLUMN_HISTORY_ID);

            // MoneyFrom Field.
            index = cursor.getColumnIndex(DatabaseHelper.COLUMN_HISTORY_ID);
            history.id = cursor.getInt(index);

            // MoneyTo Field.
            index = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEAM_NAME);
            history.TeamName = cursor.getString(index);

            histories.add(history);
            cursor.moveToNext();
        }

        cursor.close();

        if(histories.size() == 0){
            Optional.empty();
        }

        return Optional.of(histories);
    }

    public boolean addPlayerHistory(String fullname){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues content = new ContentValues();

        content.put(COLUMN_PLAYERHISTORY_NAME, fullname);

        boolean success = db.insert(TABLE_PLAYERHISTORY, null, content) > 0;

        return success;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso haja uma atualização do banco de dados, você pode implementar aqui a lógica para migrar os dados existentes
        // OU SÓ EXCLUIR
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMHISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERHISTORY);
        onCreate(db);
    }

}
