package com.mapimagery.cancer200sdkdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.mapswim.cancer200sdk.Cancer200
import com.mapswim.cancer200sdk.MapActivity

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init the Map Api
        Cancer200.initApi(this)

        // Launch the map
        val mapActivity = MapActivity()
        val mapIntent = Intent(this, MapActivity::class.java)
        startActivity(mapIntent)
    }
}