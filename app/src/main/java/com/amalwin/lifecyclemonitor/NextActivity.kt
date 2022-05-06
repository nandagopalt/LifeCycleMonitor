package com.amalwin.lifecyclemonitor

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(AppConstants.TAG, "NextActivity / onCreate called...")
        setContentView(R.layout.activity_next)
        val name = intent.getStringExtra("NAME")
        name.let {
            Log.i(AppConstants.TAG, "Name : it")
        }

        val flow = flow<String> {
            for(i in 1..10) {
                emit("Hello World!")
                delay(1000L)
            }
        }

        GlobalScope.launch {
            flow.collect() {
                println("Welcome $it")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(AppConstants.TAG, "NextActivity / onStart called...")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(AppConstants.TAG, "MainActivity / onRestart called...")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(AppConstants.TAG, "NextActivity / onRestoreInstanceState called...")
    }

    override fun onResume() {
        super.onResume()
        Log.i(AppConstants.TAG, "NextActivity / onResume called...")
    }

    override fun onPause() {
        super.onPause()
        Log.i(AppConstants.TAG, "NextActivity / onPause called...")
    }

    override fun onStop() {
        super.onStop()
        Log.i(AppConstants.TAG, "NextActivity / onStop called...")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(AppConstants.TAG, "NextActivity / onSaveInstanceState called...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(AppConstants.TAG, "NextActivity / onDestroy called...")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i(AppConstants.TAG, "NextActivity / onConfigurationChanged called...")
        Log.i(
            AppConstants.TAG,
            "NextActivity " + newConfig.layoutDirection + "," + newConfig.orientation
        )
    }


}