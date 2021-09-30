package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PrefernsesActivity : AppCompat() {
    lateinit var recyclerView: RecyclerView
    lateinit var managementPrefernses: ManagementPrefernses

    // lateinit var arrayObject:ArrayList<FoodDomain>
    lateinit var homeBtn: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    lateinit var emptyTxt: TextView
    lateinit var myConstraintLayout: ConstraintLayout
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefernses)

        managementPrefernses = ManagementPrefernses(this)

        initView()
        imageViewFavorite.setColorFilter(
            resources.getColor(R.color.orange),
            PorterDuff.Mode.SRC_ATOP
        )
        recyclerViewCategory()
        bottomNavigation()
    }

    private fun bottomNavigation() {

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        homeBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            imageViewHome.setColorFilter(
                resources.getColor(R.color.orange),
                PorterDuff.Mode.SRC_ATOP
            )
        }

        profileBtn.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            imageViewProfile.setColorFilter(
                resources.getColor(R.color.orange),
                PorterDuff.Mode.SRC_ATOP
            )
        }
        favoriteBtn.setOnClickListener {
            startActivity(Intent(this, PrefernsesActivity::class.java))
            imageViewFavorite.setColorFilter(
                resources.getColor(R.color.orange),
                PorterDuff.Mode.SRC_ATOP
            )
        }
        alertBtn.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
            imageViewAlert.setColorFilter(
                resources.getColor(R.color.orange),
                PorterDuff.Mode.SRC_ATOP
            )
        }
    }

    fun recyclerViewCategory() {

        val m = LinearLayoutManager(this)
        m.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = m

        val adapter = Prefernses_Adapter(
            R.layout.my_meals_prefernses,
            managementPrefernses.getListPrefernses(),
            this,
            object : ChangeNumberItemsListener {
                override fun changed() {
                }
            }
        )

        recyclerView.adapter = adapter

        if (managementPrefernses.getListPrefernses().isEmpty()) {
            emptyTxt.visibility = View.VISIBLE
            myConstraintLayout.visibility = View.GONE
        } else {
            emptyTxt.visibility = View.GONE
            myConstraintLayout.visibility = View.VISIBLE
        }
    }

    fun initView() {
        recyclerView = findViewById(R.id.recyclerView3)
        floatingActionButton = findViewById(R.id.card_btn)
        homeBtn = findViewById(R.id.homeBtn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)
        emptyTxt = findViewById(R.id.emptyTxt)
        myConstraintLayout = findViewById(R.id.myConstraintLayout)
        imageViewHome = findViewById(R.id.imageViewHome)
        imageViewAlert = findViewById(R.id.imageViewAlert)
        imageViewFavorite = findViewById(R.id.imageViewFavorite)
        imageViewProfile = findViewById(R.id.imageViewProfile)
    }
}
