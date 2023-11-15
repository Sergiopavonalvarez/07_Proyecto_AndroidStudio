package com.example.a07_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQlite extends SQLiteOpenHelper {

    public AdminSQlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE POLITICOS (NOMBRE VARCHAR(200) PRIMARY KEY, EDAD INT, ESTUDIOS VARCHAR(200))");
        db.execSQL("CREATE TABLE PARTIDOSPOLITICOS(" +
                "NOMBRE TEXT PRIMARY KEY," +
                "PRESIDENTE TEXT ," +
                "ESCAÑOS INT," +
                "FOREIGN KEY(PRESIDENTE) REFERENCES LIDERESPOLITICOS(NOMBRE))");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Partidos.db");
        onCreate(db);

    }
}
