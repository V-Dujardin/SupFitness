package com.example.supfitness.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val arrayData = dataManager.getAllValue()


        // Date actuelle
        val instantT: Calendar = Calendar.getInstance()
        val ex = instantT.timeInMillis
        val dateArrondie: Calendar = Calendar.getInstance()
        dateArrondie.timeInMillis
        dateArrondie.set(Calendar.MILLISECOND, 0)
        dateArrondie.set(Calendar.SECOND, 0)
        dateArrondie.set(Calendar.MINUTE, 0)
        dateArrondie.set(Calendar.HOUR, 0)
        dateArrondie.set(Calendar.AM_PM, 0)
        dateArrondie.add(Calendar.DAY_OF_MONTH,-30)
        val miliDateMoins30 = dateArrondie.timeInMillis
        // Prendre tout les longs supérieurs à miliDateMoins30
        // 12 decembre 2021 Final
        // Point de départ = denierre valeur de la base save la plus longue
        // Sort le 7 décembre 2021 17:30 en long
        // Convertie en Calendar
        // Met le 7 decembre à 00:00:00
        // On fait - 30 jours
        // En remet en mili
        // En base tu prends tout ce qu'il a au dessus

        // Moins 30 jours


        val graphique: LineChart = rootView.findViewById(R.id.graphique)


        val valueentry = ArrayList<Entry>()
        valueentry.add(Entry(50F, 1F))
        valueentry.add(Entry(55f, 2F))
        valueentry.add(Entry(60f, 3F))
        valueentry.add(Entry(65f, 4F))

        val lineDataSet = LineDataSet(valueentry, "First")
        lineDataSet.color = resources.getColor(R.color.purple_200)
        val data = LineData(lineDataSet)
        graphique.data = data

        fun addValuesInEntry(valueEntry: ArrayList<Entry>,dataArray: ArrayList<PoundModel>){
            val array = dataArray
            for (item in arrayData){
                valueEntry.add(Entry())
            }
        }

        return rootView
    }
}