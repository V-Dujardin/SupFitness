package com.example.supfitness

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supfitness.data.PoundModel

class ItemAdapter() : RecyclerView.Adapter<ItemAdapter.ItemAdapterHolder>() {

    private var poundList: ArrayList<PoundModel> = ArrayList()

    fun addItems(items : ArrayList<PoundModel>){
        this.poundList = items
        notifyDataSetChanged()
    }

    class ItemAdapterHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val pound: TextView = view.findViewById(R.id.poundEdit)
//        private var buttonEdit = view.findViewById<Button>(R.id.buttonEdit)
        val time: TextView = view.findViewById(R.id.timeEdit)
        //val date: TextView = view.findViewById(R.id.dateEdit)

        //private var buttonDelete = view.findViewById<Button>(R.id.buttonDelete)

        fun bindView(pound : PoundModel){
            time.text = pound.time.toString()
            this.pound.text = pound.pound.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.model_pound,parent,false))

    override fun onBindViewHolder(holder: ItemAdapterHolder, position: Int) {
        val pound = poundList[position]
        holder.bindView(pound)

        /*val item = dataset[position]
        holder.time.text = item.time.toString()
        holder.pound.text = item.pound.toString()

*/
        //holder.imageView.setImageResource(getResourceId(item.imageCocktail))
        /*holder.imageView.setOnClickListener{
            Log.i("nameCocktail", item.toString())
            val DetailsActivity = Intent(context, DetailsCocktails::class.java)
            DetailsActivity.putExtra("keyCocktailName",item.nameCocktail)
            DetailsActivity.putExtra("keyCocktailImage",item.imageCocktail)

            ContextCompat.startActivity(context, DetailsActivity, null)

        }*/
    }

    override fun getItemCount(): Int{
        return poundList.size
    }

}