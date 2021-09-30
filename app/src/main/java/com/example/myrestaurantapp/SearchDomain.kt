package com.example.myrestaurantapp

import java.io.Serializable

class SearchDomain : Serializable {
    var photo: Int = 0
    var title: String = ""
    var price: Double = 0.0

    constructor(photo: Int, title: String, price: Double) {
        this.photo = photo
        this.title = title
        this.price = price
    }
}
