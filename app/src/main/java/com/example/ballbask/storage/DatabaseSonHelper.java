package com.example.ballbask.storage;
import android.content.Context;

import java.util.ArrayList;
public class DatabaseSonHelper extends DatabaseHelper {
    public DatabaseSonHelper(Context context) {
        super(context);
    }

    // Exemplo de método para obter os registros da classe mãe e retornar um ArrayList
    public ArrayList<String> getAllRecordsFromParent() {
        return getAllRecords();
    }
}
