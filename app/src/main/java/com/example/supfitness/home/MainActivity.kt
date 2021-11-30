package com.example.supfitness.home

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.supfitness.R
import com.example.supfitness.data.PoundModel
import com.example.supfitness.database.DataManage
import com.example.supfitness.database.DataManager

class MainActivity : AppCompatActivity() {

    private val dataManager = DataManager(this)
    private val dataManage = DataManage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recupInputFromEditText: EditText = findViewById(R.id.nameUser)
        val sendButton: Button = findViewById(R.id.sendButton)
        val showData: Button = findViewById(R.id.showData)
        val dropData: Button = findViewById(R.id.dropData)


        sendButton.setOnClickListener {
            val inputUser = recupInputFromEditText.text
            val stringToIntInputUser = Integer.parseInt(inputUser.toString())
            addPoundInData(stringToIntInputUser)
        }

        showData.setOnClickListener {
            getAllValuesFromDatabase()
        }

        dropData.setOnClickListener {
            dataManager.deleteAllData()
            Log.d("data", "Delete all data")
        }
    }


    private fun addPoundInData(inputUser: Int) {

        if (inputUser == null){
            Toast.makeText(this, "Merci de rentrer une valeur", Toast.LENGTH_SHORT).show()
        } else {
            val newPound = PoundModel(inputUser, 3)
            val addPoundInDatabase = dataManage.insertValueInDataBase(newPound)
            if (addPoundInDatabase > -1){
                Toast.makeText(this, "Poids ajouter", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Poids non ajouter", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun getAllValuesFromDatabase(){
        val value = dataManage.getAllValue()
        Log.e("data", "${value.size}")
    }
}
