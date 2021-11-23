package com.example.supfitness.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.sax.TextElementListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.supfitness.R
import com.example.supfitness.database.DataManager

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user: TextView = findViewById(R.id.userValue)
        val nameUser: EditText = findViewById(R.id.nameUser)
        val sendButton: Button = findViewById(R.id.sendButton)
        val dataManager = DataManager(this)



        sendButton.setOnClickListener{
            dataManager.insertValue(nameUser.toString(),1)
        }
    }
}