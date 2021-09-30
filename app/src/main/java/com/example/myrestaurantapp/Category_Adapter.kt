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

class Category_Adapter : RecyclerView.Adapter<Category_Adapter.Recycl_holder> {
    var managementPrefernses: ManagementPrefernses
    var layoutid: Int = 0
    var foodList: ArrayList<FoodDomain>
    var context: Context
    var changeNumberItemsListener: ChangeNumberItemsListener

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

        val meals = foodList.get(position) // return Meals
        holder.imageMeal.setImageResource(meals.photo)
        holder.titleMeal.setText(meals.title)
        holder.descMeal.setText(meals.descreption)
        holder.price.setText(meals.price.toString())

        holder.orderNow.setOnClickListener {
            val i = Intent(context, ShowDetailsActivity::class.java)
            i.putExtra("object", meals)
            context.startActivity(i)
        }

        holder.addToFav.setOnClickListener {
            notifyDataSetChanged()
            managementPrefernses.insertFood(meals)
            changeNumberItemsListener.changed()

            /*managementPrefernses.addToFav(
                foodList,
                position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.changed()

                    }

                })*/
        }
    }

    inner class Recycl_holder : RecyclerView.ViewHolder {
        var imageMeal: ImageView
        var titleMeal: TextView
        var descMeal: TextView
        var price: TextView
        var orderNow: Button
        var addToFav: ImageView

        constructor(itemView: View) : super(itemView) {

            imageMeal = itemView.findViewById(R.id.picture)
            titleMeal = itemView.findViewById(R.id.title)
            descMeal = itemView.findViewById(R.id.descreption)
            price = itemView.findViewById(R.id.price)
            orderNow = itemView.findViewById(R.id.orderNow)
            addToFav = itemView.findViewById(R.id.addToFav)
        }
    }
}
