package com.mapimagery.cancer200sdkdemo

import android.app.Activity
6004
import android.os.Bundle
import com.mapswim.cancer200sdk.Cancer200
import com.mapswim.cancer200sdk.MapSwimMapFragment




class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init the Map Api
        Cancer200.initApi(this)

        setContentView(R.layout.map_activity_container)

        if (savedInstanceState == null) {
            val fragMan: FragmentManager? = this.fragmentManager
            val fragTransaction: FragmentTransaction? = fragMan?.beginTransaction()
            fragTransaction?.add(R.id.fragment_container_view, MapSwimMapFragment())
            fragTransaction?.commit()
        }
    }
}

// Alternative integration via public MainActivty from the library
//class MainActivity : Activity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Init the Map Api
//        Cancer200.initApi(this)
//
//        // Launch the map
//        val mapActivity = MapActivity()
//        val mapIntent = Intent(this, MapActivity::class.java)
//        startActivity(mapIntent)
//    }
//}