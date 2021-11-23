package com.example.supfitness.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "fitness.db";
    private static final int DATABASE_VERSION = 1;

    public DataManager(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String commandSql = "create table fitness (" +
                            "idValue INTEGER primary key autoincrement," +
                            "nameUser VARCHAR(25) not null," +
                            "poundUser INTEGER not null" +
                            //"timeDay integrer not null" +
                            ")";
        sqLiteDatabase.execSQL(commandSql);
        Log.i("Database", "database create");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertValue(String nameUserUpdate, Integer poundUserUpdate){
        String nameUser = nameUserUpdate;
        Integer poundUser = poundUserUpdate;
        String commandSql = "insert into fitness (nameUser,poundUser) values (nameUser,poundUser)";
        this.getWritableDatabase().execSQL(commandSql);
        Log.i("Database", "insert value");
    }
}
