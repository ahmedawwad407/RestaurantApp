package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyAppetizersActivity : AppCompat() {
    lateinit var searchArray: ArrayList<SearchDomain>
    lateinit var recyclerViewSearch: RecyclerView
    lateinit var mAdapter: Search_Adapter
    lateinit var editTextSearch: EditText
    lateinit var homeBtn: LinearLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    lateinit var recyclerView: RecyclerView
    lateinit var appetizers1: FoodDomain
    lateinit var appetizers2: FoodDomain
    lateinit var appetizers3: FoodDomain
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_appetizers)

        initView()
        createSearchList()
        recyclerViewSearch()
        recyclerViewCategory()

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

    private fun initView() {
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch)
        editTextSearch = findViewById(R.id.editTextTextPersonName)
        homeBtn = findViewById(R.id.homeBtn)
        floatingActionButton = findViewById(R.id.card_btn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)
        recyclerView = findViewById(R.id.recyclerView3)
    }

    fun recyclerViewCategory() {

        appetizers1 = FoodDomain()
        appetizers1.photo = R.drawable.appetizers
        appetizers1.title = "vegetables"
        appetizers1.descreption = "Biscuits and corn"
        appetizers1.price = 20.0

        appetizers2 = FoodDomain()
        appetizers2.photo = R.drawable.appetizers
        appetizers2.title = "vegetables"
        appetizers2.descreption = "Biscuits and corn"
        appetizers2.price = 20.0

        appetizers3 = FoodDomain()
        appetizers3.photo = R.drawable.appetizers
        appetizers3.title = "vegetables"
        appetizers3.descreption = "Biscuits and corn"
        appetizers3.price = 20.0

        val m = LinearLayoutManager(this)
        m.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = m

        var arr = arrayListOf(
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3,
            appetizers1, appetizers2, appetizers3

        )

        val adapter = Category_Adapter(
            R.layout.my_meals, arr, this,
            object : ChangeNumberItemsListener {
                override fun changed() {
                }
            }
        )
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

        val text = editTextSearch.text.toString().trim()
    }
}
