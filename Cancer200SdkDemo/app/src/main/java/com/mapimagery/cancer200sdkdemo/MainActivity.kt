package com.mapimagery.cancer200sdkdemo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mapswim.cancer200sdk.MapActivity
import com.mapswim.cancer200sdk.Preferences

class MainActivity : Activity() {

    init {
        instance = this
    }

    companion object {
        var instance: MainActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun application() : Application {
            return instance!!.application
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is required to parts of the library can use the application contents
        // We may make this a sdk level initialisation later
        Preferences.applicationInstance = this

        val mapActivity = MapActivity()

        val mapIntent = Intent(this, MapActivity::class.java)
        startActivity(mapIntent)
    }
}