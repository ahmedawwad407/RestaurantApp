package com.example.myrestaurantapp

import java.io.Serializable

class FoodDomain : Serializable {
    var photo: Int = 0
    var title: String = ""
    var descreption: String = ""
    var price: Double = 0.0
    var numberInCart: Int = 0

    fun setNumberInCartt(numberInCart: Int) {
        this.numberInCart = numberInCart
    }
}
