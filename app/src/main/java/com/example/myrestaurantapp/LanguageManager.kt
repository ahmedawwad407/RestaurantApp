package com.example.myrestaurantapp

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class LanguageManager {
    private var context: Context
    private var sharedPreferences: SharedPreferences
    constructor(context: Context) {
        this.context = context
        sharedPreferences = context.getSharedPreferences("LANG", Context.MODE_PRIVATE)
    }

    fun updateResources(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val resources = context.resources
        val config = resources.configuration
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
        // save data to shared preferences
        setLang(lang)
    }

    fun getLang(): String {
        return sharedPreferences.getString("MyLang", "en").toString()
    }
    fun setLang(lang: String) {
        val editor = sharedPreferences.edit()
        editor.putString("MyLang", lang)
        editor.commit()
    }
}
