package com.example.myrestaurantapp

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class ManagementPrefernses {

    var context: Context
    private var tinyDB: TinyDB? = null

    constructor(context: Context) {
        this.context = context
        tinyDB = TinyDB(context)
    }

    fun insertFood(item: FoodDomain) {
        val listFood = getListPrefernses()
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
        tinyDB!!.putListObject("PrefernsesList", listFood)
        Toast.makeText(context, "Added To Your Preferences", Toast.LENGTH_LONG).show()
    }

    fun getListPrefernses(): ArrayList<FoodDomain> {
        return tinyDB!!.getListObject("PrefernsesList")
    }

    fun deleteToFav(
        foodList: ArrayList<FoodDomain>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        val builder = AlertDialog.Builder(context, R.style.MyAlertDialogStyle)
            .setCancelable(false)
            .setMessage(context.getString(R.string.preferances_dialog))
            .setPositiveButton(R.string.Yes) { dialogInterface, i ->
                foodList.removeAt(position) // delet item list
                tinyDB!!.putListObject("PrefernsesList", foodList)
                changeNumberItemsListener.changed()
                dialogInterface.cancel()
            }
            .setNegativeButton(R.string.No) { dialogInterface, i ->
                dialogInterface.cancel()
            }

        val dialog = builder.create()
        dialog.show()
    }

//    fun addToFav(
//        foodList: ArrayList<FoodDomain>,
//        position: Int,
//        changeNumberItemsListener: ChangeNumberItemsListener
//    ) {
//        changeNumberItemsListener.changed()
//        val meal = foodList.get(position)
//        insertFood(meal)
//        changeNumberItemsListener.changed()
//
//    }
}
