package com.example.myrestaurantapp

import android.content.Context
import android.widget.Toast

class ManagementCart {

    private var context: Context? = null
    private var tinyDB: TinyDB? = null

    constructor(context: Context?) {
        this.context = context
        tinyDB = TinyDB(context)
    }

    fun insertFood(item: FoodDomain) {
        val listFood = getListCard()
        var existAlready = false
        var n = 0
        for (i in 0 until listFood.size) {
            if (listFood[i].title.equals(item.title)) {
                existAlready = true
                n = i
                break
            }
        }
        if (existAlready) {
            listFood[n].setNumberInCartt(item.numberInCart)
        } else {
            listFood.add(item)
        }
        tinyDB!!.putListObject("CardList", listFood)
        Toast.makeText(context, "Added To Your Card", Toast.LENGTH_LONG).show()
    }

    fun getListCard(): ArrayList<FoodDomain> {
        return tinyDB!!.getListObject("CardList")
    }

    fun plusNumberFood(
        listfood: ArrayList<FoodDomain>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        listfood[position].setNumberInCartt(listfood[position].numberInCart + 1)
        tinyDB!!.putListObject("CardList", listfood)
        changeNumberItemsListener.changed()
    }

    fun MinusNumerFood(
        listfood: ArrayList<FoodDomain>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        if (listfood[position].numberInCart == 1) {
            listfood.removeAt(position)
        } else {
            listfood[position].setNumberInCartt(listfood[position].numberInCart - 1)
        }
        tinyDB!!.putListObject("CardList", listfood)
        changeNumberItemsListener.changed()
    }

    fun getTotalFee(): Double {
        val listFood2 = getListCard()
        var fee = 0.0
        for (i in 0 until listFood2.size) {
            fee = fee + listFood2[i].price.toInt() * listFood2[i].numberInCart
        }
        return fee
    }
}
