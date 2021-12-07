package com.example.supfitness.pages

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
    private lateinit var recupInputFromEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        val rootView: View = inflater.inflate(R.layout.fragment_values, container, false)
        recupInputFromEditText = rootView.findViewById(R.id.nameUser)
        val sendButton: Button = rootView.findViewById(R.id.sendButton)
        dataManager = DataManager(this.context)

        // Recycler view
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = ItemAdapter(context)
        recyclerView.adapter = adapter

        // Show data
        getValuesPound()

        sendButton.setOnClickListener {
            if (dataManager.checkIfDateExist()){
                if (recupInputFromEditText.text.isNotEmpty()) {
                    val inputUser = recupInputFromEditText.text
                    val changeInputUserIntoInt: Int = Integer.parseInt(inputUser.toString())
                    addPoundInData(changeInputUserIntoInt)
                    getValuesPound()
                    recupInputFromEditText.setText("")

                } else {
                    Toast.makeText(requireContext(), "Merci de rentrer une valeur", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), "Une seule valeur par jour", Toast.LENGTH_LONG)
                    .show()
            }
        }
        return rootView
    }


    private fun addPoundInData(inputUser: Int) {
        val current = LocalDateTime.now() // Heure actuelle
        val formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm")
        val formatted = current.format(formatter).toString()
        val dateCompleteFormatter = setUpTime(formatted)
        val newPound = PoundModel(id,inputUser, dateCompleteFormatter , formatted.takeLast(5))
        val addPoundInDatabase = dataManager.insertValueInDataBase(newPound)
        if (addPoundInDatabase > -1) {
            Toast.makeText(requireContext(), "Poids ajouter", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpTime(time: String): String {
        val mouth = changeEditMouth(time.substring(8, 10))
        val day = time.substring(5, 7)
        val year = time.take(4)
        return "$day $mouth $year"
    }

    private fun changeEditMouth(mouth: String): String {
        when (mouth) {
            "01" -> return "janv."
            "02" -> return "frév."
            "03" -> return "mars."
            "04" -> return "mai."
            "05" -> return "avril."
            "06" -> return "juin."
            "07" -> return "juil."
            "08" -> return "août."
            "09" -> return "sept."
            "10" -> return "oct."
            "11" -> return "nov."
            "12" -> return "dec."
        }
        return mouth
    }

    private fun getValuesPound() {
        val poundList = dataManager.getAllValue()
        adapter?.addItems(poundList)
    }


}

