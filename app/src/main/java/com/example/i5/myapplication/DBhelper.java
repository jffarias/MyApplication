package com.example.i5.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by i5 on 12/03/2016.
 */
public class DBhelper extends SQLiteOpenHelper {
   String Tabla = "CREATE TABLE Tareas(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
           "Nombre Text)";
    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //CONSTRUCTOR

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAREAS);
        //onCreate(db);
    }
}
