package com.example.supfitness.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.supfitness.data.PoundModel

class DataManager(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "fitnessDB"
        private const val DATABASE_VERSION = 1
        private const val TABLEAU_FITNESS = "fitness_pound"
        private const val ID = "id_pound"
        private const val NAME = "pound"
        private const val TIME = "time"
        private const val HOUR = "hour"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val generateTable =
            ("CREATE TABLE $TABLEAU_FITNESS($ID INTEGER PRIMARY KEY AUTOINCREMENT,$NAME TEXT,$TIME TEXT, $HOUR TEXT)")
        db?.execSQL(generateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLEAU_FITNESS")
        onCreate(db)
    }

    fun insertValueInDataBase(pound: PoundModel): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, pound.pound)
        values.put(TIME, pound.date)
        values.put(HOUR, pound.hour)
        val insert = db.insert(TABLEAU_FITNESS, null, values)
        db.close()
        return insert
    }

    @SuppressLint("Range")
    fun getAllValue(): ArrayList<PoundModel> {
        val poundList: ArrayList<PoundModel> = ArrayList()
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLEAU_FITNESS", null)
        var pound: Int
        var id: Int
        var date: String
        var hour: String

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    pound = cursor.getInt(cursor.getColumnIndex("pound"))
                    date = cursor.getString(cursor.getColumnIndex("time"))
                    hour = cursor.getString(cursor.getColumnIndex("hour"))
                    id = cursor.getInt(cursor.getColumnIndex("id_pound"))
                    val newPound = PoundModel(id, pound, date, hour)
                    poundList.add(0,newPound)
                } while (cursor.moveToNext())

            }
        }

        return poundList
    }

    fun deleteData(id: Int?): Int {

        val db = this.writableDatabase
        val cursor = db.delete(TABLEAU_FITNESS, "$ID = ?", arrayOf(id.toString()))
        db.close()
        return cursor
    }
}
