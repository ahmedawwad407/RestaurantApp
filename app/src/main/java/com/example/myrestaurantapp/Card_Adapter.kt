package com.example.myrestaurantapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Card_Adapter : RecyclerView.Adapter<Card_Adapter.Recycl_holder> {
    var managementCart: ManagementCart
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
        this.managementCart = ManagementCart(context)
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
        holder.priceEachItem.setText(meals.price.toString())
        val total = Math.round((meals.numberInCart * meals.price) * 100.0) / 100.0
        holder.totalEachItem.setText(total.toString())
        holder.numberItemTxt.setText(meals.numberInCart.toString())

        holder.addMeal.setOnClickListener {
            managementCart.plusNumberFood(
                foodList, position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.changed()
                    }
                }
            )
        }

        holder.minusMeal.setOnClickListener {
            managementCart.MinusNumerFood(
                foodList, position,
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
        var priceEachItem: TextView
        var totalEachItem: TextView
        var numberItemTxt: TextView
        var addMeal: Button
        var minusMeal: Button

        constructor(itemView: View) : super(itemView) {

            imageMeal = itemView.findViewById(R.id.imageCard)
            titleMeal = itemView.findViewById(R.id.title2Txt)
            priceEachItem = itemView.findViewById(R.id.priceEachItem)
            totalEachItem = itemView.findViewById(R.id.totalEachItem)
            numberItemTxt = itemView.findViewById(R.id.numberItemTxt)
            addMeal = itemView.findViewById(R.id.addMeals)
            minusMeal = itemView.findViewById(R.id.minusMeals)
        }
    }
}
