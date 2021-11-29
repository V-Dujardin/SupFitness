package com.example.supfitness.home

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.sax.TextElementListener
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.supfitness.R
import com.example.supfitness.database.DataManager

class MainActivity : AppCompatActivity() {

    private val dataManager = DataManager(this)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user: TextView = findViewById(R.id.userValue)
        val nameUser: EditText = findViewById(R.id.nameUser)
        val sendButton: Button = findViewById(R.id.sendButton)
        val cursor = dataManager.alldata()

        sendButton.setOnClickListener{
            val name = nameUser .text
            Log.i("nameUser", name.toString())
            dataManager.insertValue(name.toString(),"1")
            showData(cursor)
        }




    }


    fun showData(cursor:Cursor) {
        if (cursor.count == 0){
            Toast.makeText(applicationContext,"NO DATA",Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()){
                Toast.makeText(applicationContext,"First name :"+cursor.getString(1),Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext,"First name :"+cursor.getString(1),Toast.LENGTH_SHORT).show()
            }
        }
    }

}