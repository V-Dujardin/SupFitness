package com.example.supfitness.home

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.supfitness.R
import com.example.supfitness.database.DataManager
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val dataManager = DataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user: TextView = findViewById(R.id.userValue)
        val nameUser: EditText = findViewById(R.id.nameUser)
        val sendButton: Button = findViewById(R.id.sendButton)
        val showData: Button = findViewById(R.id.showData)
        val dropData: Button = findViewById(R.id.dropData)
        val cursor = dataManager.alldata()

        sendButton.setOnClickListener {
            val name = nameUser.text
            Log.i("nameUser", name.toString())
            dataManager.insertValue(name.toString(), "1")
            dataManager.updateValue(name.toString(), "1")
        }

        showData.setOnClickListener {
            showData(cursor)
            Log.d("data", "Show data")
            return@setOnClickListener

        }

        dropData.setOnClickListener {
            dataManager.deleteAllData()
            Log.d("data", "Delete all data")
        }


    }


    private fun showData(cursor: Cursor) {
        if (cursor.count == 0) {
            Toast.makeText(applicationContext, "NO DATA", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()) {
                Toast.makeText(
                    applicationContext,
                    "First name :" + cursor.getString(1),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}