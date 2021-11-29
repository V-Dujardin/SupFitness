package com.example.supfitness.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "fitness";
    private static final int DATABASE_VERSION = 1;
    private static final String FITNESS_KEY = "fitness_id";
    private static final String FITNESS_POUND = "pound";
    private static final String FITNESS_NAME = "name";



    public DataManager(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String commandSql = "CREATE TABLE " + DATABASE_NAME + "(" + FITNESS_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FITNESS_NAME + " TEXT," + FITNESS_POUND + " TEXT)";
        db.execSQL(commandSql);
        Log.i("data","Base de donnée créer");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop Table if exists " + DATABASE_NAME);

    }

    public void insertValue(String nameUserUpdate, String poundUserUpdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FITNESS_NAME,nameUserUpdate);
        values.put(FITNESS_POUND,poundUserUpdate);
        db.insert(DATABASE_NAME, null,values);
    }

    public void updateValue(String nameUserUpdate, String poundUserUpdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FITNESS_NAME,nameUserUpdate);
        values.put(FITNESS_POUND,poundUserUpdate);
        db.update(DATABASE_NAME,values, "name=?", new String[]{nameUserUpdate});
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ DATABASE_NAME);
    }

    public Cursor alldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from fitness",null);
        return cursor;
    }
}
