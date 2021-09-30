package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ProfileActivity : AppCompat() {
    lateinit var homeBtn: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    lateinit var signOut: TextView
    lateinit var userPersonality: TextView
    lateinit var prefernses: TextView
    lateinit var advertising: TextView
    lateinit var rateUs: TextView
    lateinit var location: TextView
    lateinit var help: TextView
    lateinit var changeLanguage: TextView
    lateinit var builder: AlertDialog.Builder
    override lateinit var languageManager: LanguageManager
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        homeBtn = findViewById(R.id.homeBtn)
        floatingActionButton = findViewById(R.id.card_btn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)

        userPersonality = findViewById(R.id.UserPersonality)
        prefernses = findViewById(R.id.Prefernses)
        advertising = findViewById(R.id.advertising)
        rateUs = findViewById(R.id.rate_us)
        location = findViewById(R.id.Location)
        help = findViewById(R.id.Help)
        signOut = findViewById(R.id.Sign_Out)
        changeLanguage = findViewById(R.id.changeLanguage)
        imageViewHome = findViewById(R.id.imageViewHome)
        imageViewAlert = findViewById(R.id.imageViewAlert)
        imageViewFavorite = findViewById(R.id.imageViewFavorite)
        imageViewProfile = findViewById(R.id.imageViewProfile)

        languageManager = LanguageManager(this)

        imageViewProfile.setColorFilter(resources.getColor(R.color.orange), PorterDuff.Mode.SRC_ATOP)

        userPersonality.setOnClickListener {
            startActivity(Intent(applicationContext, PersonalityActivity::class.java))
        }

        prefernses.setOnClickListener {
            startActivity(Intent(applicationContext, PrefernsesActivity::class.java))
        }
        advertising.setOnClickListener {
            startActivity(Intent(applicationContext, AdvertisingActivity::class.java))
        }
        rateUs.setOnClickListener {
            startActivity(Intent(applicationContext, RateUsActivity::class.java))
        }

        location.setOnClickListener {
            startActivity(Intent(applicationContext, LocationActivity::class.java))
        }

        help.setOnClickListener {
            startActivity(Intent(applicationContext, HelpActivity::class.java))
        }

        signOut.setOnClickListener {
            exist_dialog()
        }
        changeLanguage.setOnClickListener {
            showChangeLanguageDialog()
        }

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

    private fun showChangeLanguageDialog() {
        builder = AlertDialog.Builder(this)
            .setMessage(getString(R.string.chooseLanguage))
            .setPositiveButton("الإنجليزية") { dialogInterface, i ->
                languageManager.updateResources("en")
                recreate()
                dialogInterface.cancel()
            }
            .setNegativeButton("العربية") { dialogInterface, i ->

                languageManager.updateResources("ar")
                recreate()

                dialogInterface.cancel()
            }

        val dialog = builder.create()
        dialog.show()
    }

    fun exist_dialog() {
        builder = AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
            .setCancelable(false)
            .setMessage(getString(R.string.messageAlert))
            .setPositiveButton(getString(R.string.Yes)) { dialogInterface, i ->
                firebaseAuth.signOut()
                checkUser()
            }
            .setNegativeButton(getString(R.string.No)) { dialogInterface, i ->
                dialogInterface.cancel()
            }

        val dialog = builder.create()
        dialog.show()
    }

    private fun checkUser() {
        // check user logIn or Not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            // user is already logged in
            val email = firebaseUser.email
            Toast.makeText(applicationContext, "loggedIn as $email", Toast.LENGTH_SHORT).show()
        } else {
            // user is not logged in
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }
}
