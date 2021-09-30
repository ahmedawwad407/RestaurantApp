package com.example.myrestaurantapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.design_1.OnBoardingFragment1
import com.example.design_1.OnBoardingFragment2
import com.example.design_1.OnBoardingFragment3

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompat() {
    lateinit var pagerAdapter: ScreenSlidPagerAdapter
    lateinit var anim: Animation
    lateinit var imageView: ImageView
    lateinit var imageView2: ImageView
    lateinit var textView: TextView

    lateinit var viewPager: ViewPager
    companion object {
        val NUM_PAGES = 3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        imageView = findViewById(R.id.imageView)
        imageView2 = findViewById(R.id.imageView2)
        textView = findViewById(R.id.textView)
        viewPager = findViewById(R.id.viewpager)

        pagerAdapter = ScreenSlidPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        anim = AnimationUtils.loadAnimation(applicationContext, R.anim.o_b_anim)
        viewPager.startAnimation(anim)

        imageView.animate().translationY(-2200F).setDuration(1000).setStartDelay(1800)
        imageView2.animate().translationY(1400F).setDuration(1000).setStartDelay(1800)
        textView.animate().translationY(1400F).setDuration(1000).setStartDelay(1800)
    }

    class ScreenSlidPagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int {
            return NUM_PAGES
        }

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return OnBoardingFragment1()
                1 -> return OnBoardingFragment2()
                2 -> return OnBoardingFragment3()
                else -> return null!!
            }
        }
    }
}
