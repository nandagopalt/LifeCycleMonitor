package com.amalwin.lifecyclemonitor

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.amalwin.lifecyclemonitor.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModelProviderFactory: MainActivityViewModelProviderFactory
    private lateinit var mainActivityViewModel: MainActivityViewModel

    private val viewModel: MainActivityViewModel by viewModels()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(AppConstants.TAG, "MainActivity / onCreate called...")
        if (savedInstanceState != null) {
            Log.i(AppConstants.TAG, "MainActivity / Bundle is having data to load in UI")
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        mainActivityViewModelProviderFactory = MainActivityViewModelProviderFactory()
        mainActivityViewModel = ViewModelProvider(
            this,
            mainActivityViewModelProviderFactory
        )[MainActivityViewModel::class.java]
        binding.btnNext.setOnClickListener {
            /*val nextIntent = Intent(this, NextActivity::class.java)
            nextIntent.apply {
                putExtra("NAME", "FIRST TO SECOND")
            }
            startActivity(nextIntent)*/

            mainActivityViewModel.counter.observe(this, Observer {
                println(it)
            })

            lifecycleScope.launchWhenStarted {
                println("Thread execution from lifecycleScope is ${Thread.currentThread().name}")
                launch(Dispatchers.IO) {
                    println("Thread execution from launch from lifecycleScope is ${Thread.currentThread().name}")
                    mainActivityViewModel.incrementCount()
                }
            }
        }

        val flow = flow {
            println("Thread running from producer side : ${Thread.currentThread().name}")
            for (i in 1..10) {
                emit(i)
                delay(1000L)
            }
        }

        lifecycleScope.launchWhenStarted {
            println("Thread running from receiver/consumer side : ${Thread.currentThread().name}")
            flow.buffer().filter {
                it % 2 == 0
            }.map {
                it * it
            }.collect {
                println(it)
                delay(2000L)
            }
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
        Log.i(
            AppConstants.TAG,
            "MainActivity / " + newConfig.layoutDirection.toString() + "," + newConfig.orientation
        )
    }
}