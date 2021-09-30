package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RateUsActivity : AppCompat() {
    lateinit var homeBtn: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    lateinit var imageGoogle: ImageView
    lateinit var imageTwitter: ImageView
    lateinit var imageFacebook: ImageView
    lateinit var imageInstagram: ImageView
    lateinit var rateUs: RatingBar
    lateinit var textViewRate: TextView
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView

    var myRating = 0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_us)

        initView()

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

        imageGoogle.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            val linkGoogle = "https://instagram.com/fatafet_rs?utm_medium=copy_link"
            i.setData(Uri.parse(linkGoogle))
            startActivity(i)
        }

        imageTwitter.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            val linkTwitter = "https://instagram.com/fatafet_rs?utm_medium=copy_link"
            i.setData(Uri.parse(linkTwitter))
            startActivity(i)
        }
        imageFacebook.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            val linkFacebook = "https://www.facebook.com/10mfat/"
            i.setData(Uri.parse(linkFacebook))
            startActivity(i)
        }

        imageInstagram.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            val linkInstagram = "https://instagram.com/fatafet_rs?utm_medium=copy_link"
            i.setData(Uri.parse(linkInstagram))
            startActivity(i)
        }

        rateUs.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(rate: RatingBar?, position: Float, p2: Boolean) {

                val rating = position.toInt()
                var messsage = ""
                myRating = rate!!.rating

                when (rating) {
                    1 -> messsage = "sorry to hear that! :("
                    2 -> messsage = "You always accept suggestion!"
                    3 -> messsage = "Good enough!"
                    4 -> messsage = "Great! ThankYou!"
                    5 -> messsage = "You are the best!"
                }
                Toast.makeText(applicationContext, "your rating is:$messsage", Toast.LENGTH_LONG).show()
            }
        })

        textViewRate.setOnClickListener {
            Toast.makeText(applicationContext, "your rating is:$myRating", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        homeBtn = findViewById(R.id.homeBtn)
        textViewRate = findViewById(R.id.textRate)
        floatingActionButton = findViewById(R.id.card_btn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)
        imageGoogle = findViewById(R.id.imageGoogle)
        imageTwitter = findViewById(R.id.imageTwitter)
        imageFacebook = findViewById(R.id.imageFacebook)
        imageInstagram = findViewById(R.id.imageInstagram)
        rateUs = findViewById(R.id.ratiing)
        imageViewHome = findViewById(R.id.imageViewHome)
        imageViewAlert = findViewById(R.id.imageViewAlert)
        imageViewFavorite = findViewById(R.id.imageViewFavorite)
        imageViewProfile = findViewById(R.id.imageViewProfile)
    }
}
