package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PersonalityActivity : AppCompat() {
    lateinit var homeBtn: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView
    lateinit var userName: TextView
    lateinit var address: TextView
    lateinit var email: TextView
    lateinit var phone: TextView
    lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personality)

        initView()
        getData()

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

    fun initView() {
        homeBtn = findViewById(R.id.homeBtn)
        floatingActionButton = findViewById(R.id.card_btn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)
        userName = findViewById(R.id.UserName)
        address = findViewById(R.id.address)
        email = findViewById(R.id.Email)
        phone = findViewById(R.id.Phone)
        password = findViewById(R.id.password)
    }

    fun getData() {
        val db = Firebase.firestore
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Log.d("TAG", "${document.id} => ${document.data}")
                    val userText = document.data.get("Name").toString()
                    val phoneText = document.data.get("Phone").toString()
                    val addressText = document.data.get("Address").toString()
                    val emailText = document.data.get("Email").toString()
                    val passwordText = document.data.get("Password").toString()
                    userName.text = userText
                    phone.text = phoneText
                    address.text = addressText
                    email.text = emailText
                    password.text = passwordText
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }
}
