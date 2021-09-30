package com.example.myrestaurantapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompat() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var authStateListener: FirebaseAuth.AuthStateListener
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.ViewPager)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.sign_in))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.sign_up))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.tabRippleColor = null // cancel shadow in pressed tab

        // init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }

        val adapter = LoginAdapter(supportFragmentManager, tabLayout.tabCount, this)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabLayout)
        )
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    override fun onBackPressed() {
        System.exit(0)
        super.onBackPressed()
    }

    private fun checkUser() {
        // check user LoggedIn or not
        val user = firebaseAuth.currentUser
        if (user != null) {
            // user already LoggedIn
            startActivity(Intent(this, HomeActivity::class.java))
            // finish()
        } else {

            Toast.makeText(
                baseContext, "please SignIn to continue...",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }
}
