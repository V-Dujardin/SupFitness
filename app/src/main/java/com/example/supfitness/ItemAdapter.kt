package com.example.supfitness

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supfitness.data.PoundModel
import com.example.supfitness.database.DataManager

class ItemAdapter(context: Context?) : RecyclerView.Adapter<ItemAdapter.ItemAdapterHolder>() {

    private var poundList: ArrayList<PoundModel> = ArrayList()
    private val context: Context? = null
    private var dataManager: DataManager = DataManager(context)



    fun addItems(items : ArrayList<PoundModel>){
        this.poundList = items
        notifyDataSetChanged()
    }

    class ItemAdapterHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private val getPound: TextView = view.findViewById(R.id.poundEdit)
        private var time: TextView = view.findViewById(R.id.timeEdit)
        private val date: TextView = view.findViewById(R.id.dateEdit)
        var buttonDelete: ImageButton = view.findViewById(R.id.buttonDelete)

        fun bindView(pound : PoundModel){
            date.text = pound.date
            time.text = pound.hour
            getPound.text = pound.pound.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.model_pound,parent,false))

    override fun onBindViewHolder(holder: ItemAdapterHolder, position: Int) {
        val pound = poundList[position]
        val id = poundList[position]
        holder.bindView(pound)
        holder.buttonDelete.setOnClickListener{
            getId(pound.id)
            this.poundList.removeAt(position)
            dataManager.deleteData(pound.id)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int{
        return poundList.size
    }

    fun getId(id: Int?): Int? {
        val i : String = id.toString()
        val e : Int = i.toInt()
        return id
    }




}