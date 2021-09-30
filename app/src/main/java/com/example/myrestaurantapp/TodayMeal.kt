package com.example.myrestaurantapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodayMeal : AppCompat() {
    lateinit var searchArray: ArrayList<SearchDomain>
    lateinit var recyclerViewSearch: RecyclerView
    lateinit var mAdapter: Search_Adapter
    lateinit var editTextSearch: EditText

    lateinit var recyclerView: RecyclerView
    lateinit var meal1: FoodDomain
    lateinit var meal2: FoodDomain
    lateinit var meal3: FoodDomain
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_meal)

        initView()
        createSearchList()
        recyclerViewSearch()
        recyclerViewCategory()

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
        recyclerView = findViewById(R.id.recyclerView2)
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch)
        editTextSearch = findViewById(R.id.editTextTextPersonName)
    }

    fun recyclerViewCategory() {

        meal1 = FoodDomain()
        meal1.photo = R.drawable.avatar
        meal1.title = "Palestinian burner"
        meal1.descreption = "Chicken and vegetables"
        meal1.price = 50.0

        meal2 = FoodDomain()
        meal2.photo = R.drawable.avatar
        meal2.title = "Palestinian burner"
        meal2.descreption = "Chicken and vegetables"
        meal2.price = 50.0

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

        val text = editTextSearch.text.toString().trim()
    }
}
