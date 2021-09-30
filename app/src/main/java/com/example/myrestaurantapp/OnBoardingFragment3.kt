package com.example.design_1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myrestaurantapp.MainActivity
import com.example.myrestaurantapp.R

class OnBoardingFragment3 : Fragment() {
    lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_on_bording_3, container, false)
        button = v.findViewById(R.id.button)

        button.setOnClickListener {
            startActivity(Intent(v.context, MainActivity::class.java))
        }
        return v
    }
}
