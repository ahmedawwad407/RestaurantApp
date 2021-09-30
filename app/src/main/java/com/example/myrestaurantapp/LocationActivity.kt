package com.example.myrestaurantapp

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.myrestaurantapp.databinding.ActivityLocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.io.IOException
import java.util.*

class LocationActivity : AppCompat(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityLocationBinding
    lateinit var getlocation: TextView
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var latitude = 0.0
    var longitude = 0.0
    var countryName = ""
    var locality = ""
    var myAddress = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        getlocation = findViewById(R.id.textLocation)

        // init fused location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getlocation.setOnClickListener {
            // checkPermission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                    val location = it.result
                    if (location != null) {
                        // init geoCoder
                        val geoCoder = Geocoder(this, Locale.getDefault())
                        try {
                            // init address
                            val address: ArrayList<Address> = geoCoder.getFromLocation(
                                location.latitude, location.longitude, 1
                            ) as ArrayList<Address>

                            // set latitude
                            latitude = address[0].latitude
                            Toast.makeText(this, latitude.toString(), Toast.LENGTH_LONG).show()
                            // set longitude
                            longitude = address[0].longitude
                            Toast.makeText(this, longitude.toString(), Toast.LENGTH_LONG).show()
                            // set countryName
                            countryName = address[0].countryName
                            Toast.makeText(this, countryName, Toast.LENGTH_LONG).show()
                            // set locality
                            locality = address[0].locality
                            Toast.makeText(this, locality, Toast.LENGTH_LONG).show()

                            // set address
                            myAddress = address[0].getAddressLine(0)
                            Toast.makeText(this, myAddress, Toast.LENGTH_LONG).show()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 44)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.uiSettings.isRotateGesturesEnabled = true
        mMap.uiSettings.isTiltGesturesEnabled = true

        // Add a marker in Sydney and move the camera
        val myLocation = LatLng(latitude, longitude)
        val markerOptions = MarkerOptions()
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mMap.addMarker(markerOptions.position(myLocation).title("Marker in ALQarara").snippet("Details AlQarara"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14f))

        val aLQarara2 = LatLng(30.3759335, 34.33729)
        val markerOptions2 = MarkerOptions()
        markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        mMap.addMarker(markerOptions2.position(aLQarara2).title("Marker in ALQarara2").snippet("Details AlQarara2"))

        mMap.addPolyline(
            PolylineOptions()
                .add(myLocation)
                .add(aLQarara2)
                .color(Color.RED)
                .visible(true)
        )

        val c1 = mMap.addCircle(
            CircleOptions()
                .center(myLocation)
                .radius(500.0)
                .clickable(true)
                .fillColor(Color.YELLOW)
                .strokeColor(Color.BLUE)
                .strokeWidth(5f)
        )
        c1.tag = "c1"

        val c2 = mMap.addCircle(
            CircleOptions()
                .center(aLQarara2)
                .radius(500.0)
                .clickable(true)
                .fillColor(Color.YELLOW)
                .strokeColor(Color.BLUE)
                .strokeWidth(5f)
        )
        c2.tag = "c2"

        mMap.setOnCircleClickListener { circle ->
            if (circle.tag!! == "c1") {
            } else if (circle.tag!! == "c2") {
            }
        }
    }
}
