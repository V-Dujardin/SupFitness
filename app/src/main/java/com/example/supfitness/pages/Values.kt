package com.example.supfitness.pages

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.supfitness.R
import com.example.supfitness.data.PoundModel
import com.example.supfitness.database.DataManager
import com.example.supfitness.databinding.FragmentValuesBinding
import com.example.supfitness.main.MainActivity


class Values : Fragment(R.layout.fragment_values) {


    private var _binding: FragmentValuesBinding? = null
    lateinit var dataManager :DataManager



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        dataManager = DataManager(this.context)
        super.onCreate(savedInstanceState)

        val rootView : View = inflater.inflate(R.layout.fragment_values,container,false)
        // Inflate the layout for this fragment
        val recupInputFromEditText: EditText = rootView.findViewById(R.id.nameUser)
        val sendButton: Button = rootView.findViewById(R.id.sendButton)
        val showData: Button = rootView.findViewById(R.id.showData)
        val dropData: Button = rootView.findViewById(R.id.dropData)


        sendButton.setOnClickListener {
            val inputUser = recupInputFromEditText.text
            val stringToIntInputUser = Integer.parseInt(inputUser.toString())
            addPoundInData(stringToIntInputUser)
        }

        showData.setOnClickListener {
            getAllValuesFromDatabase()
        }

        dropData.setOnClickListener {
            Log.d("data", "Delete all data")
        }

        return rootView
    }


    private fun addPoundInData(inputUser: Int) {

        if (inputUser == null || inputUser !is Int){
            Toast.makeText(requireContext(), "Merci de rentrer une valeur", Toast.LENGTH_SHORT).show()
        } else {
            val newPound = PoundModel(inputUser, 3)
            val addPoundInDatabase = dataManager.insertValueInDataBase(newPound)
            if (addPoundInDatabase > -1){
                Toast.makeText(requireContext(), "Poids ajouter", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Poids non ajouter", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun getAllValuesFromDatabase(){
        val value = dataManager.getAllValue()
        Log.e("data", "${value.size}")
    }



}

