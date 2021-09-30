package com.example.myrestaurantapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class AppCompat : AppCompatActivity() {
    open lateinit var languageManager: LanguageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        languageManager = LanguageManager(this)
        languageManager.updateResources(languageManager.getLang())
    }
}
