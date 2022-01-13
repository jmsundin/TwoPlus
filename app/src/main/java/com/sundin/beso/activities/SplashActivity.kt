package com.sundin.beso.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import com.sundin.beso.R
import com.sundin.beso.database.FirestoreClass

class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(this@SplashActivity, IntroActivity::class.java))

            // Get the current user id
//            val currentUserID = FirestoreClass().getCurrentUserID()
            // Start the Intro Activity

//            if (currentUserID.isNotEmpty()) {
//                // Start the Main Activity
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//            } else {
//                // Start the Intro Activity
//                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
//            }
            finish() // Call this when your activity is done and should be closed.
        }, 2500) // Here we pass the delay time in milliSeconds after which the splash activity will disappear.

    }

}