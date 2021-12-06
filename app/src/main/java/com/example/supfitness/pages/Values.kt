package com.example.supfitness.pages

import android.icu.number.Precision.integer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supfitness.ItemAdapter
import com.example.supfitness.R
import com.example.supfitness.data.PoundModel
import com.example.supfitness.database.DataManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Values : Fragment(R.layout.fragment_values) {


    private lateinit var dataManager: DataManager
    private lateinit var recyclerView: RecyclerView
    private var adapter: ItemAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)



        val rootView: View = inflater.inflate(R.layout.fragment_values, container, false)
        val recupInputFromEditText: EditText = rootView.findViewById(R.id.nameUser)
        val sendButton: Button = rootView.findViewById(R.id.sendButton)
        val showData: Button = rootView.findViewById(R.id.showData)
        val dropData: Button = rootView.findViewById(R.id.dropData)
        dataManager = DataManager(this.context)

        // Recycler view
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = ItemAdapter()
        recyclerView.adapter = adapter





        // Show data
        getValuesPound()


        sendButton.setOnClickListener {
            if (recupInputFromEditText.text.isNotEmpty()) {
                val inputUser = recupInputFromEditText.text
                val stringToIntInputUser = Integer.parseInt(inputUser.toString())
                addPoundInData(stringToIntInputUser)
                getValuesPound()
                recupInputFromEditText.setText("")

            } else {
                Toast.makeText(requireContext(), "Merci de rentrer une valeur", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        showData.setOnClickListener {
            getValuesPound()
        }

        dropData.setOnClickListener {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)
            Log.d("data", formatted.toString())
        }

        return rootView
    }


    private fun addPoundInData(inputUser: Int) {
        val newPound = PoundModel(inputUser, 3)
        val addPoundInDatabase = dataManager.insertValueInDataBase(newPound)
        if (addPoundInDatabase > -1) {
            Toast.makeText(requireContext(), "Poids ajouter", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAllValuesFromDatabase() {
        val value = dataManager.getAllValue()
        Log.e("data", "${value.size}")
    }

    private fun getValuesPound(){
        val poundList  = dataManager.getAllValue()
        getAllValuesFromDatabase()
        adapter?.addItems(poundList)
    }


}

