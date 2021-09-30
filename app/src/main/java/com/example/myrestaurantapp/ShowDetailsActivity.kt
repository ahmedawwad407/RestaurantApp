package com.example.myrestaurantapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ShowDetailsActivity : AppCompat() {

    lateinit var title: TextView
    lateinit var numberOfOrder: TextView
    lateinit var price: TextView
    lateinit var desc: TextView
    lateinit var image: ImageView
    lateinit var add_to_card: Button
    lateinit var addMeal: Button
    lateinit var minusMeal: Button
    private lateinit var objectt: FoodDomain
    lateinit var managementCart: ManagementCart

    var countOfOrder = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        managementCart = ManagementCart(this)
        initView()
        getBundle()
    }

    fun getBundle() {
        objectt = intent.getSerializableExtra("object") as FoodDomain

        title.setText(objectt.title)
        desc.setText(objectt.descreption)
        price.setText(objectt.price.toString())
        image.setImageResource(objectt.photo)
        numberOfOrder.setText(countOfOrder.toString())

        addMeal.setOnClickListener {
            countOfOrder += 1
            numberOfOrder.setText(countOfOrder.toString())
        }
        minusMeal.setOnClickListener {
            if (countOfOrder > 1) {
                countOfOrder -= 1
            }
            numberOfOrder.setText(countOfOrder.toString())
        }

        add_to_card.setOnClickListener {
            objectt.setNumberInCartt(countOfOrder)
            managementCart.insertFood(objectt)
        }
    }

    fun initView() {
        title = findViewById(R.id.title)
        price = findViewById(R.id.price)
        desc = findViewById(R.id.descreption)
        image = findViewById(R.id.imageView11)
        numberOfOrder = findViewById(R.id.numberOfOrder)
        add_to_card = findViewById(R.id.add_to_card)
        addMeal = findViewById(R.id.addMeals)
        minusMeal = findViewById(R.id.minusMeals)
    }
}
