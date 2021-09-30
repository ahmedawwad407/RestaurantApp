package com.example.myrestaurantapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Prefernses_Adapter : RecyclerView.Adapter<Prefernses_Adapter.Recycl_holder> {
    var managementPrefernses: ManagementPrefernses
    var changeNumberItemsListener: ChangeNumberItemsListener

    var layoutid: Int = 0
    var foodList: ArrayList<FoodDomain>
    var context: Context

    constructor(
        layoutid: Int,
        array: ArrayList<FoodDomain>,
        context: Context,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        this.layoutid = layoutid
        this.foodList = array
        this.context = context
        this.managementPrefernses = ManagementPrefernses(context)
        this.changeNumberItemsListener = changeNumberItemsListener
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Recycl_holder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutid, parent, false)
        return Recycl_holder(view)
    }

    override fun onBindViewHolder(holder: Recycl_holder, position: Int) {
        val meal = foodList.get(position)
        holder.imageMeal.setImageResource(meal.photo)
        holder.titleMeal.setText(meal.title)
        holder.descMeal.setText(meal.descreption)
        holder.price.setText(meal.price.toString())

        holder.orderNow.setOnClickListener {
            val i = Intent(context, ShowDetailsActivity::class.java)
            i.putExtra("object", meal)
            context.startActivity(i)
        }

        holder.deleteToFav.setOnClickListener {
            managementPrefernses.deleteToFav(
                foodList,
                position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.changed()
                    }
                }
            )
        }
    }
    inner class Recycl_holder : RecyclerView.ViewHolder {
        var imageMeal: ImageView
        var titleMeal: TextView
        var descMeal: TextView
        var price: TextView
        var orderNow: Button
        var deleteToFav: ImageView

        constructor(itemView: View) : super(itemView) {

            imageMeal = itemView.findViewById(R.id.picture)
            titleMeal = itemView.findViewById(R.id.title)
            descMeal = itemView.findViewById(R.id.descreption)
            price = itemView.findViewById(R.id.price)
            orderNow = itemView.findViewById(R.id.orderNow)
            deleteToFav = itemView.findViewById(R.id.deleteToFav)
        }
    }
}
