package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class HelpActivity : AppCompat() {
    lateinit var recyclerView: ListView
    lateinit var homeBtn: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    lateinit var arrAdapter: ArrayAdapter<String>
    lateinit var arrHelp: ArrayList<String>
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        initView()
        arrHelp = arrayListOf(
            "North Korea’s hackers are reportedly targeting bitcoin exchanges \n\n by User Name in Category",
            "The director of Baidu’s Silicon Valley AI Lab has departed \n\n by User Name in Category",
            "India’s Zomato completes long-rumored acquisition of logistics startup Runnr \n\n by User Name in Category",
            "North Korea’s hackers are reportedly targeting bitcoin exchanges \n\n by User Name in Category",
            "The director of Baidu’s Silicon Valley AI Lab has departed \n\n by User Name in Category",
            "India’s Zomato completes long-rumored acquisition of logistics startup Runnr \n\n by User Name in Category",
            "North Korea’s hackers are reportedly targeting bitcoin exchanges \n\n by User Name in Category",
            "The director of Baidu’s Silicon Valley AI Lab has departed \n\n by User Name in Category",
            "India’s Zomato completes long-rumored acquisition of logistics startup Runnr \n\n by User Name in Category",
            "North Korea’s hackers are reportedly targeting bitcoin exchanges \n\n by User Name in Category",
            "The director of Baidu’s Silicon Valley AI Lab has departed \n\n by User Name in Category",
            "India’s Zomato completes long-rumored acquisition of logistics startup Runnr \n\n by User Name in Category",
            "North Korea’s hackers are reportedly targeting bitcoin exchanges \n\n by User Name in Category",
            "The director of Baidu’s Silicon Valley AI Lab has departed \n\n by User Name in Category",
            "India’s Zomato completes long-rumored acquisition of logistics startup Runnr \n\n by User Name in Category"
        )
        recyclerViewData()

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

    private fun initView() {
        homeBtn = findViewById(R.id.homeBtn)
        floatingActionButton = findViewById(R.id.card_btn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)
        recyclerView = findViewById(R.id.recyclerView3)
        imageViewHome = findViewById(R.id.imageViewHome)
        imageViewAlert = findViewById(R.id.imageViewAlert)
        imageViewFavorite = findViewById(R.id.imageViewFavorite)
        imageViewProfile = findViewById(R.id.imageViewProfile)
    }

    fun recyclerViewData() {
        arrAdapter = ArrayAdapter(this, R.layout.data_message, arrHelp)
        recyclerView.adapter = arrAdapter
    }
}
