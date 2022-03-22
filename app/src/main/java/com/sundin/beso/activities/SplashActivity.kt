package com.sundin.beso.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.firebase.auth.FirebaseAuth
import com.sundin.beso.database.FirestoreClass

import com.sundin.beso.databinding.ActivitySplashBinding


class SplashActivity: BaseActivity() {

    lateinit var bindingSplashActivity: ActivitySplashBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingSplashActivity = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bindingSplashActivity.root)

        hideSystemBars()

        auth = FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed({
            // Get the current user id
            val currentUserID = FirestoreClass().getCurrentUserID()

            // Start the Intro Activity
            if (currentUserID.isNotEmpty()) {
                // Start the Main Activity
                val intentMainActivity: Intent = Intent(this, MainActivity::class.java)
                intentMainActivity.putExtra("currentUserID", currentUserID)
                startActivity(intentMainActivity)
            } else {
                // Start the Intro Activity
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
            }
            finish() // Call this when your activity is done and should be closed.
        }, 1000) // Here we pass the delay time in milliSeconds after which the splash activity will disappear.

    }

    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}
