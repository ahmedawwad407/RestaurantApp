package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompat() {
    lateinit var searchArray: ArrayList<SearchDomain>
    lateinit var recyclerViewSearch: RecyclerView
    lateinit var mAdapter: Search_Adapter
    lateinit var editTextSearch: EditText
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var recyclerView: RecyclerView
    lateinit var meal1: FoodDomain
    lateinit var meal2: FoodDomain
    lateinit var meal3: FoodDomain
    lateinit var imageTodayMeal: ImageView
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView
    lateinit var mealsBtn: Button
    lateinit var sweetBtn: Button
    lateinit var drinkBtn: Button
    lateinit var appetizersBtn: Button
    lateinit var constraintLayout: ConstraintLayout
    lateinit var homeBtn: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        initFirebaseAuth()
        createSearchList()
        recyclerViewSearch()
        recyclerViewCategory()

        imageViewHome.setColorFilter(resources.getColor(R.color.orange), PorterDuff.Mode.SRC_ATOP)

        imageTodayMeal.setOnClickListener {

//            Handler().postDelayed({
//                constraintLayout.animate().translationY(-2200F).setDuration(1000)
//            }, 1800)
//
//            Handler().postDelayed({
//                startActivity(Intent(applicationContext, TodayMeal::class.java))
//            }, 3000)
            startActivity(Intent(applicationContext, TodayMeal::class.java))
        }

        mealsBtn.setOnClickListener {
            startActivity(Intent(applicationContext, MyMealsActivity::class.java))
        }
        drinkBtn.setOnClickListener {
            startActivity(Intent(applicationContext, MyDrinkActivity::class.java))
        }
        sweetBtn.setOnClickListener {
            startActivity(Intent(applicationContext, MySweetActivity::class.java))
        }
        appetizersBtn.setOnClickListener {
            startActivity(Intent(applicationContext, MyAppetizersActivity::class.java))
        }

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

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                recyclerViewSearch.visibility = View.VISIBLE
            }

            override fun onTextChanged(string: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filter(string.toString())
            }

            override fun afterTextChanged(string: Editable?) {
                val text = editTextSearch.text.toString().trim()
                if (text.isEmpty()) {
                    recyclerViewSearch.visibility = View.GONE
                }
            }
        })
    }

    private fun initFirebaseAuth() {
        // init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
    }

    private fun initView() {
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch)
        editTextSearch = findViewById(R.id.editTextTextPersonName)
        imageTodayMeal = findViewById(R.id.ImageTodayMeal)
        mealsBtn = findViewById(R.id.MealsBtn)
        sweetBtn = findViewById(R.id.SweetBtn)
        drinkBtn = findViewById(R.id.DrinkBtn)
        appetizersBtn = findViewById(R.id.AppetizersBtn)
        constraintLayout = findViewById(R.id.constraintLayout)
        homeBtn = findViewById(R.id.homeBtn)
        floatingActionButton = findViewById(R.id.card_btn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)
        recyclerView = findViewById(R.id.recyclerView)
        imageViewHome = findViewById(R.id.imageViewHome)
        imageViewAlert = findViewById(R.id.imageViewAlert)
        imageViewFavorite = findViewById(R.id.imageViewFavorite)
        imageViewProfile = findViewById(R.id.imageViewProfile)
    }

    fun recyclerViewCategory() {

        meal1 = FoodDomain()
        meal1.photo = R.drawable.avatar
        meal1.title = "Palestinian burner"
        meal1.descreption = "Chicken and vegetables"
        meal1.price = 50.0

        meal2 = FoodDomain()
        meal2.photo = R.drawable.pizza2
        meal2.title = "Palestinian pizza"
        meal2.descreption = "Chicken and vegetables"
        meal2.price = 90.0

        meal3 = FoodDomain()
        meal3.photo = R.drawable.avatar
        meal3.title = "Palestinian burner"
        meal3.descreption = "Chicken and vegetables"
        meal3.price = 50.0

        val m = LinearLayoutManager(this)
        m.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = m

        var arr = arrayListOf<FoodDomain>(
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3,
            meal1, meal2, meal3
        )

        val adapter = CategoryHome_Adapter(R.layout.meals, arr, this)
        recyclerView.adapter = adapter
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<SearchDomain> = ArrayList()
        for (item in searchArray) {
            if (item.title.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        mAdapter.filterList(filteredList)
    }

    private fun createSearchList() {
        searchArray = ArrayList()
        searchArray.add(SearchDomain(R.drawable.avatar, "chicken", 50.0))
        searchArray.add(SearchDomain(R.drawable.pizza2, "pizza", 50.0))
        searchArray.add(SearchDomain(R.drawable.drink, "drinkFruit", 90.0))
        searchArray.add(SearchDomain(R.drawable.sweet, "Chocolate Biscuits", 50.0))
        searchArray.add(SearchDomain(R.drawable.appetizers, "Biscuits with vegetables", 20.0))
        searchArray.add(SearchDomain(R.drawable.avatar, "chicken", 50.0))
        searchArray.add(SearchDomain(R.drawable.pizza2, "pizza", 50.0))
        searchArray.add(SearchDomain(R.drawable.drink, "drinkFruit", 90.0))
        searchArray.add(SearchDomain(R.drawable.sweet, "Chocolate Biscuits", 50.0))
        searchArray.add(SearchDomain(R.drawable.appetizers, "Biscuits with vegetables", 20.0))
        searchArray.add(SearchDomain(R.drawable.avatar, "chicken", 50.0))
        searchArray.add(SearchDomain(R.drawable.pizza2, "pizza", 50.0))
        searchArray.add(SearchDomain(R.drawable.drink, "drinkFruit", 90.0))
        searchArray.add(SearchDomain(R.drawable.sweet, "Chocolate Biscuits", 50.0))
        searchArray.add(SearchDomain(R.drawable.appetizers, "Biscuits with vegetables", 20.0))
    }

    fun recyclerViewSearch() {

        val m = LinearLayoutManager(this)
        m.orientation = LinearLayoutManager.VERTICAL
        recyclerViewSearch.layoutManager = m

        mAdapter = Search_Adapter(R.layout.search_item, searchArray, this)

        recyclerViewSearch.adapter = mAdapter
    }

    override fun onBackPressed() {
        exist_dialogOnbackPressed()
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

    fun exist_dialogOnbackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
            .setCancelable(false)
            .setMessage(R.string.messageAlert)
            .setPositiveButton(R.string.Yes) { dialogInterface, i ->

                val a = Intent(Intent.ACTION_MAIN)
                a.addCategory(Intent.CATEGORY_HOME)
                a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(a)
            }
            .setNegativeButton(R.string.No) { dialogInterface, i ->
                dialogInterface.cancel()
            }

        val dialog = builder.create()
        dialog.show()
    }
}
