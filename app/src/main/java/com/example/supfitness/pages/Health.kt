package com.example.supfitness.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.supfitness.R
import com.example.supfitness.data.PoundModel
import com.example.supfitness.database.DataManager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.*
import kotlin.collections.ArrayList

class Health : Fragment(R.layout.fragment_health) {

    private lateinit var dataManager: DataManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        val rootView: View = inflater.inflate(R.layout.fragment_health, container, false)
        dataManager = DataManager(this.context)

        val graphique: LineChart = rootView.findViewById(R.id.graphique)
        // Date actuelle
        val instantT: Calendar = Calendar.getInstance()
        val ex = instantT.timeInMillis

        // Gérer la date pour revenir de 30 jours
        val dateArround: Calendar = Calendar.getInstance() //Prends la date d'aujourd'hui
        val dateReset = setDateToZero(dateArround) //Mets la date à 00:00:00 et 30 jours en arrière
        val dateResetInMillis = dateReset.timeInMillis //Mets la date en milliseconds et de type long
        val getAllDateFrom30days = dataManager.takeAllValueInMouth(dateResetInMillis) //Sort de la database les données supérieurs à la journée supérieur
        if(getAllDateFrom30days.size != 0){

            val valueentry = ArrayList<Entry>()
            val dataEntrySet = addValuesInEntry(valueentry,getAllDateFrom30days)
            val lineDataSet = LineDataSet(dataEntrySet, "Pound")
            lineDataSet.color = resources.getColor(R.color.purple_200)
            val data = LineData(lineDataSet)
            graphique.data = data
        } else {
            graphique.visibility = View.GONE

        }

        return rootView
    }

    private fun addValuesInEntry(valueEntry: ArrayList<Entry>, dataArray: ArrayList<PoundModel>): ArrayList<Entry> {
        val value = arrayOf(dataArray)
        var start = 0F
        for(item in value){
            for(i in item){
                valueEntry.add(Entry(start,i.pound.toFloat()))
                start++
            }
        }
        return valueEntry
    }

    private fun setDateToZero(date: Calendar): Calendar {
        date.timeInMillis
        date.set(Calendar.MILLISECOND, 0)
        date.set(Calendar.SECOND, 0)
        date.set(Calendar.MINUTE, 0)
        date.set(Calendar.HOUR, 0)
        date.set(Calendar.AM_PM, 0)
        date.add(Calendar.DAY_OF_MONTH,-30)
        return date
    }
}