package com.amalwin.lifecyclemonitor

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.amalwin.lifecyclemonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(AppConstants.TAG, "MainActivity / onCreate called...")
        if (savedInstanceState != null) {
            Log.i(AppConstants.TAG, "MainActivity / Bundle is having data to load in UI")
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.btnNext.setOnClickListener {
            val nextIntent = Intent(this, NextActivity::class.java)
            nextIntent.apply {
                putExtra("NAME", "FIRST TO SECOND")
            }
            startActivity(nextIntent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(AppConstants.TAG, "MainActivity / onRestart called...")
    }

    override fun onStart() {
        super.onStart()
        Log.i(AppConstants.TAG, "MainActivity / onStart called...")
    }

    override fun onResume() {
        super.onResume()
        Log.i(AppConstants.TAG, "MainActivity / onResume called...")
    }

    override fun onPause() {
        super.onPause()
        Log.i(AppConstants.TAG, "MainActivity / onPause called...")
    }

    override fun onStop() {
        super.onStop()
        Log.i(AppConstants.TAG, "MainActivity / onStop called...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(AppConstants.TAG, "MainActivity / onDestroy called...")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(AppConstants.TAG, "MainActivity / onSaveInstanceState called...")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(AppConstants.TAG, "MainActivity / onRestoreInstanceState called...")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i(AppConstants.TAG, "MainActivity / onConfigurationChanged called...")
        Log.i(AppConstants.TAG, "MainActivity / " + newConfig.layoutDirection.toString() + "," + newConfig.orientation)
    }
}