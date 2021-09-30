package com.example.myrestaurantapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Search_Adapter : RecyclerView.Adapter<Search_Adapter.Recycl_holder> {
    var layoutid: Int = 0
    var searchList: ArrayList<SearchDomain>
    var context: Context

    constructor(layoutid: Int, array: ArrayList<SearchDomain>, context: Context) {
        this.layoutid = layoutid
        this.searchList = array
        this.context = context
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Recycl_holder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutid, parent, false)
        return Recycl_holder(view)
    }

    override fun onBindViewHolder(holder: Recycl_holder, position: Int) {

        val meals = searchList.get(position) // return Meals
        holder.imageMeal.setImageResource(meals.photo)
        holder.titleMeal.setText(meals.title)
        holder.price.setText(meals.price.toString())
    }

    fun filterList(filteredList: ArrayList<SearchDomain>) {
        searchList = filteredList
        notifyDataSetChanged()
    }

    inner class Recycl_holder : RecyclerView.ViewHolder {
        var imageMeal: ImageView
        var titleMeal: TextView
        var price: TextView

        constructor(itemView: View) : super(itemView) {

            imageMeal = itemView.findViewById(R.id.photo)
            titleMeal = itemView.findViewById(R.id.title)
            price = itemView.findViewById(R.id.price)

            itemView.setOnClickListener {
                val meals = searchList.get(adapterPosition) // return Meals
                val i = Intent(context, ShowDetailsActivity::class.java)
                i.putExtra("object", meals)
                context.startActivity(i)
            }
        }
    }
}
