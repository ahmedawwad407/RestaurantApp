package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PaymentActivity : AppCompat() {
    lateinit var homeBtn: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        homeBtn = findViewById(R.id.homeBtn)
        floatingActionButton = findViewById(R.id.card_btn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        homeBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            imageViewHome.setColorFilter(resources.getColor(R.color.orange), PorterDuff.Mode.SRC_ATOP)
        }

        profileBtn.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            imageViewProfile.setColorFilter(resources.getColor(R.color.orange), PorterDuff.Mode.SRC_ATOP)
        }
        favoriteBtn.setOnClickListener {
            startActivity(Intent(this, PrefernsesActivity::class.java))
            imageViewFavorite.setColorFilter(resources.getColor(R.color.orange), PorterDuff.Mode.SRC_ATOP)
        }
        alertBtn.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
            imageViewAlert.setColorFilter(resources.getColor(R.color.orange), PorterDuff.Mode.SRC_ATOP)
        }
    }
}
