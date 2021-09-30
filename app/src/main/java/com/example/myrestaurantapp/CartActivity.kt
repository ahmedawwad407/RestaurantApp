package com.example.myrestaurantapp

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CartActivity : AppCompatActivity() {
    lateinit var searchArray: ArrayList<SearchDomain>
    lateinit var recyclerViewSearch: RecyclerView
    lateinit var mAdapter: Search_Adapter
    lateinit var profileBtn: LinearLayout
    lateinit var favoriteBtn: LinearLayout
    lateinit var alertBtn: LinearLayout
    lateinit var editTextSearch: EditText
    lateinit var imageViewHome: ImageView
    lateinit var imageViewAlert: ImageView
    lateinit var imageViewFavorite: ImageView
    lateinit var imageViewProfile: ImageView
    lateinit var managementCart: ManagementCart
    lateinit var totalFeeTxt: TextView
    lateinit var deliveryTxt: TextView
    lateinit var taxTxt: TextView
    lateinit var totalTxt: TextView
    lateinit var emptyTxt: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var myConstraintLayout: ConstraintLayout
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var homeBtn: LinearLayout
    lateinit var checkoutBtn: Button
    private var tax = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        managementCart = ManagementCart(this)

        initView()
        createSearchList()
        recyclerViewSearch()
        recyclerViewCategory()
        calculateCard()
        bottomNavigation()

        checkoutBtn.setOnClickListener {
            startActivity(Intent(applicationContext, PaymentActivity::class.java))
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

    private fun bottomNavigation() {

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

    fun initView() {
        recyclerView = findViewById(R.id.recyclerView5)
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch)
        editTextSearch = findViewById(R.id.editTextTextPersonName)
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        taxTxt = findViewById(R.id.taxTxt)
        totalTxt = findViewById(R.id.totalTxt)
        emptyTxt = findViewById(R.id.emptyTxt)
        myConstraintLayout = findViewById(R.id.myConstraintLayout)
        checkoutBtn = findViewById(R.id.CheckoutBtn)
        floatingActionButton = findViewById(R.id.card_btn)
        homeBtn = findViewById(R.id.homeBtn)
        profileBtn = findViewById(R.id.profileBtn)
        favoriteBtn = findViewById(R.id.favoriteBtn)
        alertBtn = findViewById(R.id.alertBtn)
        imageViewHome = findViewById(R.id.imageViewHome)
        imageViewAlert = findViewById(R.id.imageViewAlert)
        imageViewFavorite = findViewById(R.id.imageViewFavorite)
        imageViewProfile = findViewById(R.id.imageViewProfile)
    }

    fun recyclerViewCategory() {

        val m = LinearLayoutManager(this)
        m.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = m

        val adapter = Card_Adapter(
            R.layout.meals_card,
            managementCart.getListCard(),
            this,
            object : ChangeNumberItemsListener {
                override fun changed() {
                    calculateCard()
                }
            }
        )

        recyclerView.adapter = adapter

        if (managementCart.getListCard().isEmpty()) {
            emptyTxt.visibility = View.VISIBLE
            myConstraintLayout.visibility = View.GONE
        } else {
            emptyTxt.visibility = View.GONE
            myConstraintLayout.visibility = View.VISIBLE
        }
    }

    private fun calculateCard() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round(managementCart.getTotalFee() * percentTax * 100.0) / 100.0
        val total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0
        val itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0
        totalFeeTxt.text = "$$itemTotal"
        taxTxt.text = "$$tax"
        deliveryTxt.text = "$$delivery"
        totalTxt.text = "$$total"
    }

    fun recyclerViewSearch() {

        val m = LinearLayoutManager(this)
        m.orientation = LinearLayoutManager.VERTICAL
        recyclerViewSearch.layoutManager = m

        mAdapter = Search_Adapter(R.layout.search_item, searchArray, this)

        recyclerViewSearch.adapter = mAdapter
    }
}
